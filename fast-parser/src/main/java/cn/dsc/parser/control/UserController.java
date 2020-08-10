package cn.dsc.parser.control;

import cn.dsc.parser.dto.PagingBody;
import cn.dsc.parser.dto.Result;
import cn.dsc.parser.dto.UserCO;
import cn.dsc.parser.dto.UserGetCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 * @description 用户相关的接口
 * @author dingshichen
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户查询
     * @description 根据名称查询用户详情
     * @author zhukongming
     * @param cmd
     * @return
     */
    @PostMapping("/getByName")
    public Result<PagingBody<UserCO>> getByName(@RequestBody UserGetCommand cmd, HttpServletRequest request) {
        UserCO co = new UserCO();
        co.setAge(10);

        Result<UserCO> result = new Result<>();
        result.setData(co);
        return null;
    }
}
