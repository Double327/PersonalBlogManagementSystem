package cn.doublefloat.pbms.project.system.service;

import cn.doublefloat.pbms.project.system.domain.Menu;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * @author 李广帅
 * @date 2020/7/22 10:21 下午
 */
public interface MenuService {

    /**
     * 根据用户查询系统菜单列表
     *
     * @param user 用户
     * @return 菜单列表
     */
    public List<Menu> queryMenuList(User user);

    /**
     * 根据用户和菜单信息查询菜单列表
     *
     * @param menu 菜单信息
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> queryMenuList(Menu menu, User user);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限
     */
    public Set<String> queryMenuPermsByUserId(Long userId);

    /**
     * 根据用户查询菜单树信息
     *
     * @param user 用户
     * @return 菜单列表
     */
    public List<Menu> queryMenuTreeByUser(User user);

    /**
     * 根据菜单ID查询详细信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public Menu queryMenuInfo(Long menuId);

    /**
     * 构建前端所需的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<Menu> menus);


    /**
     * 检查是否含有子菜单
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public Boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public Boolean checkMenuExistRole(Long menuId);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public Integer insertMenu(Menu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public Integer updateMenu(Menu menu);

    /**
     * 删除菜单信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public Integer deleteMenu(Long menuId);

    /**
     * 检查菜单名是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(Menu menu);
}
