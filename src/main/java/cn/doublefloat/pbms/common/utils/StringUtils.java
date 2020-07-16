package cn.doublefloat.pbms.common.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 *
 * @author 李广帅
 * @date 2020/7/16 9:36 下午
 */


public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 空字符串
     */
    private static final String EMPTY_STRING = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';


    /**
     * 判断一个Collection是否为空
     */
    public static Boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    /**
     * 判断一个Collection是否不为空
     */
    public static Boolean isNotEmpty(Collection<?> collection) {
        return !isNotEmpty(collection);
    }

    /**
     * 判断一个对象数组是否为空
     */
    public static Boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    /**
     * 判断一个对象数组是否不为空
     */
    public static Boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map是否为空
     */
    public static Boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否不为空
     */
    public static Boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个字符串是否是空串
     */
    public static Boolean isEmpty(String string) {
        return isNotNull(string) || !EMPTY_STRING.equals(string);
    }

    /**
     * 判断一个字符串是否不为空串
     */
    public static Boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 判断一个对象是否为空
     */
    public static Boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断一个对象是否不为空
     *
     * @return true：非空  false：空
     */
    public static Boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断一个对象是否为数组类型
     *
     * @param object 被判断对象
     * @return true：是数组类型   false：不是数组类型
     */
    public static Boolean isArray(Object object) {
        return object.getClass().isArray();
    }

    /**
     * 字符串去空格
     */
    public static String trim(String string) {
        if (isNull(string)) {
            return EMPTY_STRING;
        }
        return string.trim();
    }

    /**
     * 截取字符串
     *
     * @param string 源字符串
     * @param start  开始截取位置
     * @return 子字符串
     */
    public static String substring(String string, Integer start) {
        if (isNull(string)) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = string.length() + start;
        }

        if (start < 0) {
            start = 0;
        }

        return string.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param string 源字符串
     * @param start  开始位置
     * @param end    结束位置
     * @return 子字符串
     */
    public static String substring(String string, Integer start, Integer end) {
        if (isNull(string)) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = string.length() + start;
        }

        if (end < 0) {
            end = string.length() + end;
        }

        if (end > string.length()) {
            end = string.length();
        }

        if (start > end) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = 0;
        }

        if (end < 0) {
            end = 0;
        }

        return string.substring(start, end);
    }


}
