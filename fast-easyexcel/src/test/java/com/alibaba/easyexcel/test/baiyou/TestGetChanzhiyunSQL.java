package com.alibaba.easyexcel.test.baiyou;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/9/6
 */
@Slf4j
public class TestGetChanzhiyunSQL {


	//生成sql模板
	private static final String SELECT_VIP = "select * from wx_vip where wx_public_id = 1213 and phone_number in (";

	private static final String SRC = "D:\\公司\\0906婵之云查询会员发券\\婵之云会员信息.xlsx";

	private static final String DESC = "C:\\Users\\dingshichen\\Desktop\\婵之云会员信息sql.txt";

	@Test
	public void test(){
		//源文件
		BufferedInputStream bi = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(SRC));
			EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

				String phoneNumbers = "";

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					phoneNumbers = phoneNumbers + getListE(s, 3) + ", ";
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("sql组装完毕");
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(DESC));
						bw.write(SELECT_VIP + phoneNumbers + ");");
						bw.newLine();
						bw.flush();
						log.info("sql脚本生成完毕");
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (bw != null) {
							try {
								bw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
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


	private Object getListE(List list, int index){
		try {
			return "'" + list.get(index) + "'";
		} catch (IndexOutOfBoundsException e) {
			return "''";
		}
	}
}
