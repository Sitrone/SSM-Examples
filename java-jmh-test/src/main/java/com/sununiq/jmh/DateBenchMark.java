package com.sununiq.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-jmh-test
 *
 * @description: 日期性能测试
 *
 * @author: sununiq
 *
 * @create: 2018-06-15 16:11
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class DateBenchMark {

	@Benchmark
	public Calendar runCalendar() {
		return Calendar.getInstance();
	}

	@Benchmark
	public LocalDate runJoda() {
		return LocalDate.now();
	}

	@Benchmark
	public long runSystem() {
		return System.currentTimeMillis();
	}


	public static void main(String[] args) throws RunnerException
	{
		Options opt = new OptionsBuilder()
				.include(DateBenchMark.class.getSimpleName())
				.build();

		new Runner(opt).run();
	}
}
