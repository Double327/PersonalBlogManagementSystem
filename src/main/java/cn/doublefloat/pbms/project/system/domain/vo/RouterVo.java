package cn.doublefloat.pbms.project.system.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/23 1:11 下午
 */
@Data
public class RouterVo {

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由
     */
    private Boolean hidden;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     *
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVo meta;

    /**
     * 子路由
     */
    private List<RouterVo> children;
}
