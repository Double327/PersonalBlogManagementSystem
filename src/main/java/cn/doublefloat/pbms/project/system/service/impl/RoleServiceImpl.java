package cn.doublefloat.pbms.project.system.service.impl;

import cn.doublefloat.pbms.project.system.domain.Role;
import cn.doublefloat.pbms.project.system.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 李广帅
 * @date 2020/9/2 5:51 下午
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> queryRoleList(Role role) {
        return null;
    }

    @Override
    public Set<String> queryRolePermissionByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Role> queryAllRoles() {
        return null;
    }
}
