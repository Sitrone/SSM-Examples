package com.sununiq.cryptor.digest;

public class Sha256Digest extends BaseDigest{
	private static Sha256Digest ourInstance = new Sha256Digest();

	public static Sha256Digest getInstance() {
		return ourInstance;
	}

	private Sha256Digest() {
	}

	@Override
	public String getDigestAlgs() {
		return "SHA-256";
	}
}
