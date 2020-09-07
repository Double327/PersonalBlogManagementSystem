package cn.doublefloat.pbms.framework.security.service;

import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.service.MenuService;
import cn.doublefloat.pbms.project.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 李广帅
 * @date 2020/9/2 6:14 下午
 */
@Component
public class UserPermissionService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    public Set<String> getRolePermission(User user) {
        Set<String> roles = new HashSet<>();
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.queryRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    public Set<String> getMenuPermission(User user) {
        Set<String> roles = new HashSet<>();
        if (user.isAdmin()) {
            roles.add("*:*:*");
        } else {
            roles.addAll(menuService.queryMenuPermsByUserId(user.getId()));
        }
        return roles;
    }
}
