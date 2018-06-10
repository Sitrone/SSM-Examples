package com.sununiq.scaffold.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: spring-mybatis-scaffold
 *
 * @description: 定时任务使用
 *
 * @author: sununiq
 *
 * @create: 2018-06-10 19:38
 **/
@Slf4j
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	// 固定频率执行
	@Scheduled(fixedRate = 1000 * 60)
	public void reportCurrentTime() {
		log.info("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
	}

	// 每1分钟执行一次
	@Scheduled(cron = "0 */1 *  * * * ")
	public void reportCurrentByCron() {
		log.info("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
	}

	private SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("HH:mm:ss");
	}

}
