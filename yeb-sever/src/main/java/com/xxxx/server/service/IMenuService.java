package com.xxxx.server.service;

import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
public interface IMenuService extends IService<Menu> {
    //根据用户id查询菜单列表

    List<Menu> getMenusByAdminId();
    List<Menu> getAllMenusWithRole();
}
