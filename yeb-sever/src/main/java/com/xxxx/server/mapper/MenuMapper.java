package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
public interface MenuMapper extends BaseMapper<Menu> {
    //根据用户id获取菜单列表

    List<Menu> getMenusByAdminId(Integer id);
     //通过角色获取菜单列表

    List<Menu> getAllMenusWithRole();

}
