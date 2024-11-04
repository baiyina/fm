package com.baiyina.fmrouterapi.msg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 15:58
 * @project: fm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FmReqMsgVO {
    private Integer type;
    private Long senderId;
    private List<Long> receiverIds;
    private String msg;
}
