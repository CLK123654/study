package com.hae.basis;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestMap {
    Map<String, String> hashMap = new HashMap<>();
    Map<String, String> treeMap = new TreeMap<>();

    @Test
    public void testHashMap() {
        hashMap.put("b", "li4");
        hashMap.put("g", "wang5");
        hashMap.put("a", "zhang3");
        System.out.println(hashMap);
    }

    /**
     * treeMap 自身实现了排序
     */
    @Test
    public void testTreeMap() {
        treeMap.put("b", "li4");
        treeMap.put("g", "wang5");
        treeMap.put("a", "zhang3");
        System.out.println(treeMap);
    }
}
