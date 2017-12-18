package com.geolisa.web.controller;

import com.geolisa.bean.City;
import com.geolisa.bean.CityBuilder;
import com.geolisa.exception.ResourceNotFoundException;
import com.geolisa.service.CityMapperService;
import com.geolisa.web.model.CityModel;
import com.geolisa.web.model.ResponseModel;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
    MediaType.APPLICATION_XML_VALUE})
public class CityController {

    //consumes 限定请求的 content-type 就是请求内容的mime类型
    //produces 限定accept 中的mime类型
    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    CityMapperService cityMapperService;

    /**
     * 获取城市列表信息.
     *
     * @return 城市列表集合
     */
    @GetMapping(value = "/list")
    @ResponseBody
    public List<ResponseModel> cityList() {
        if (logger.isDebugEnabled()) {
            logger.debug("get city list");
        }
        List<City> cities = cityMapperService.findCities();
        List<ResponseModel> result = new ArrayList<>();
        for (City city : cities) {
            ResponseModel cityModel = CityBuilder.cityBuilder(city).buildModel();
            result.add(cityModel);
        }
        return result;
    }

    /**
     * 通过城市ID 获取城市信息.
     *
     * @param cityId 城市id
     * @return 单个城市信息
     */
    @GetMapping(value = "/city/{cityId}")
    @ResponseBody
    public ResponseModel cityGet(@PathVariable(value = "cityId") Long cityId) {
        if (logger.isDebugEnabled()) {
            logger.debug("get city entity");
        }
        City city = cityMapperService.getCityById(cityId);
        if (city != null) {
            CityBuilder cityBuilder = CityBuilder.cityBuilder(city);
            return cityBuilder.buildModel();
        }
        throw new ResourceNotFoundException();
    }

    /**
     * 创建城市信息.
     *
     * @param cityModel 城市的基本信息
     * @return 创建后的城市信息
     */
    @PostMapping(value = "/city")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel addCity(@RequestBody @Valid CityModel cityModel) {
        if (logger.isDebugEnabled()) {
            logger.debug("add city entity");
        }
        City city = cityMapperService.insertCity(cityModel.toCity());
        CityBuilder cityBuilder = CityBuilder.cityBuilder(city);
        return cityBuilder.buildModel();
    }

    /**
     * 更新城市信息.
     *
     * @param cityModel 需要更新的城市信息
     * @return 更新后的城市信息
     */
    @PutMapping(value = "/city")
    @ResponseBody
    public ResponseModel updateCity(@RequestBody @Valid CityModel cityModel) {
        if (logger.isDebugEnabled()) {
            logger.debug("update city entity");
        }
        City city = cityMapperService.updateCity(cityModel.toCity());
        return CityBuilder.cityBuilder(city).buildModel();
    }

    /**
     * 通过城市ID删除该城市信息.
     *
     * @param cityId 城市id
     */
    @DeleteMapping(value = "/city/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable(value = "cityId") Long cityId) {
        if (logger.isDebugEnabled()) {
            logger.debug("delete city");
        }
        cityMapperService.deleteCity(cityId);
    }
}
