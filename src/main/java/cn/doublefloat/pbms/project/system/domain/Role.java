package cn.doublefloat.pbms.project.system.domain;

import cn.doublefloat.pbms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @author 李广帅
 * @date 2020/7/19 5:41 下午
 */
@Data
@ToString
public class Role extends BaseEntity {
    private Long id;

    private String name;

    private String key;

    private String sort;

    private String dataScope;

    private String status;

    private Boolean flag = false;

    private Long[] menuIds;
}
