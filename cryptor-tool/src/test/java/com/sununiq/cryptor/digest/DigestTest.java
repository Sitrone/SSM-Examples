package com.sununiq.cryptor.digest;

import com.sununiq.cryptor.digest.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import java.io.File;
import java.util.Base64;

@Slf4j
public class DigestTest {
	private static File file;

	@BeforeClass
	public static void setUp() throws Exception {
		file = FileUtil.getFileFromClassPath("raft.pdf").toFile();
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

	@Test
	public void wholeFileDigest() {
		byte[] md5Digest = Md5Digest.getInstance().digest(file);
		byte[] sha256Digest = Sha256Digest.getInstance().digest(file);

		log.info("file is:{}", file);
		log.info("md5 digest result is: {}", Base64.getEncoder().encodeToString(md5Digest));
		log.info("sha256 digest result is: {}", Base64.getEncoder().encodeToString(sha256Digest));
	}

	@Test
	public void partFileDigest() {
		long offset = 1000;
		long length = 5000;
		log.info("file is:{}, offset is:{}, length is:{}", file, offset, length);

		byte[] md5Digest = Md5Digest.getInstance().digest(file, offset, length);
		byte[] sha256Digest = Sha256Digest.getInstance().digest(file, offset, length);

		log.info("md5 digest result is: {}", Base64.getEncoder().encodeToString(md5Digest));
		log.info("sha256 digest result is: {}", Base64.getEncoder().encodeToString(sha256Digest));
	}

	@Test
	public void digest2() {
	}
}