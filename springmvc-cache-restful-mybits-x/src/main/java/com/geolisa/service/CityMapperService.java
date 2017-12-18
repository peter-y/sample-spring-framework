package com.geolisa.service;

import com.geolisa.bean.City;
import java.util.List;

public interface CityMapperService {

    public City insertCity(City city);

    public City getCityByCode(String code);

    public City getCityById(Long id);

    public List<City> findCities();

    public void deleteCity(Long id);

    public City updateCity(City city);
}
