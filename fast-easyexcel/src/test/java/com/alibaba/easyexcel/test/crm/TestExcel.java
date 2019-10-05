package com.alibaba.easyexcel.test.crm;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestExcel {


	@Test
	public void test01(){
//源文件
		String file = "D:\\公司\\0910例外迁移券数据\\无标题.xlsx";
		InputStream in = null;
		try {
			in = new FileInputStream(new File(file));
			EasyExcelFactory.readBySax(in, new Sheet(1, 1), new AnalysisEventListener(){

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					Object o1 = s.get(0);
					if (o1 == null) {
						log.info("有空 : {}", analysisContext.getCurrentRowNum());
					}
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("success");
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
