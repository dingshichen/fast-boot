package cn.dsc.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dingShiChen
 * @since 2019/9/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveEmpInfo {

	private long id;
	private long empId;
	private Date createDate;
	private String empName;
	private String phone;

}
