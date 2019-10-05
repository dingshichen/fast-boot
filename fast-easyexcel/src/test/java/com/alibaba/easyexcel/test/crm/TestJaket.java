package com.alibaba.easyexcel.test.crm;

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
 * @since 2019/9/5
 */
@Slf4j
public class TestJaket {

	//生成sql模板
	private static final String UPDATE_COUPON_PWD = "update t_coupon_entity set use_password = %s where sys_company_id = 48 and sys_brand_id = 42 and send_type = 55 and coupon_code = %s;";

	private static final String SRC = "D:\\公司\\0905jacket券迁移\\最终版.xlsx";

	private static final String DESC = "C:\\Users\\dingshichen\\Desktop\\更新jacket券密码sql.txt";

	@Test
	public void test(){
		//源文件
		BufferedInputStream bi = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(SRC));
			EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

				List<String> sqls = new ArrayList<>();

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					String sql = String.format(UPDATE_COUPON_PWD, getListE(s, 9), getListE(s, 2));
					sqls.add(sql);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("sql组装完毕，一共{}条语句", sqls.size());
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(DESC));
						for (String sql : sqls) {
							bw.write(sql);
							bw.newLine();
						}
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
