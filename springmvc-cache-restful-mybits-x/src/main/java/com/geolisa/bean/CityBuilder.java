package com.geolisa.bean;

import com.geolisa.web.model.CityModel;
import com.geolisa.web.model.ResponseModel;

public class CityBuilder extends City {

    private CityBuilder() {

    }

    private CityBuilder(City cityInstance) {
        this.cityInstance = cityInstance;
    }

    private City cityInstance;

    public static CityBuilder cityBuilder() {
        return new CityBuilder();
    }

    public static CityBuilder cityBuilder(City city) {
        return new CityBuilder(city);
    }

    public CityBuilder withId(Long id) {
        setId(id);
        return this;
    }

    public CityBuilder withCityCode(String cityCode) {
        setCityCode(cityCode);
        return this;
    } //城市代码 4位阿拉伯数字

    public CityBuilder withProvinceCode(String provinceCode) {
        setProvinceCode(provinceCode);
        return this;
    } //所在省份代码

    public CityBuilder withCityName(String cityName) {
        setCityName(cityName);
        return this;
    } //城市名称

    public CityBuilder withCityJb(Integer cityJb) {
        setCityJb(cityJb);
        return this;
    } //城市级别 1 省级 2地级 3 县级

    public CityBuilder withCity(String city) {
        setCity(city);
        return this;
    } //所在地城市名称

    public CityBuilder withProvince(String province) {
        setProvince(province);
        return this;
    } //所在省份名称

    public CityBuilder withState(String state) {
        setState(state);
        return this;
    }

    /**
     * 构建一个 dto 这里是cityModel.
     */
    public ResponseModel buildModel() {
        CityModel cityModel = new CityModel();
        if (cityInstance == null) {
            cityModel.setId(getId());
            cityModel.setCityName(getCityName());
            cityModel.setCity(getCity());
            cityModel.setCityCode(getCityCode());
            cityModel.setCityJb(getCityJb());
            cityModel.setProvince(getProvince());
            cityModel.setState(getState());
            cityModel.setProvinceCode(getProvinceCode());
            return cityModel;
        }
        cityModel.setId(cityInstance.getId());
        cityModel.setCityName(cityInstance.getCityName());
        cityModel.setCity(cityInstance.getCity());
        cityModel.setCityCode(cityInstance.getCityCode());
        cityModel.setCityJb(cityInstance.getCityJb());
        cityModel.setProvince(cityInstance.getProvince());
        cityModel.setState(cityInstance.getState());
        cityModel.setProvinceCode(cityInstance.getProvinceCode());
        return cityModel;
    }
}
