package cn.doublefloat.pbms.project.system.controller;

import cn.doublefloat.pbms.common.constant.UserConstants;
import cn.doublefloat.pbms.framework.web.controller.BaseController;
import cn.doublefloat.pbms.framework.web.domain.AjaxResult;
import cn.doublefloat.pbms.framework.web.page.TableDataResult;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/19 4:50 下午
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public AjaxResult add(User user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUsernameUnique(user.getUsername()))) {
            return AjaxResult.error("用户:" + user.getUsername() + "创建失败!用户名已存在!");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneNumUnique(user.getPhoneNum()))) {
            return AjaxResult.error("用户:" + user.getUsername() + "创建失败!手机号已使用!");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getEmail()))) {
            return AjaxResult.error("用户:" + user.getUsername() + "创建失败!邮箱已使用!");
        }
        return toAjax(userService.insertUser(user));
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable Long[] ids) {
        return toAjax(userService.deleteUserByIds(ids));
    }

    @PutMapping("/edit")
    public AjaxResult edit(User user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneNumUnique(user.getPhoneNum()))) {
            return AjaxResult.error("用户:" + user.getUsername() + "修改失败!手机号已使用!");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getEmail()))) {
            return AjaxResult.error("用户:" + user.getUsername() + "修改失败!邮箱已使用!");
        }
        return toAjax(userService.updateUser(user));
    }

    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(User user) {
        return toAjax(userService.resetUserPassword(user.getUsername(), user.getPassword()));
    }





    @GetMapping("/list")
    public TableDataResult list(User user) {
        startPage();
        List<User> users = userService.queryUserList(user);
        return getTableData(users);
    }
}
