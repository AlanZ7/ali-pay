package com.xxxx.server.service.impl;


import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    //根据用户id查询菜单列表
    @Override
    public List<Menu> getMenusByAdminId() {
        //用 security 自带的方法，来获取当前登录用户的基本信息，其中就包括ID
        Integer adminId = ((Admin) SecurityContextHolder
                .getContext()//获取登录用户全局上下文
                .getAuthentication()//获取验证后的信息
                .getPrincipal())//权限验证
                .getId();
        ValueOperations<String,Object> valueOperations=redisTemplate.opsForValue();
        //查询缓存中是否由数据
        List<Menu> menus=(List<Menu>) valueOperations.get("menu_"+adminId);
        if(CollectionUtils.isEmpty(menus)){
            //如果没数据，数据库中查询，并设置到缓存中
            menus=menuMapper.getMenusByAdminId(adminId);
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }


}
