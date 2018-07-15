package com.sununiq.cryptor.digest;

import java.io.File;

public interface Digest {

	/**
	 * 计算内存中数据的摘要
	 */
	byte[] digest(byte[] data);

	/**
	 * 计算文件的整个摘要
	 * @param file 文件
	 * @return 摘要值
	 */
	byte[] digest(File file);

	/**
	 * 计算文件的分段摘要
	 * @param file 文件
	 * @param offset 起始便宜位置
	 * @param length 长度
	 * @return 摘要值
	 */
	byte[] digest(File file, long offset, long length);
}
