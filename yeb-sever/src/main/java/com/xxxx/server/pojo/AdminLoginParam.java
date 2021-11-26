package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: yeb
 * @description: 用户登录实体类
 * @author: 作者
 * @create: 2021-09-13 10:21
 */
 //lombok组件自动定义getter setter

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
  //swagger接口文档

@ApiModel(value="AdminLogin对象",description = "")
public class AdminLoginParam {
    @ApiModelProperty(value ="用户名",required = true)
    private String username;
    @ApiModelProperty(value ="密码",required = true)
    private String password;
    @ApiModelProperty(value ="验证码",required = true)
    private String code;

}
