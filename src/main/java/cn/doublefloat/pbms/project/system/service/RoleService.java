package cn.doublefloat.pbms.project.system.service;

import cn.doublefloat.pbms.project.system.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * @author 李广帅
 * @date 2020/9/2 5:47 下午
 */
public interface RoleService {

    /**
     * 根据条件分页查询角色信息
     *
     * @param role 条件
     * @return 角色数据
     */
    List<Role> queryRoleList(Role role);

    /**
     * 根据用户编号查询角色信息
     *
     * @param userId 用户编号
     * @return 角色信息
     */
    Set<String> queryRolePermissionByUserId(Long userId);

    /**
     * 查询所有角色信息
     *
     * @return 角色数据
     */
    List<Role> queryAllRoles();


}
