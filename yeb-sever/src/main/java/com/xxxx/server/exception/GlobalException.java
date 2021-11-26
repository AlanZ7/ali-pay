package com.xxxx.server.exception;

import com.xxxx.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @program: yeb
 * @description:
 * @author: 作者
 * @create: 2021-11-05 19:17
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySQLException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关数据，操作失败！");
        }
        return RespBean.error("数据库异常，操作失败！");
    }
}
