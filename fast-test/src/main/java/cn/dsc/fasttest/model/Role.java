package cn.dsc.fasttest.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    /**
     * 主键自增
     */
    private Long id;

    /**
     * 数据有效性（1有效，0无效）
     */
    private Boolean valid;

    /**
     * 角色名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }
}