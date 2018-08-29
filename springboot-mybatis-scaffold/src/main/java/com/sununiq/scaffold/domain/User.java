package com.sununiq.scaffold.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * spring-web-starter 已经集成hibernate-validator校验框架
 * sprintboot默认的国际化文件名为：
 * ValidationMessages.properties
 * ValidationMessages_en.properties
 * 需要配置在resources目录下面
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@ApiModelProperty(value = "主键", hidden = true)
	private Integer id;

	@ApiModelProperty(value = "名字, 长度5-20个字符" )
	@NotEmpty
	@Length(min = 5, max = 20, message = "{user.name.length.constraint}")
	private String name;

	@ApiModelProperty(value = "年龄, 最小1，最大99")
	@NotNull
	@Min(value = 1, message = "{user.age.constraint}")
	@Max(value = 99, message = "{user.age.constraint}")
	private Integer age;

	@ApiModelProperty(value = "描述, 最大1024")
	@Length(max = 1024, message = "{user.desc.length.constraint}")
	private String desc;
}
