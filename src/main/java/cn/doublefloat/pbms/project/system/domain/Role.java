package cn.doublefloat.pbms.project.system.domain;

import cn.doublefloat.pbms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @author 李广帅
 * @date 2020/7/19 5:41 下午
 */
@Data
@ToString
public class Role extends BaseEntity {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String key;

    /**
     * 角色显示顺序
     */
    private String sort;

    /**
     * 访问数据范围
     */
    private String dataScope;

    /**
     * 角色状态
     */
    private String status;

    /**
     * 用户是否存在此角色
     */
    private Boolean flag = false;

    /**
     * 菜单组
     */
    private Long[] menuIds;

    public Role(Long id) {
        this.id = id;
    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }
}
