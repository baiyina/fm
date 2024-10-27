package com.baiyina.fmcommon.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 16:20
 * @project: fm
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FmException extends RuntimeException implements Serializable {
    Integer code;
    String message;
}
