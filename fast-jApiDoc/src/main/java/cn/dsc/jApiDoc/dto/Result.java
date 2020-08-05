package cn.dsc.jApiDoc.dto;

import lombok.Data;

/**
 * @author dingshichen
 */
@Data
public class Result<T> {

    /**
     * 状态码
     */
    private int code = 0;

    /**
     * 信息
     */
    private String message = "success";

    /**
     * 业务数据
     */
    private T data;
}
