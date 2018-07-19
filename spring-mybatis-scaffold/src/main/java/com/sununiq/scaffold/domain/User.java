package com.sununiq.scaffold.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {
	private Integer id;

	private String name;

	private Integer age;

	private String desc;
}
