package com.alibaba.easyexcel.test.crm;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dingShiChen
 * @since 2019/9/5
 */
@Slf4j
public class TestReadCouponExcel {

	private static final String SRC = "D:\\公司\\0905jacket券迁移\\最终版.xlsx";

	@Test
	public void getSql(){
		//源文件
		BufferedInputStream bi = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(SRC));
			EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					log.info(s.toString());
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {

				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bi != null) {
				try {
					bi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
