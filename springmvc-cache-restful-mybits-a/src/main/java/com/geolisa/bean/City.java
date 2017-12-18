package com.geolisa.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonSerialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class City {
    private String id;
    private String cityCode;
    private String provinceCode;
    private String cityName;
    private String cityJb;
    private String city;
    private String province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCityJb() {
        return cityJb;
    }

    public void setCityJb(String cityJb) {
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
            && Objects.equal(province, city1.province);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, cityCode, provinceCode, cityName, cityJb, city, province);
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
            .toString();
    }
}
