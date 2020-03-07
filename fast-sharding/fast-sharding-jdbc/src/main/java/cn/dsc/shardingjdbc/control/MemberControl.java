package cn.dsc.shardingjdbc.control;

import cn.dsc.shardingjdbc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControl {

    private final MemberService memberService;

    @GetMapping("/save/{brandId}/{name}")
    public String save(@PathVariable("brandId") Integer brandId, @PathVariable("name") String name) {
        return String.valueOf(memberService.saveNewMember(name));
    }

    @GetMapping("/find/{brandId}/{id}")
    public String find(@PathVariable("brandId") Integer brandId, @PathVariable("id") Integer id) {
        return memberService.findById(id).getName();
    }
}
