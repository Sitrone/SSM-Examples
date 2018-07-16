package com.sununiq.cryptor.util;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 解码工具类, 支持hex，String，base64
 */
public interface Decoder {

	Decoder HEX = Hex::decode;

	Decoder RAW = data -> data.getBytes(StandardCharsets.UTF_8);

	Decoder base64 = data -> Base64.getDecoder().decode(data);

	byte[] decode(String data);
}
