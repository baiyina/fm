package com.baiyina.fmrouterimpl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baiyina.fmrouterapi.user.vo.UserLoginReqVO;
import com.baiyina.fmrouterapi.user.vo.UserLoginResVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterReqVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterResVO;
import com.baiyina.fmrouterimpl.dao.dos.UserDO;
import com.baiyina.fmrouterimpl.dao.mapper.UserMapper;
import com.baiyina.fmrouterimpl.enums.RouterExceptionEnum;
import com.baiyina.fmrouterimpl.service.UserService;
import com.baiyina.fmrouterimpl.utils.RouterExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 14:15
 * @project: fm
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService{
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
        save(user);

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
        UserLoginResVO resVo = new UserLoginResVO();
        resVo.setUserId(user.getId());
        resVo.setExpireTime(LocalDateTime.now());

        log.info("login user: {}", user);
        return resVo;
    }

    private UserDO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getUsername, username);
        return getOne(queryWrapper);
    }
}
