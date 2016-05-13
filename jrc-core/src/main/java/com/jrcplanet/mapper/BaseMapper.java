package com.jrcplanet.mapper;

import java.util.List;

/**
 * 基本的 增,删,改,查接口
 * Created by rxb on 2016/5/9.
 */
public interface BaseMapper {
    /**
     * 取表字段存在放缓存
     *
     * @return List<Map<?, ?>>
     */
    <T> List<T> initTableField(T formMap);

    /**
     * 保存实体
     * @param formMap
     * @param <T>
     * @return
     */
    <T> int insert(T formMap);

    /**
     * 根据主键删除实体
     * @param id
     * @param clazz
     * @return
     */
    int deleteById(String id, Class clazz);

    /**
     * 根据主键获取实体
     * @param id
     * @param <T>
     * @return
     */
    <T> T get(String id);
}
