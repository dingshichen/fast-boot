package cn.dsc.shardingjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dingshichen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {

    private Integer id;

    private String code;

    private static final long serialVersionUID = 1L;
}