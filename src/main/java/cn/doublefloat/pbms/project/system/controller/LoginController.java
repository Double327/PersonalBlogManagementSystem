package cn.doublefloat.pbms.project.system.controller;

import cn.doublefloat.pbms.common.constant.Constants;
import cn.doublefloat.pbms.common.utils.ServletUtils;
import cn.doublefloat.pbms.framework.security.LoginUser;
import cn.doublefloat.pbms.framework.security.service.LoginService;
import cn.doublefloat.pbms.framework.security.service.TokenService;
import cn.doublefloat.pbms.framework.security.service.UserPermissionService;
import cn.doublefloat.pbms.framework.web.domain.AjaxResult;
import cn.doublefloat.pbms.project.system.domain.Menu;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author 李广帅
 * @date 2020/7/21 5:55 下午
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserPermissionService userPermissionService;

    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) throws Exception {

        log.debug("username:" + username + "          password:" + password);
        String token = loginService.login(username, password, code, uuid);
        AjaxResult res = AjaxResult.success();
        res.put(Constants.TOKEN, token);
        return res;
    }

    @PostMapping("/getUserInfo")
    public AjaxResult getUserInfo() {
        AjaxResult res = AjaxResult.success();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();
        // 角色权限
        Set<String> roles = userPermissionService.getRolePermission(user);

        // 菜单权限
        Set<String> permission = userPermissionService.getMenuPermission(user);

        res.put("user", user);
        res.put("roles", roles);
        res.put("permissions", permission);
        return res;
    }


    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();
        List<Menu> menus = menuService.queryMenuTreeByUser(user);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
