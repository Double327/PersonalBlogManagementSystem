package cn.doublefloat.pbms.project.system.service.impl;

import cn.doublefloat.pbms.common.constant.UserConstants;
import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.project.system.domain.Menu;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.domain.vo.MetaVo;
import cn.doublefloat.pbms.project.system.domain.vo.RouterVo;
import cn.doublefloat.pbms.project.system.mapper.MenuMapper;
import cn.doublefloat.pbms.project.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 李广帅
 * @date 2020/7/22 10:27 下午
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> queryMenuList(User user) {
        return queryMenuList(new Menu(), user);
    }

    @Override
    public List<Menu> queryMenuList(Menu menu, User user) {
        List<Menu> menuList = null;
        if (user.isAdmin()) {
            menuList = menuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", user.getId());
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    @Override
    public Set<String> queryMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.queryMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Menu> queryMenuTreeByUser(User user) {
        List<Menu> menuList = null;
        if (user.isAdmin()) {
            menuList = menuMapper.selectMenuTreeAll();
        } else {
            menuList = menuMapper.selectMenuTreeByUserId(user.getId());
        }
        return getChildrenPerms(menuList, 0L);
    }

    @Override
    public Menu queryMenuInfo(Long menuId) {
        return menuMapper.queryMenuInfo(menuId);
    }

    @Override
    public List<RouterVo> buildMenus(List<Menu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (Menu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<Menu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public Boolean hasChildByMenuId(Long menuId) {
        return null;
    }

    @Override
    public Boolean checkMenuExistRole(Long menuId) {
        return null;
    }

    @Override
    public Integer insertMenu(Menu menu) {
        Long parentId = menu.getParentId();
        String id = parentId.toString() + (menuMapper.selectChildrenSize(parentId) + 1);

        menu.setId(Long.valueOf(id));
        menu.setCreateBy("admin");
        menu.setUpdateBy("admin");
        Date date = new Date();
        menu.setCreateTime(date);
        menu.setUpdateTime(date);
        return menuMapper.insertMenu(menu);
    }

    @Override
    public Integer updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public Integer deleteMenu(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        return null;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(Menu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(Menu menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }


    public List<Menu> getChildrenPerms(List<Menu> list, Long parentId) {
        List<Menu> menuList = new ArrayList<>();
        for (Menu t : list) {
            if (t.getParentId().equals(parentId)) {
                recursionFn(list, t);
                menuList.add(t);
            }
        }
        return menuList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t) {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                for (Menu n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t) {
        List<Menu> tlist = new ArrayList<Menu>();
        for (Menu n : list) {
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t) {
        return getChildList(list, t).size() > 0;
    }
}
