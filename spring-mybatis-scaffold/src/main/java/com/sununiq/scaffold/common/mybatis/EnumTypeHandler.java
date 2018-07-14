package com.sununiq.scaffold.common.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: 自定义mybatis枚举注解
 *
 * @author: sununiq
 *
 * @create: 2018-07-14 13:35
 **/
public class EnumTypeHandler<E extends Enum<?> & BaseIntEnum> extends BaseTypeHandler<BaseIntEnum> {
	private Class<E> type;

	public EnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BaseIntEnum parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getCode());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int code = rs.getInt(columnName);
		return rs.wasNull() ? null : codeOf(code);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int code = rs.getInt(columnIndex);
		return rs.wasNull() ? null : codeOf(code);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int code = cs.getInt(columnIndex);
		return cs.wasNull() ? null : codeOf(code);
	}

	private E codeOf(int code) {
		try {
			return codeOf(type, code);
		} catch (Exception ex) {
			throw new IllegalArgumentException(
					"Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
		}
	}

	private static <E extends Enum<?> & BaseIntEnum> E codeOf(Class<E> enumClass, int code) {
		for (E e : enumClass.getEnumConstants()) {
			if (e.getCode() == code)
				return e;
		}
		throw new IllegalArgumentException("Failed to find enum value, code is:" + code);
	}
}
