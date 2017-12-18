package com.geolisa.bean.mapper;

import com.geolisa.bean.City;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CityMapper {

    public Long insertCity(City city);

    public City getCityByCode(String code);

    public City getCityById(Long id);

    public List<City> findCities();

    public void deleteCity(Long id);

    public Long updateCity(City city);
}
