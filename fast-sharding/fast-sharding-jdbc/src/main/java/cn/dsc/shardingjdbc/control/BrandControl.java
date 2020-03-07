package cn.dsc.shardingjdbc.control;

import cn.dsc.shardingjdbc.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandControl {

    private final BrandService brandService;

    @GetMapping("/save/{code}")
    public String save(@PathVariable("code") String code) {
        return String.valueOf(brandService.saveNewBrand(code));
    }

    @GetMapping("/find/{id}")
    public String find(@PathVariable("id") Integer id) {
        return brandService.findById(id).getCode();
    }
}
