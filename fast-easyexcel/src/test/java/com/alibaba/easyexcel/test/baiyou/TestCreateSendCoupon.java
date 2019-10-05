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
public class TestCreateSendCoupon {


	private static final String INSERT_SQL = "insert into wx_sendcoupon (wx_public_id, wx_coupon_id, VIPS_SQL, ISSUBMIT, ERRCOUNT, AD_CLIENT_ID, CREATEID, MODIFYID, TOTALCOUNT, NUMBER) values (1213, 99999, '%s', 'N', 0,  237, 1, 1, %s, 1);";

	private static final String XML_SQL = "<filter><desc>包含(很多人)</desc><sql>IN(SELECT WX_VIP.ID FROM WX_VIP WX_VIP WHERE ((WX_VIP.AD_CLIENT_ID=237)) AND ((WX_VIP.ID IN(%s))) AND (WX_VIP.WX_PUBLIC_ID=1213))</sql></filter>";

	private static final String SRC = "D:\\公司\\0906婵之云查询会员发券\\婵之云会员信息.xlsx";

	private static final String DESC = "C:\\Users\\dingshichen\\Desktop\\婵之云发券sql.txt";


	@Test
	public void test(){
		//源文件
		BufferedInputStream bi = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(SRC));
			EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

				String ids = "";
				int count = 0;

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					ids = ids + getListE(s, 0) + ", ";
					count ++;
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("sql组装完毕");
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter(DESC));
						bw.write(String.format(INSERT_SQL, String.format(XML_SQL, ids), String.valueOf(count)));
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
			return list.get(index);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}



}
