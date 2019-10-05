package cn.dsc.highlevel.client;

import cn.dsc.highlevel.HighLevelClientApplication;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author dingShiChen
 * @since 2019/9/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighLevelClientApplication.class)
public class TElasticsearchQuery {

	@Autowired
	private ElasticSearchClientBean elasticSearchClientBean;

	private RestHighLevelClient client(){
		return this.elasticSearchClientBean.getClient();
	}

	/**
	 * 根据_id查询，同步，也可以异步
	 * @throws IOException
	 */
	@Test
	public void queryById() throws IOException {
		GetRequest request = new GetRequest("emp", "4");
//		request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
		String[] includes = new String[]{"*"};
		String[] excludes = Strings.EMPTY_ARRAY;
		FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
		request.fetchSourceContext(fetchSourceContext);
		GetResponse getResponse = client().get(request, RequestOptions.DEFAULT);
		if (getResponse.isExists()) {
			log.info("get result index : {}, id : {}, version : {}", getResponse.getIndex(), getResponse.getId(), getResponse.getVersion());
			log.info("get result sourceAsString : {}", getResponse.getSourceAsString());
			JSONObject jo = JSON.parseObject(getResponse.getSourceAsString());
		} else {

		}
	}

	/**
	 * 根据_id查询是否存在
	 * @throws IOException
	 */
	@Test
	public void exists() throws IOException {
		GetRequest getRequest = new GetRequest("emp", "1");
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		boolean exists = client().exists(getRequest, RequestOptions.DEFAULT);
		log.info("document exists : {}", exists);
	}

	/**
	 * 多条件Search
	 */
	@Test
	public void queryBySearch() throws IOException {
		SearchRequest request = new SearchRequest("emp");
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.query(QueryBuilders.matchQuery("about", "rock"));
		builder.from(2).size(2);	//分页，from参数是文档索引
		request.source(builder);
		SearchResponse response = client().search(request, RequestOptions.DEFAULT);
		log.info(response.toString(), response.getTotalShards());
		for (SearchHit document : response.getHits()) {
			log.info("response : {}, code : {}, status : {}", document, response.status().getStatus(), response.status().name());
		}
	}
}
