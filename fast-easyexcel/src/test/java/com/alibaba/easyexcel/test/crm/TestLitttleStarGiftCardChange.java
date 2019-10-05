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
 * t_cus_littlestar_gift_card_change 数据迁移
 * @author dingShiChen
 * @since 2019/9/2
 */
@Slf4j
public class TestLitttleStarGiftCardChange {


	private static final String INSERT_GIFT_CARD_ENTITY = "insert into t_cus_littlestar_gift_card_change (sys_company_id, sys_brand_id, corp_code, card_no, new_card_no, cause, create_date, trace) values (23, 17, 'C10244', %s, %s, %s, %s, '来自于数据迁移');";

	private static final String SRC = "D:\\公司\\英式礼品卡php项目\\def_vip_gift_card_change.xls";

	private static final String DESC = "D:\\公司\\英式礼品卡php项目\\gift_card_entity_change.sql";

	@Test
	public void getSql(){
		//源文件
		BufferedInputStream bi = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(SRC));
			EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

				List<String> sqls = new ArrayList<>();

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					String sql = String.format(INSERT_GIFT_CARD_ENTITY, getListE(s, 3), getListE(s,7), getListE(s,4), getListE(s,8));
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
