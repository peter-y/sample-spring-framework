package com.geolisa.bean;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;

public class City implements Serializable {

    private static final long serialVersionUID = 6437346039309818615L;
    private Long id;
    private String cityCode;//城市代码 4位阿拉伯数字
    private String provinceCode;//所在省份代码
    private String cityName;//城市名称
    private Integer cityJb;//城市级别 1 省级 2地级 3 县级
    private String city;//所在地城市名称
    private String province;//所在省份名称
    private String state;//城市状态 0 无效城市 1 有效城市

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityJb() {
        return cityJb;
    }

    public void setCityJb(Integer cityJb) {
        this.cityJb = cityJb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city1 = (City) o;
        return Objects.equal(id, city1.id)
            && Objects.equal(cityCode, city1.cityCode)
            && Objects.equal(provinceCode, city1.provinceCode)
            && Objects.equal(cityName, city1.cityName)
            && Objects.equal(cityJb, city1.cityJb)
            && Objects.equal(city, city1.city)
            && Objects.equal(state, city1.state)
            && Objects.equal(province, city1.province);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, cityCode, provinceCode, cityName, cityJb, city, province, state);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("cityCode", cityCode)
            .add("provinceCode", provinceCode)
            .add("cityName", cityName)
            .add("cityJb", cityJb)
            .add("city", city)
            .add("province", province)
            .add("state", state)
            .toString();
    }
}
