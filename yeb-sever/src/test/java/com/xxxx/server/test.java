package com.xxxx.server;

import java.util.HashMap;

/**
 * @program: yeb
 * @description:
 * @author: 作者
 * @create: 2021-11-19 18:16
 */
public class test {
    public static void main(String[] args) {
        // 创建一个 HashMap
        HashMap<Integer, Integer> primeNumbers = new HashMap<>();

        // 往HasMap中添加映射
        primeNumbers.put(1, 2);
        primeNumbers.put(2, 3);
        primeNumbers.put(3, 5);
        System.out.println("HashMap: " + primeNumbers);

        // 得到value
        int value = primeNumbers.get(3);
        System.out.println("key Three 对应的 value: " + value);
    }
}
