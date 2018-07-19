package io.sununiq.conf.util;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * 枚举转换类
 */
public class EnumConverter extends AbstractConverter {
    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        if(type.isEnum()) {
            T[] enumConstants = type.getEnumConstants();
            for (T t : enumConstants) {
                if(t.toString().equals(value)) {
                    return t;
                }
            }
        }
        throw conversionException(type, value);
    }

    @Override
    protected Class<?> getDefaultType() {
        return Enum.class;
    }
}
