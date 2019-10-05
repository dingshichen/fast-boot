package cn.dsc.highlevel.client.adocument;

import cn.dsc.highlevel.HighLevelClientApplication;
import cn.dsc.highlevel.conf.service.ElasticSearchClientBean;
import cn.dsc.highlevel.mapper.ADocumentMapper;
import cn.dsc.highlevel.model.vo.ADocument;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dingShiChen
 * @since 2019/9/25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HighLevelClientApplication.class)
public class TAVindex {

	@Autowired
	private ADocumentMapper aDocumentMapper;

	@Autowired
	private ElasticSearchClientBean elasticSearchClientBean;


	@Test
	public void batchIndex() throws IOException {
		BulkRequest requests = new BulkRequest();
		List<ADocument> aDocuments = aDocumentMapper.selectList(Wrappers.lambdaQuery());
		for (int i = 0; i < aDocuments.size(); i++) {
			IndexRequest request = new IndexRequest("av");
			request.id(String.valueOf(i + 1));
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("id", aDocuments.get(i).getId());
			map.put("createdate", aDocuments.get(i).getCreateDate());
			map.put("title", aDocuments.get(i).getTitle());
			map.put("designation", aDocuments.get(i).getDesignation());
			map.put("plot", aDocuments.get(i).getPlot());
			map.put("image", aDocuments.get(i).getImage());
			request.source(map);
			requests.add(request);
		}
		BulkResponse response = elasticSearchClientBean.getClient().bulk(requests, RequestOptions.DEFAULT);
		log.info("batch bulk result code : {}, status : {}", response.status().getStatus(), response.status().name());
	}
}
