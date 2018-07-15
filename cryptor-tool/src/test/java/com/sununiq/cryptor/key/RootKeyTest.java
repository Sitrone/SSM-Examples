package com.sununiq.cryptor.key;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.junit.BeforeClass;
import org.junit.Test;

@Slf4j
public class RootKeyTest {

	private static RootKey rootKey = new RootKey();

	@BeforeClass
	public static void setUp() throws Exception {
		rootKey.setPart1("61f401e515a1c63cde37acf0ccb37d00");
		rootKey.setPart2("aa5a1f8b566c0f7fc2ad339f9ff314e6");
		rootKey.setPart3("e9a86239fd90dc25cd37020b5829b327");
		rootKey.setSalt("a579df95c292d71c18fb7571d01a4056");
		log.info(rootKey.toString());
	}

	@Test
	public void generate() {
		byte[] rootKey = RootKey.generate(RootKeyTest.rootKey);
		log.info(Hex.toHexString(rootKey));
	}
}