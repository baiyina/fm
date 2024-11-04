package com.baiyina.fmrouterimpl.dao.mapper;

import com.baiyina.fmrouterimpl.dao.dos.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 15:22
 * @project: fm
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
