package com.baiyina.fmrouterapi.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/1 18:07
 * @project: fm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private Long id;
    private String username;
}
