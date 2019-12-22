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
public class Permission implements Serializable {
    /**
    * 主键自增
    */
    private Long id;

    /**
    * 数据有效性（1有效，0无效）
    */
    private Boolean valid;

    /**
    * 角色ID
    */
    private Long roleId;

    /**
    * 资源ID
    */
    private Long resourceId;

    private static final long serialVersionUID = 1L;
}