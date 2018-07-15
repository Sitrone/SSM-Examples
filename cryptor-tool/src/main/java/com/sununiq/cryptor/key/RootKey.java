package com.sununiq.cryptor.key;

import com.sununiq.cryptor.CryptorException;
import com.sununiq.cryptor.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.util.Objects;

/**
 * @program: cryptor-tool
 *
 * @description: 根密钥合成算法，分三段，代码一段，配置文件两段，使用时合成通过pbkdf2算法导出
 *
 * @author: sununiq
 *
 * @create: 2018-07-15 11:02
 **/
@Getter
@Setter
@ToString
public class RootKey {
	private String part1;
	private String part2;
	private String part3;
	private String salt;

	public static byte[] generate(RootKey rootKey) {
		Objects.requireNonNull(rootKey);

		byte[] part1 = Hex.decode(rootKey.getPart1());
		byte[] part2 = Hex.decode(rootKey.getPart2());
		byte[] part3 = Hex.decode(rootKey.getPart3());

		if (part1.length != part2.length || part1.length != part3.length) {
			throw new CryptorException("Input rootkey is illegal.");
		}

		byte[] password = new byte[16];
		for (int i = 0; i < 16; i++) {
			password[i] = (byte) (part1[i] ^ part2[i] ^ part3[i]);
		}

		return generate(password, Hex.decode(rootKey.getSalt()), 16, 10000);
	}


	private static byte[] generate(byte[] password, byte[] salt, int keyLength, int iterations) {
		PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();
		generator.init(password, salt, iterations);
		return ((KeyParameter) generator.generateDerivedParameters(keyLength * 8)).getKey();
	}

}
