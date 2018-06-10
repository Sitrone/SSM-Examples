package com.sununiq.scaffold.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.stream.Stream;

@Aspect
@Component
public class LogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut(value = "@annotation(Log)")
	private void log() {}

	@Around(value = "log()")
	public Object processLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		Signature signature = joinPoint.getSignature();

		Class<?> clazz = target.getClass();
		String methodName = signature.getName();
		Method invokeMethod = Stream.of(clazz.getMethods())
				.filter(it -> it.getName().equals(methodName))
				.findAny()
				.orElse(null);

		if (invokeMethod != null) {
			Log log = invokeMethod.getAnnotation(Log.class);
			LOGGER.warn("Log###{}, {}, {}", System.currentTimeMillis(), log.object(), log.type());

			Object result = joinPoint.proceed();

			LOGGER.warn("Log###{}, {}, {}", System.currentTimeMillis(), log.object(), log.type());
			return result;
		}
		LOGGER.info("print");
		return null;
	}
}
