package cn.dsc.highlevel.client;

import cn.dsc.highlevel.HighLevelClientApplication;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighLevelClientApplication.class)
public class TElasticsearchIndex {

	@Autowired
	private ElasticSearchClientBean elasticSearchClientBean;

	private RestHighLevelClient client(){
		return this.elasticSearchClientBean.getClient();
	}

	/**
	 * 索引一个文档（同步）
	 * @throws IOException
	 */
	@Test
	public void syncIndex() throws IOException {
		IndexRequest request = new IndexRequest("emp");
		request.id("4");
		JSONObject emp = new JSONObject(true);
		emp.put("first_name", "ding");
		emp.put("last_name", "shichen");
		emp.put("age", 26);
		emp.put("about", "I love coding");
		emp.put("interests", Arrays.asList("computer", "book"));
		request.source(emp.toString(), XContentType.JSON);
		IndexResponse indexResponse = client().index(request, RequestOptions.DEFAULT);
		log.info("index response : " + indexResponse);
	}

	/**
	 * 索引一个文档（异步）
	 */
	@Test
	public void asynIndex(){
		IndexRequest request = new IndexRequest("emp");
		request.id("5");
		JSONObject emp = new JSONObject(true);
		emp.put("first_name", "song");
		emp.put("last_name", "hehe");
		emp.put("age", 23);
		emp.put("about", "I love html");
		emp.put("interests", Arrays.asList("computer", "book"));
		request.source(emp.toString(), XContentType.JSON);
		request.opType(DocWriteRequest.OpType.CREATE);
		client().indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
			@Override
			public void onResponse(IndexResponse indexResponse) {
				log.info("indexAsync response success : " + indexResponse);
			}

			@Override
			public void onFailure(Exception e) {
				log.error(e.getMessage(), e);
			}
		});
		//等下回调
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量索引（同步）
	 */
	@Test
	public void batchSyncIndex() throws IOException {
		IndexRequest request1 = new IndexRequest("emp");
		request1.id("6");
		JSONObject emp1 = new JSONObject(true);
		emp1.put("first_name", "Like");
		emp1.put("last_name", "Veryter");
		emp1.put("age", 22);
		emp1.put("about", "I like to collect rock albums");
		emp1.put("interests", Arrays.asList("sports", "forestry"));
		request1.source(emp1.toString(), XContentType.JSON);

		IndexRequest request2 = new IndexRequest("emp");
		request2.id("7");
		JSONObject emp2 = new JSONObject(true);
		emp2.put("first_name", "Grave");
		emp2.put("last_name", "Sun");
		emp2.put("age", 31);
		emp2.put("about", "I like to build cabinets");
		emp2.put("interests", "forestry");
		request2.source(emp2.toString(), XContentType.JSON);

		IndexRequest request3 = new IndexRequest("emp");
		request3.id("8");
		JSONObject emp3 = new JSONObject(true);
		emp3.put("first_name", "Pudoli");
		emp3.put("last_name", "Merterr");
		emp3.put("age", 30);
		emp3.put("about", "I love coding");
		emp3.put("interests", "book");
		request3.source(emp3.toString(), XContentType.JSON);

		BulkRequest requests = new BulkRequest();
		requests.add(request1, request2, request3);
		BulkResponse responses = client().bulk(requests, RequestOptions.DEFAULT);
		log.info("batch bulk result code : {}, status : {}", responses.status().getStatus(), responses.status().name());
	}


}
