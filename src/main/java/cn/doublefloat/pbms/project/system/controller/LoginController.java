package cn.doublefloat.pbms.project.system.controller;

import cn.doublefloat.pbms.common.constant.Constants;
import cn.doublefloat.pbms.common.utils.ServletUtils;
import cn.doublefloat.pbms.framework.security.LoginUser;
import cn.doublefloat.pbms.framework.security.service.LoginService;
import cn.doublefloat.pbms.framework.security.service.TokenService;
import cn.doublefloat.pbms.framework.web.domain.AjaxResult;
import cn.doublefloat.pbms.project.system.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        res.put("data", loginUser.getUser());
        return res;
    }


    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();

        return AjaxResult.success();
    }
}
