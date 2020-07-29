package cn.doublefloat.pbms.project.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李广帅
 * @date 2020/7/23 1:13 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaVo {

    /**
     * 路由显示名称
     */
    private String title;

    /**
     * 路由图标
     */
    private String icon;
}
