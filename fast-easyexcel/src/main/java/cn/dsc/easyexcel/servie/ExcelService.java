package cn.dsc.easyexcel.servie;

import java.io.InputStream;

/**
 * @author dingShiChen
 * @since 2019/8/24
 */
public interface ExcelService {

	void readExcel(InputStream inputStream);

	void readExcelModel(InputStream inputStream);
}
