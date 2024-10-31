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
    private String msg;
    private Long senderId;
    private Integer type;
    private List<Long> receiverIds;
}
