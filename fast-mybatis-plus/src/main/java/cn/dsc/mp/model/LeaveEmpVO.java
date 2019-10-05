package cn.dsc.mp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/9/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveEmpVO {


	private Long id;

	private String empName;

	private String empPhone;

	private Date createDate;
}
