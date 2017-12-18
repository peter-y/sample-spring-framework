package com.geolisa.service.impl;

import com.geolisa.bean.City;
import com.geolisa.bean.mapper.CityMapper;
import com.geolisa.service.CityMapperService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CityMapperServiceImpl implements CityMapperService {

    private final Logger logger = LoggerFactory.getLogger(CityMapperServiceImpl.class);

    @Value("${appname}")
    String appname;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    RedisOperations<String,String> redisTemplate;

    //说是能这么注入
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    /**
     * 插入城市信息.
     *
     * @return 城市信息
     */
    public City insertCity(City city) {
        Assert.notNull(city, "city is null");
        Assert.notNull(city.getCityCode(), "cityCode is null");
        Assert.hasText(city.getCityCode(), "cityCode must be has text");
        City getCity = cityMapper.getCityByCode(city.getCityCode());
        if (getCity != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("by code get City info {}", getCity.toString());
            }
            return getCity;
        }
        cityMapper.insertCity(city);
        if (logger.isDebugEnabled()) {
            logger.debug("{} insert : {}", appname, city.toString());
        }
        return city;
    }

    /**
     * 获取城市信息 通过城市代码.
     *
     * @param code 城市代码
     */
    public City getCityByCode(String code) {
        Assert.notNull(code, "code is null");
        City city = cityMapper.getCityByCode(code);
        return city;
    }

    /**
     * 获取城市信息 通过 城市id.
     *
     * @param id 城市id
     */
    public City getCityById(Long id) {
        Assert.notNull(id, "id is null");
        City city = cityMapper.getCityById(id);
        return city;
    }

    /**
     * 查询城市列表 all.
     */
    public List<City> findCities() {
        List<City> result = cityMapper.findCities();
        return result;
    }

    /**
     * 通过城市ID 删除城市信息.
     *
     * @param id 需要删除的城市id
     */
    public void deleteCity(Long id) {
        cityMapper.deleteCity(id);
    }

    /**
     * 更新城市信息，通过city中的数据.
     *
     * @param city 城市的具体信息
     */
    public City updateCity(City city) {
        Assert.notNull(city, "city is null");
        cityMapper.updateCity(city);
        return city;
    }
}
