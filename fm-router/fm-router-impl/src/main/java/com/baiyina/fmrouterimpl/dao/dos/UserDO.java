package com.baiyina.fmrouterimpl.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 15:20
 * @project: fm
 */
@Data
@TableName("user")
public class UserDO {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createTime;
}
