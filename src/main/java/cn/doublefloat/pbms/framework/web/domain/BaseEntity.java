package cn.doublefloat.pbms.framework.web.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 李广帅
 * @date 2020/7/19 4:18 下午
 */
@Data
public class BaseEntity {

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除人
     */
    private String deleteBy;

    /**
     * 删除时间
     */
    private Date deleteTime;
}
