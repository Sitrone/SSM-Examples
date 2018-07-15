package com.sununiq.cryptor.digest;

import com.sununiq.cryptor.CryptorException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: cryptor-tool
 *
 * @description: hamcsha256 签名算法
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 10:57
 **/
public abstract class HmacSha256 {

	private static final String hmacSHA256_Algs = "HmacSHA256";

	public static byte[] sha256(byte[] data, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, hmacSHA256_Algs);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			return mac.doFinal(data);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new CryptorException("Failed to cal hmacsha256.", e);
		}
	}
}
