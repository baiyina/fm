package com.baiyina.fmrouterimpl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baiyina.fmrouterapi.user.vo.*;
import com.baiyina.fmrouterimpl.constant.CachePrefix;
import com.baiyina.fmrouterimpl.dao.cache.UserCacheMapper;
import com.baiyina.fmrouterimpl.dao.dos.UserDO;
import com.baiyina.fmrouterimpl.dao.mapper.UserMapper;
import com.baiyina.fmrouterimpl.enums.RouterExceptionEnum;
import com.baiyina.fmrouterimpl.service.UserService;
import com.baiyina.fmrouterimpl.utils.RouterExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 14:15
 * @project: fm
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserCacheMapper userCacheMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public UserRegisterResVO register(UserRegisterReqVO reqVo) {
        // 1. 参数校验
        if (reqVo == null || StrUtil.isBlank(reqVo.getUsername()) || StrUtil.isBlank(reqVo.getPassword())) {
            RouterExceptionUtil.handlerException(RouterExceptionEnum.ROUTER_REGISTER_INVALID_INPUT_EXCEPTION);
        }

        UserDO user = getUserByUsername(reqVo.getUsername());
        if (user != null) {
            RouterExceptionUtil.handlerException(RouterExceptionEnum.ROUTER_REGISTER_NAME_DUPLICATION_EXCEPTION);
        }

        user = new UserDO();
        // 3. 创建用户对象并保存到数据库
        user.setUsername(reqVo.getUsername());
        user.setPassword(reqVo.getPassword());

        userMapper.insert(user);

        // 4. 构建响应对象
        UserRegisterResVO resVo = new UserRegisterResVO();
        resVo.setUserId(user.getId());
        resVo.setToken(user.getPassword());

        log.info("register user: {}", user);

        return resVo;
    }

    @Override
    public UserLoginResVO login(UserLoginReqVO reqVo) {
        // 参数校验
        if (reqVo == null || StrUtil.isBlank(reqVo.getUsername()) || StrUtil.isBlank(reqVo.getPassword())) {
            RouterExceptionUtil.handlerException(RouterExceptionEnum.ROUTER_LOGIN_INVALID_INPUT_EXCEPTION);
        }
        UserDO user = getUserByUsername(reqVo.getUsername());
        if (user == null){
            RouterExceptionUtil.handlerException(RouterExceptionEnum.ROUTER_LOGIN_USER_NOT_EXIST_EXCEPTION);
        } else if (!user.getPassword().equals(reqVo.getPassword())){
            RouterExceptionUtil.handlerException(RouterExceptionEnum.ROUTER_LOGIN_PASSWORD_ERROR_EXCEPTION);
        }

        userCacheMapper.addOnlineUser(user.getId(), user.getUsername());

        UserLoginResVO resVo = new UserLoginResVO();
        resVo.setUserId(user.getId());
        resVo.setExpireTime(LocalDateTime.now());

        log.info("login user: {}", user);
        return resVo;
    }

    @Override
    public List<UserVO> getOnlineUserList() {
        List<String> onlineUserIds = userCacheMapper.selectOnlineUserList();
        if (onlineUserIds == null || onlineUserIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> userIds = new ArrayList<>();
        for (String onlineUserId : onlineUserIds) {
            try {
                Long userId = Long.valueOf(onlineUserId);
                userIds.add(userId);
            } catch (NumberFormatException e) {
                // 记录日志或处理异常
                log.warn("Invalid user ID: " + onlineUserId, e);
            }
        }

        if (userIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserDO> users = getUserList(userIds);
        List<UserVO> userVOList = new ArrayList<>();
        for (UserDO user : users) {
            if (user != null) {
                UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
                userVOList.add(userVO);
            }
        }

        return userVOList;
    }

    private List<UserDO> getUserList(List<Long> userIds) {
        List<UserDO> users = new ArrayList<>();
        for (Long userId : userIds) {
            UserDO user = userCacheMapper.selectUserById(userId);
            users.add(user);
        }
        return users;
    }

    private UserDO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }

}
