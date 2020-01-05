package cn.dsc.springmvc.control;

import cn.dsc.springmvc.model.vo.ParamPerson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author dingshichen
 */
@RestController
public class ValidParamController {

    @PostMapping("/post1")
    public String post1(@RequestBody @Valid ParamPerson paramPerson){
        return "success";
    }
}
