package com.sununiq.cryptor.util;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 编码工具, 支持hex，String，base64
 */
public interface Encoder {

	Encoder HEX = Hex::toHexString;

	Encoder RAW = data -> new String(data, StandardCharsets.UTF_8);

	Encoder BASE64 = data -> Base64.getEncoder().encodeToString(data);

	String encode(byte[] data);
}
