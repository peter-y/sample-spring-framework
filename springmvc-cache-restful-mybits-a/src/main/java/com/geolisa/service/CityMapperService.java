package com.geolisa.service;

import com.geolisa.bean.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CityMapperService {

    private final Logger logger = LoggerFactory.getLogger(CityMapperService.class);

    @Value("${appname}")
    String appname;

    @Autowired
    CityMapper cityMapper;

    /**
     * 插入城市信息.
     * @return 城市信息
     */
    public City insertCity() {
        City city = new City();
        city.setCity("beijing");
        city.setCityCode("010");
        city.setCityJb("beijing-jb");
        city.setCityName("北京");
        city.setProvince("beijing");
        city.setProvinceCode("010");
        City result = cityMapper.insertCity(city);
        if (logger.isDebugEnabled()) {
            logger.debug("{} insert : {}", appname, result.toString());
        }
        return result;
    }
}
