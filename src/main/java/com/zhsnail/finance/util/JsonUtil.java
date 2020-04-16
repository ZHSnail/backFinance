package com.zhsnail.finance.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhsnail.finance.entity.User;
import com.zhsnail.finance.exception.BaseRuningTimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 全部字段序列化
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * 对象转字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj){
        if(obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj :  objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseRuningTimeException(e.getMessage());
        }
    }
    /**
     * 有格式的
     * @param obj
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseRuningTimeException(e.getMessage());
        }
    }

    /**
     * 字符串转对象
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T string2Obj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }

        try {
            return clazz.equals(String.class)? (T)str : objectMapper.readValue(str,clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseRuningTimeException(e.getMessage());
        }
    }

    /**
     * json转list
     * @param str json
     * @return list
     */
    public static List getListByJsonArray(String str){
        if (StringUtils.isEmpty(str)){
            return new ArrayList();
        }else {
            List list = new ArrayList();
            try {
                list =  objectMapper.readValue(str,List.class);
                return list;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new BaseRuningTimeException(e.getMessage());
            }
        }
    }

    /**
     * 跟据json数组转list对象数组
     * @param str json
     * @param clazz 对象
     * @param <T> list
     * @return list对象
     */
    public static <T> List<T> getListByJsonArray(String str,Class<T> clazz) {
        if(StringUtils.isEmpty(str) || clazz == null){
            return new ArrayList<T>();
        }else {
            List list = getListByJsonArray(str);
            List<T> resultList = new ArrayList<>();
            try {
                for (int i = 0;i<list.size();i++){
                    T t = clazz.newInstance();
                    BeanUtil.copyProperties(t,list.get(0));
                    resultList.add(t);
                }
                return resultList;
            }catch (Exception e){
                e.printStackTrace();
                throw new BaseRuningTimeException("systemcom.zhsnail.finance.util.JsonUtil.getListByJsonArrayError");
            }
        }
    }

    /**
     * json转map
     * @param str json
     * @return map
     */
    public static Map getMapByJsonString(String str){
        if (StringUtils.isEmpty(str)){
            return new HashMap();
        }else {
            try {
                Map map = objectMapper.readValue(str, Map.class);
                return map;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new BaseRuningTimeException(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String json = "[{\"id\":\"1\",\"name\":\"tom\"},{\"id\":\"2\",\"name\":\"jerry\"}]";
        List listByJsonArray = getListByJsonArray(json);
        Map o = (Map) listByJsonArray.get(0);
        User user = new User();
        List<User> listByJsonArray1 = getListByJsonArray(json, User.class);
        System.out.println(obj2String(listByJsonArray1));
    }
}
