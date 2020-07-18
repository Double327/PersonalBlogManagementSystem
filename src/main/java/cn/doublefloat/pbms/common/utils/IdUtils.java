package cn.doublefloat.pbms.common.utils;

import java.util.UUID;

/**
 * @author 李广帅
 * @date 2020/7/18 12:31 下午
 */
public class IdUtils {

    /**
     * 随机生成UUID
     *
     * @return UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化UUID，无横线
     *
     * @return UUID字符串
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
