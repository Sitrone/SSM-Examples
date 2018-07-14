package com.sununiq.scaffold.domain;

import com.sununiq.scaffold.common.mybatis.BaseIntEnum;

public enum GenderIntEnum implements BaseIntEnum {
	MAIL(1), FEMAIL(2);

	private int code;

	GenderIntEnum(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return this.code;
	}
}
