package cn.dsc.oss.control;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author dingshichen
 */
@RequestMapping("/oss")
@RestController
@RequiredArgsConstructor
public class OssController {

    private final OSS ossClient;

    @Value("${bocket-name}")
    private String bucketName;

    private static final String FILE_KEY = "zheshiwenjian-key";

    @GetMapping("/upload")
    public String upload() {
        try {
            ossClient.putObject(bucketName, FILE_KEY, this
                    .getClass().getClassLoader().getResourceAsStream("oss-test.json"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "upload fail: " + e.getMessage();
        }
        return "upload success";
    }


    @GetMapping("/download")
    public String download() {
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, FILE_KEY);
            return "download success, content: " + IOUtils
                    .readStreamAsString(ossObject.getObjectContent(), CharEncoding.UTF_8);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "download fail: " + e.getMessage();
        }
    }

}
