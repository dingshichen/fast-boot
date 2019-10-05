package com.alibaba.easyexcel.test.littlestar;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/9/28
 */
@Slf4j
public class UpdateBalance {


	private static final String URL = "https://miniapps.littlestarbrands.com/admin/pay/balance";

	private static RestTemplate restTemplate = new RestTemplate();

	@Test
	public void work(){
//源文件
		String file = "D:\\公司\\0928给英式储值卡刷数据\\线下礼品卡余额.xlsx";
		InputStream in = null;
		try {
			in = new FileInputStream(new File(file));
			EasyExcelFactory.readBySax(in, new Sheet(1, 1), new AnalysisEventListener(){

				List<Message> messages = new ArrayList<>();

				@Override
				public void invoke(Object o, AnalysisContext analysisContext) {
					ArrayList s = (ArrayList) o;
					messages.add(new Message((String) s.get(0), (String) s.get(1)));
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext analysisContext) {
					log.info("读取完成，第二条数据：" + messages.get(1));
					log.info("一共数据量：" + messages.size());
					post(messages);
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


	private void post(List<Message> list){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (int i = 0; i < list.size(); i++) {
			try {
				log.info("第" + (i + 1) + "次请求开始");
				HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(list.get(i)), headers);
				ResponseEntity<String> response = restTemplate.postForEntity(URL, requestEntity, String.class);
				log.info("返回结果：" + response.getBody());
			} catch (Exception e){
				log.error(e.getMessage(), e);
			}
		}
	}
}
