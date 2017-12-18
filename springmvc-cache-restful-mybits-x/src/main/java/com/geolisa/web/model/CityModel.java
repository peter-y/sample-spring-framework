package com.geolisa.web.model;

import com.geolisa.bean.City;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据模型.
 */
@XmlRootElement(name = "city")
public class CityModel implements RequestModel, ResponseModel, Serializable {

    private static final long serialVersionUID = -3371934618173052904L;
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 4)
    private String cityCode;//城市代码 4位阿拉伯数字
    @NotEmpty
    private String provinceCode;//所在省份代码
    @NotEmpty
    private String cityName;//城市名称
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private Integer cityJb;//城市级别 1 省级 2地级 3 县级
    @NotEmpty
    private String city;//所在地城市名称
    @NotEmpty
    private String province;//所在省份名称
    @NotEmpty
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

    @XmlElement(name = "cityLevel")
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

    /**
     * 从页面提交数据 形成对应的city bean.
     */
    public City toCity() {
        City city = new City();
        city.setId(getId());
        city.setProvinceCode(getProvinceCode());
        city.setProvince(getProvince());
        city.setCityName(getCityName());
        city.setCityCode(getCityCode());
        city.setCity(getCity());
        city.setCityJb(getCityJb());
        city.setState(getState());
        return city;
    }
}
