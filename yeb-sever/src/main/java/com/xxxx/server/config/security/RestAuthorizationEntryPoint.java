package com.xxxx.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: yeb
 *当未登录(没有token)或者登录失效  自定义返回结果
 * @author: 作者
 * @create: 2021-09-13 15:49
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json");
      PrintWriter out=response.getWriter();
      RespBean  bean=RespBean.error("未登录或者用户信息过期:请重新登录");
      bean.setCode(401);
      out.write(new ObjectMapper().writeValueAsString(bean));
      out.flush();
      out.close();
    }
}
