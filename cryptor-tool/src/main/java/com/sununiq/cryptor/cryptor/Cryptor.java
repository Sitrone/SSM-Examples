package com.sununiq.cryptor.cryptor;

/**
 * @program: cryptor-tool
 *
 * @description: 加解密接口
 *
 * @author: sununiq
 *
 * @create: 2018-07-14 13:55
 **/
public interface Cryptor {

	byte[] encrypt(byte[] plainText);

	byte[] decrypt(byte[] encryptedText);
}
