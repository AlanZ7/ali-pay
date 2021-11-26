package com.xxxx.server.controller;


import com.xxxx.server.pojo.Position;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-09-02
 */
//restful风格的api接口
//api方式get put post delete


@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private IPositionService PositionService;
    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return PositionService.list();
    }

    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(PositionService.save(position)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){

        if(PositionService.updateById(position)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){

        if(PositionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @ApiOperation(value = "批量删除职位职位")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){

        if(PositionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }
}
