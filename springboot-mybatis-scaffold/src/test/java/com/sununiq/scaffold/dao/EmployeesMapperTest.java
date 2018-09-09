package com.sununiq.scaffold.dao;

import com.sununiq.scaffold.domain.Employees;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * dao test
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeesMapperTest {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Test
	public void selectByPrimaryKey() {
		int employeeNo = 10001;
		Employees employees = employeesMapper.selectByPrimaryKey(employeeNo);
		log.info(employees.toString());
	}
}