package cn.doublefloat.pbms.project.system.controller;

import cn.doublefloat.pbms.common.constant.UserConstants;
import cn.doublefloat.pbms.common.utils.ServletUtils;
import cn.doublefloat.pbms.framework.security.LoginUser;
import cn.doublefloat.pbms.framework.security.service.TokenService;
import cn.doublefloat.pbms.framework.web.controller.BaseController;
import cn.doublefloat.pbms.framework.web.domain.AjaxResult;
import cn.doublefloat.pbms.project.system.domain.Menu;
import cn.doublefloat.pbms.project.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/26 8:31 下午
 */
@Slf4j
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取菜单列表
     *
     * @param menu 查询信息
     * @return 结果
     */
    @GetMapping("/list")
    public AjaxResult list(Menu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Menu> menuList = menuService.queryMenuList(menu, loginUser.getUser());
        return AjaxResult.success(menuList);
    }

    /**
     * 根据菜单编号获取详细信息
     *
     * @param menuId 菜单编号
     * @return 结果
     */
    @GetMapping("/info/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        log.debug("查询菜单信息");
        return AjaxResult.success(menuService.queryMenuInfo(menuId));
    }


    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @PostMapping("/add")
    public AjaxResult add(Menu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("新增菜单'" + menu.getName() + "'失败,菜单名已存在");
        }
        return toAjax(menuService.insertMenu(menu));
    }


    @DeleteMapping("/remove")
    public AjaxResult remove(Menu menu) {
        return toAjax(menuService.deleteMenu(menu.getId()));
    }

}
