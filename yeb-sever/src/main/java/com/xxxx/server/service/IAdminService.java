package com.xxxx.server.service;

import com.xxxx.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
public interface IAdminService extends IService<Admin> {


    /*
   登录之后返回token
    */



    RespBean login(String username, String password, String code, HttpServletRequest request);

    /*
    根据用户名获取用户
     */

    Admin getAdminByUserName(String username);

    List<Role> getRoles(Integer adminId);
}
