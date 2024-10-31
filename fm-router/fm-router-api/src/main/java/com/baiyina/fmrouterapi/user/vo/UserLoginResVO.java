package com.baiyina.fmrouterapi.user.vo;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/25 17:51
 * @project: fm
 */
@Data
public class UserLoginResVO implements Serializable {
    private Long userId;
    private LocalDateTime expireTime;
}
