package com.sununiq.cryptor.digest;

public class Md5Digest extends BaseDigest{
	private static Md5Digest ourInstance = new Md5Digest();

	public static Md5Digest getInstance() {
		return ourInstance;
	}

	private Md5Digest() {
	}

	@Override
	public String getDigestAlgs() {
		return "MD5";
	}
}
