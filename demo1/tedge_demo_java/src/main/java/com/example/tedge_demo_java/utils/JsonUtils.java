package com.example.tedge_demo_java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper
import com.example.tedge_demo_java.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * @author lumang.wei@tuya.com
 * @since 2021/11/24
 */
@Slf4j
public class JsonUtils {


    private JsonUtils() {

    }

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化出错：" + obj, e);
            throw ExceptionUtils.JSON_ERROR.toException();
        }
    }

    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            throw ExceptionUtils.JSON_ERROR.toException();
        }
    }

    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            throw ExceptionUtils.JSON_ERROR.toException();
        }
    }


    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            throw ExceptionUtils.JSON_ERROR.toException();
        }
    }


    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            throw ExceptionUtils.JSON_ERROR.toException();
        }
    }

}
