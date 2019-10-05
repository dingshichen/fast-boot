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
 * t_cus_littlestar_good_card_entity 数据迁移
 * @author dingShiChen
 * @since 2019/9/2
 */
@Slf4j
public class TestLittleStarGoodCard {

	private static final String INSERT_GIFT_CARD_ENTITY = "insert into t_cus_littlestar_good_card_entity (sys_company_id, sys_brand_id, corp_code, card_no, order_time, eorder_no, order_id, order_id_online, consignee_name, consignee_province, consignee_city, consignee_area, consignee_address, consignee_phone, order_item, expired_date, create_date, modify_date, trace) values (23, 17, 'C10244', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, '来自于数据迁移');";

	private static final String SRC = "D:\\公司\\英式礼品卡php项目\\def_vip_goods_trade.xls";

	private static final String DESC = "D:\\公司\\英式礼品卡php项目\\good_card_entity.sql";

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
					String sql = String.format(INSERT_GIFT_CARD_ENTITY, getListE(s, 2), getListE(s,3), getListE(s,4), getListE(s,5), getListE(s, 19), getListE(s, 6), getListE(s, 7), getListE(s, 8), getListE(s, 9), getListE(s, 10), getListE(s, 11), getListE(s, 12), getListE(s, 13), getListE(s, 16), getListE(s, 17));
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
