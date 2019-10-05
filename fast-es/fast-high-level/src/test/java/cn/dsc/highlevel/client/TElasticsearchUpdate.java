package cn.dsc.highlevel.client;

import cn.dsc.highlevel.HighLevelClientApplication;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * @author dingShiChen
 * @since 2019/9/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighLevelClientApplication.class)
public class TElasticsearchUpdate {

	@Autowired
	private ElasticSearchClientBean elasticSearchClientBean;

	private RestHighLevelClient client(){
		return this.elasticSearchClientBean.getClient();
	}

	/**
	 * 根据_id，存在即更新
	 * @throws IOException
	 */
	@Test
	public void updateByIndex() throws IOException {
		IndexRequest request = new IndexRequest("emp");
		request.id("4");
		JSONObject emp = new JSONObject(true);
		emp.put("first_name", "Ding");
		emp.put("last_name", "Shichen");
		emp.put("age", 26);
		emp.put("about", "I love coding");
		emp.put("interests", Arrays.asList("computer", "book"));
		request.source(emp.toString(), XContentType.JSON);
		IndexResponse indexResponse = client().index(request, RequestOptions.DEFAULT);
		log.info("index response : " + indexResponse);
	}

	/**
	 * 更新使用脚本
	 */
	@Test
	public void updateByUpdateScript() throws IOException {
		UpdateRequest request = new UpdateRequest("emp", "5");
		Map<String, Object> params = singletonMap("age", 3);
		Script inline = new Script(ScriptType.INLINE, "painless",
				"ctx._source.age += params.age", params);
		request.script(inline);
		UpdateResponse response = client().update(request, RequestOptions.DEFAULT);
		log.info("update by script response : {}", response);
	}

	/**
	 * 更新合并部分文档
	 */
	@Test
	public void updatePartialDocument() throws IOException {
		UpdateRequest request = new UpdateRequest("emp", "5");
		JSONObject document = new JSONObject();
		document.put("first_name", "Song");
		document.put("last_name", "Hehe");
		request.doc(document.toString(), XContentType.JSON);
		UpdateResponse response = client().update(request, RequestOptions.DEFAULT);
		log.info("update partial json document response : {}", response);
	}
}
