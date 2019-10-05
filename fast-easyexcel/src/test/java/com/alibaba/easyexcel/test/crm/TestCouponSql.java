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
 * @since 2019/8/27
 */
@Slf4j
public class TestCouponSql {

	//生成sql模板
	private static final String UPDATE_COUPON_PWD = "update t_coupon_entity set coupon_status = 30, is_use = 1, use_time = '%s' where coupon_code = '%s' and sys_company_id = 37 and sys_brand_id = 30;";

	@Test
	public void test(){
		//源文件
		String file = "D:\\公司\\0827红蜻蜓导入券\\陕西红蜻S - 副本.xlsx";
		InputStream in = null;
		try {
			in = new FileInputStream(new File(file));
			EasyExcelFactory.readBySax(in, new Sheet(1, 1), new AnalysisEventListener(){

				List<String> sqls = new ArrayList<>();

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					String sql = String.format(UPDATE_COUPON_PWD, s.get(6), s.get(2));
					sqls.add(sql);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("sql组装完毕，一共{}条语句", sqls.size());
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(new File("D:\\公司\\0827红蜻蜓导入券\\0827更新核销sql.txt")));
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
