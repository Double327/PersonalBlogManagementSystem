package cn.doublefloat.pbms.project.system.domain;

import cn.doublefloat.pbms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/22 10:15 下午
 */
@Data
@ToString
public class Menu extends BaseEntity {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 上级菜单名
     */
    private String parentName;

    /**
     * 上级菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否为外链
     */
    private String isFrame;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 是否可见
     */
    private String visible;

    /**
     * 菜单状态
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<Menu>();

}
