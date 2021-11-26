package com.xxxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: yeb
 * @description:
 * @author: 作者
 * @create: 2021-09-13 10:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;
    //成功返回结果

    public  static  RespBean success(String message)
    {
        return  new RespBean(200,message,null);
    }
    public  static  RespBean success(String message,Object obj)
    {
        return  new RespBean(200,message,obj);
    }
     //失败返回结果

     public  static  RespBean error(String message)
     {
         return  new RespBean(500,message,null);
     }
     public  static  RespBean error(String message,Object obj)
    {
        return  new RespBean(500,message,obj);
    }
}
