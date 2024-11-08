package com.baiyina.fmrouterimpl.dao.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/7 18:29
 * @project: fm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushServerDO {
    private Long id;
    private String serverName;
    private String serverIp;
    private Integer serverPort;
}
