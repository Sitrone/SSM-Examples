package com.sununiq.scaffold.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "名字")
	private String name;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "描述")
	private String desc;
}
