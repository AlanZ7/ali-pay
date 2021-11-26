package com.xxxx.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.config.security.JwtTokenUtil;
import com.xxxx.server.mapper.RoleMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
     private  AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    //登录之后返回token
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha=(String) request.getSession().getAttribute("captcha");
//        System.out.println(captcha);
//        System.out.println(username);
//        System.out.println(code);
//        System.out.println(StringUtils.isEmpty(code));
//        System.out.println(!captcha.equalsIgnoreCase(code));
        //忽略大小写
        if(StringUtils.isEmpty(code)|| !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入");
        }
        //登录
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码错误");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用,请联系管理员");
        }
        //更新security登录用户对象 获取权限放在全局 表示已登录
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,
                null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenhead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }
     //根据用户名获取用户,登陆后的操作获取用户信息
     //开始时securityconfig类Userdetailservice通过该方法获取用户信息
    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin= adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username)
        .eq("enabled",true));

         //  System.out.println(admin.getPassword());

        return admin;
    }

    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }
    //根据用户id获取菜单列表
//    @Override
//    public List<Menu> getMenusByAdminId() {
//
//
//        return adminMapper.getMenusByAdminId(((Admin)SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal())
//                .getId());
//    }
}
