package com.baiyina.fmrouterapi.user.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 13:43
 * @project: fm
 */
@Data
public class UserRegisterResVO implements Serializable {
    private Long userId;
    private String token;
}
