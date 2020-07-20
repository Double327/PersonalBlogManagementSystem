package cn.doublefloat.pbms.framework.web.page;

import lombok.Data;

/**
 * @author 李广帅
 * @date 2020/7/19 4:25 下午
 */
@Data
public class PageDomain {

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 总页数
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String orderColumn;

    /**
     * 排序规则 "asc" 或者 "desc"
     */
    private String orderRule;
}
