package com.baiyina.fmrouterimpl.dao.cache;

import com.baiyina.fmrouterimpl.constant.CachePrefix;
import com.baiyina.fmrouterimpl.dao.dos.UserDO;
import com.baiyina.fmrouterimpl.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/1 13:52
 * @project: fm
 */
@Slf4j
@Component
public class UserCacheMapper {
    private RedisTemplate<String, String> redisTemplate;
    private UserMapper userMapper;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addOnlineUser(Long userId, String userName) {
        redisTemplate.opsForSet().add(CachePrefix.USER_ONLINE_CACHE_PREFIX
                , userId.toString());
    }

    public List<String> selectOnlineUserList() {
        Set<String> members = redisTemplate.opsForSet().members(CachePrefix.USER_ONLINE_CACHE_PREFIX);
        if (members == null) {
            return Collections.emptyList();
        }
        List<String> userVOList = new ArrayList<>(members.size());
        for (Object member : members) {
            if (member instanceof String) {
                userVOList.add((String) member);
            } else {
                // 可以选择记录日志或抛出异常
                log.warn("Unexpected type in Redis set: {}", member.getClass());
            }
        }
        return userVOList;
    }

    @Cacheable(value = {CachePrefix.USER_CACHE_PREFIX}, key = "#id")
    public UserDO selectUserById(Long id) {
        return userMapper.selectById(id);
    }

}
