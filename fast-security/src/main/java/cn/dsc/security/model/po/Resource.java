package cn.dsc.security.model.po;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {
    /**
    * 主键自增
    */
    private Long id;

    /**
    * 数据有效性（1有效，0无效）
    */
    private Boolean valid;

    /**
    * 资源名称
    */
    private String name;

    /**
    * 资源地址
    */
    private String url;

    /**
    * 请求方式
    */
    private String method;

    private static final long serialVersionUID = 1L;
}