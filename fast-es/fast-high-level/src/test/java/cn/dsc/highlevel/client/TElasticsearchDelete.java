package cn.dsc.highlevel.client;

import cn.dsc.highlevel.HighLevelClientApplication;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
public class TElasticsearchDelete {

	@Autowired
	private ElasticSearchClientBean elasticSearchClientBean;

	private RestHighLevelClient client(){
		return this.elasticSearchClientBean.getClient();
	}

	/**
	 * 删除文档，根据_id删除
	 * @throws IOException
	 */
	@Test
	public void delete() throws IOException {
		DeleteRequest request = new DeleteRequest("emp");
		request.id("8");
		DeleteResponse response = client().delete(request, RequestOptions.DEFAULT);
		log.info("delete response : {}", response);
	}
}
