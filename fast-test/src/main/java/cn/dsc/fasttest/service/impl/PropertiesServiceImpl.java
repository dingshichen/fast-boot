package cn.dsc.fasttest.service.impl;

import cn.dsc.fasttest.service.PropertiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Value("${commonName}")
    private String commonName;

    @Value("${commonLine}")
    private Integer commonLine;

    @Override
    public String getCommonName() {
        return commonName;
    }

    @Override
    public Integer getCommonLine() {
        return commonLine;
    }
}
