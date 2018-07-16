package com.sununiq.cryptor.digest;

import com.sununiq.cryptor.CryptorException;
import com.sununiq.cryptor.util.AssertUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @program: cryptor-tool
 *
 * @description: 生成摘要的基类
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 08:45
 **/
public abstract class BaseDigest implements Digest {

	private static final int BUFF_SIZE = 1024 * 64;

	public abstract String getDigestAlgs();

	@Override
	public byte[] digest(byte[] data) {
		Objects.requireNonNull(data);

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(getDigestAlgs());
			messageDigest.update(data);
			return messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new CryptorException(e);
		}
	}


	@Override
	public byte[] digest(File file) {
		AssertUtil.fileExists(file);

		ByteBuffer buf = ByteBuffer.allocate(BUFF_SIZE);
		try (FileInputStream fis = new FileInputStream(file); FileChannel channel = fis.getChannel()) {

			MessageDigest messageDigest = MessageDigest.getInstance(getDigestAlgs());

			buf.clear();          // Prepare buffer for use
			while (channel.read(buf) >= 0 || buf.position() != 0) {
				buf.flip();
				messageDigest.update(buf);
				buf.compact();    // In case of partial write
			}

			return messageDigest.digest();
		} catch (IOException | NoSuchAlgorithmException e) {
			throw new CryptorException(e);
		}
	}

	@Override
	public byte[] digest(File file, long offset, long length) {
		AssertUtil.fileExists(file);
		AssertUtil.nonNegative(offset);
		AssertUtil.isPositive(length);

		try (InputStream in = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(in)) {

			MessageDigest messageDigest = MessageDigest.getInstance(getDigestAlgs());

			byte[] buf = new byte[BUFF_SIZE];
			skip(bis, offset);

			long remaining = length;
			int read;
			while (remaining > 0) {
				if (remaining > BUFF_SIZE) {
					read = bis.read(buf);
				} else {
					read = bis.read(buf, 0, (int) remaining);
				}

				if (read == -1) {
					break;
				}

				messageDigest.digest(buf, 0, read);
				remaining -= read;
			}
			return messageDigest.digest();
		} catch (IOException | NoSuchAlgorithmException | DigestException e) {
			throw new CryptorException(e);
		}
	}

	private void skip(InputStream in, long offset) throws IOException {
		long remaining = offset - in.skip(offset);
		while (remaining > 0) {
			long skip = in.skip(remaining);
			remaining -= skip;
		}
	}
}
