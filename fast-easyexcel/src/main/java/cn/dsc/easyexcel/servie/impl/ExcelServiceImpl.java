package cn.dsc.easyexcel.servie.impl;

import cn.dsc.easyexcel.model.bo.CouponExcelBO;
import cn.dsc.easyexcel.servie.ExcelService;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/8/24
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public void readExcel(InputStream inputStream) {
		try {
			ExcelReader reader = EasyExcelFactory.getReader(inputStream, new AnalysisEventListener() {

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					log.info("当前行号：{}", analysisContext.getCurrentRowNum());
					log.info("object : {}", o);		//object的类型是一个String数组，内容为excel里一行的数据
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("解析excel结束");
				}
			});
			reader.read();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void readExcelModel(InputStream inputStream) {
		try {
			List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(0, 1, CouponExcelBO.class));
			for (Object o : data) {
				log.info(o.toString());
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
