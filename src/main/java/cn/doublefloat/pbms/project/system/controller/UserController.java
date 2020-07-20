package cn.doublefloat.pbms.project.system.controller;

import cn.doublefloat.pbms.framework.web.controller.BaseController;
import cn.doublefloat.pbms.framework.web.domain.AjaxResult;
import cn.doublefloat.pbms.framework.web.page.TableDataResult;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list")
    public TableDataResult list(User user) {
        startPage();
        List<User> users = userService.queryUserList(user);
        return getTableData(users);
    }

    @PostMapping("/add")
    public AjaxResult add(User user) {
        AjaxResult result = AjaxResult.success();
        if (userService.insertUser(user) <= 0) {
            result = AjaxResult.error();
        }
        return result;
    }
}
