package cn.doublefloat.pbms.project.system.mapper;

import cn.doublefloat.pbms.project.system.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/9/2 5:56 下午
 */
@Repository
public interface RoleMapper {
    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<Role> queryRoleList(Role role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> queryRolePermissionByUserId(Long userId);

    /**
     * 查询所有角色数据
     *
     * @return 角色数据
     */
    List<Role> queryAllRoles();
}
