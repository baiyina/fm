package com.baiyina.fmrouterimpl.router.hash.impl;

import com.baiyina.fmrouterimpl.router.RouterHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.TreeMap;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/8 12:20
 * @project: fm
 */
public class ConsistentHashRouterHandlerImpl implements RouterHandler{
    private final TreeMap<Long, String> treeMap = new TreeMap<>();
    private final int VIRTUAL_NODE_NUM;

    public ConsistentHashRouterHandlerImpl(int virtualNodeNum) {
        VIRTUAL_NODE_NUM = virtualNodeNum;
    }

    private String getFirstNode(String key) {
        Long hash = hash(key);
        String nextValue = treeMap.ceilingEntry(hash).getValue();
        if (nextValue == null) {
            nextValue = treeMap.firstEntry().getValue();
        }
        return nextValue;
    }

    @Override
    public String route(List<String> values, String key) {
        treeMap.clear();
        for (String value : values) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = value + "vir" + i;
                Long hash = hash(virtualNodeName);
                treeMap.put(hash, value);
            }
            treeMap.put(hash(value), value);
        }

        return getFirstNode(key);
    }

    private Long hash(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(keyBytes);
        byte[] digest = md5.digest();

        // hash code, Truncate to 32-bits
        long hashCode = ((long) (digest[3] & 0xFF) << 24)
                | ((long) (digest[2] & 0xFF) << 16)
                | ((long) (digest[1] & 0xFF) << 8)
                | (digest[0] & 0xFF);

        return hashCode & 0xffffffffL;
    }

}
