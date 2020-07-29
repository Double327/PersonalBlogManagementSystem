package cn.doublefloat.pbms.project.system.mapper;

import cn.doublefloat.pbms.project.system.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/22 10:38 下午
 */
@Repository
public interface MenuMapper {

    /**
     * 查询菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<Menu> selectMenuListByUserId(Menu menu);

    /**
     * 查询所有菜单树
     *
     * @return 菜单列表
     */
    public List<Menu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单树
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenuTreeByUserId(Long userId);

    /**
     * 查询菜单详细信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public Menu queryMenuInfo(Long menuId);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public Integer insertMenu(Menu menu);

    /**
     * 查询子菜单个数
     *
     * @param parentId 父菜单ID
     * @return 结果
     */
    public Integer selectChildrenSize(Long parentId);

    /**
     * 查询菜单名是否重复
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public Integer checkMenuNameUnique(Menu menu);
}
