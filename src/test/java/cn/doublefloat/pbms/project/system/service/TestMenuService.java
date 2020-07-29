package cn.doublefloat.pbms.project.system.service;

import cn.doublefloat.pbms.project.system.domain.Menu;
import cn.doublefloat.pbms.project.system.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/23 10:17 上午
 */
@SpringBootTest
public class TestMenuService {

    @Autowired
    private MenuService menuService;

    @Test
    public void testQueryMenuListWithMenuAndUser() {
        User user = new User();
        user.setId(1L);
        List<Menu> menuList = menuService.queryMenuList(new Menu(), user);
        menuList.forEach(System.out::println);
    }
}
