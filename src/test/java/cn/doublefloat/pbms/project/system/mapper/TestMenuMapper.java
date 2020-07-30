package cn.doublefloat.pbms.project.system.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 李广帅
 * @date 2020/7/30 7:23 上午
 */
@SpringBootTest
public class TestMenuMapper {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testDeleteMenuById() {
        Long id = 404L;
        Integer result = menuMapper.deleteMenuById(id);
        if (result > 0) {
            System.out.println("删除成功!");
        } else {
            System.out.println("删除失败！");
        }
    }

    @Test
    public void testDeleteMenuByIds() {
        Long[] ids = {1L, 2L, 31L, 4L};
        Integer result = menuMapper.deleteMenuByIds(ids);
        if (result > 0) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败！");
        }
    }
}
