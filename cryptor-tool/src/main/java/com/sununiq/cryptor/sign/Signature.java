package com.sununiq.cryptor.sign;

public interface Signature {

	String sign(byte[] data);


	boolean verify(byte[] data, String expectData);
}
