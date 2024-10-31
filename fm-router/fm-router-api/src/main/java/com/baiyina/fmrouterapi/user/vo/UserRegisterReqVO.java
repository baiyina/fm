package com.baiyina.fmrouterapi.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 13:42
 * @project: fm
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterReqVO {
    @NotNull(message = "用户名不为空")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "username", example = "zhang san")
    private String username;

    @NotNull(message = "密码不为空")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "password", example = "123456")
    private String password;
}
