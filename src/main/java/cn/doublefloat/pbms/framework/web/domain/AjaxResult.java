package cn.doublefloat.pbms.framework.web.domain;

import cn.doublefloat.pbms.common.constant.HttpStatus;
import cn.doublefloat.pbms.common.utils.StringUtils;
import lombok.Data;

import java.util.HashMap;

/**
 * @author 李广帅
 * @date 2020/7/20 2:21 下午
 */
@Data
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码标识
     */
    public static final String CODE_TAG = "code";

    /**
     * 消息标识
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据标识
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化空 AjaxResult对象
     */
    public AjaxResult() {

    }

    /**
     * 初始化 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  消息内容
     */
    public AjaxResult(Integer code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  消息内容
     * @param data 数据
     */
    public AjaxResult(Integer code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @param data 数据
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 消息内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  消息内容
     * @param data 数据
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 消息内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误信息
     *
     * @param msg  消息内容
     * @param data 数据
     * @return 错误消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  消息内容
     * @return 错误消息
     */
    public static AjaxResult error(Integer code, String msg) {
        return new AjaxResult(code, msg);
    }

}
