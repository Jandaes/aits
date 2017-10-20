package com.liu.ticket.service;

import java.util.List;
import java.util.Map;

/**
 * @author jared
 */
public interface CityCodeService {
    /**
     * 获得所有城市名称
     * @param name
     * @return
     */
     List<Map<String,Object>> getCityByName(String name);

    /**
     * 获得所有城市
     * @return
     */
     List<Map<String,Object>> getAllCity();

    /**
     * 保存城市
     */
    void saveCity();
}
