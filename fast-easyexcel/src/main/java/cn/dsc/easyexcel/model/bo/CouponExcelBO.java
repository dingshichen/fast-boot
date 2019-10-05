package cn.dsc.easyexcel.model.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @author dingShiChen
 * @since 2019/8/25
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponExcelBO extends BaseRowModel {

	@ExcelProperty(index = 0)
	private String erpid;
	@ExcelProperty(index = 1)
	private String quanCode;
	@ExcelProperty(index = 2)
	private String couponCode;
	@ExcelProperty(index = 3)
	private String startTime;
	@ExcelProperty(index = 4)
	private String endTime;
	@ExcelProperty(index = 5)
	private String isUse;
	@ExcelProperty(index = 6)
	private String useTime;
	@ExcelProperty(index = 7)
	private String type;
	@ExcelProperty(index = 8)
	private String busname;
}
