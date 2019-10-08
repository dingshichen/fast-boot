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
 * @since 2019/10/8
 */
@Slf4j
public class TestUrEmpCoupon {

	private static final String INSERT_SQL_TEMP = "insert into t_coupon_entity (sys_company_id, sys_brand_id, business_name, coupon_code, coupon_definition_id, coupon_batch_send_record_id, member_code, coupon_name, money, discount, img, info, valid_date_start, valid_date_end, send_type, coupon_status, create_date, modified_date, preferential_type) select 50, 46, '手动导入100802', '%s', coupon_definition_id, coupon_definition_id, '%s', coupon_name, money, discount, img, info, valid_date_start, valid_date_end, '111', 20, now(), now(), preferential_type from t_coupon_definition where coupon_definition_id = 2333 and sys_company_id = 50 and sys_brand_id = 46;";

	private static final String SRC = "D:\\公司\\1008员工券迁移\\员工券迁移数据源1.xlsx";

	private static final String DESC = "D:\\公司\\1008员工券迁移\\员工券迁移sql脚本.txt";

	@Test
	public void test(){
		//源文件
		InputStream in = null;
		try {
			in = new FileInputStream(SRC);
			EasyExcelFactory.readBySax(in, new Sheet(1, 1), new AnalysisEventListener(){

				List<String> sqls = new ArrayList<>();

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					String sql = String.format(INSERT_SQL_TEMP, s.get(0), s.get(1));
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
