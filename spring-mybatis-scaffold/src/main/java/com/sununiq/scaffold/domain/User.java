package com.sununiq.scaffold.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
	private Integer id;

	private String name;

	private Integer age;

	private String desc;
}
