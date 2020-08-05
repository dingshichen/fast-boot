package cn.dsc.jApiDoc.control;

import cn.dsc.jApiDoc.dto.Result;
import cn.dsc.jApiDoc.dto.TestCO;
import cn.dsc.jApiDoc.dto.TestCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author dingshichen
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试请求入口
     * @param cmd
     * @return
     */
    @PostMapping("/co")
    public Result<TestCO> co(@RequestBody TestCommand cmd) {
        TestCO co = new TestCO();
        co.setAge(10);
        Result<TestCO> result = new Result<>();
        result.setData(co);
        return result;
    }
}
