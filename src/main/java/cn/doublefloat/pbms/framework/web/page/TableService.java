package cn.doublefloat.pbms.framework.web.page;

import cn.doublefloat.pbms.common.utils.ServletUtils;

/**
 * @author 李广帅
 * @date 2020/7/19 4:25 下午
 */
public class TableService {

    private static final String PAGE_NUM = "pageNum";

    private static final String PAGE_SIZE = "pageSize";

    private static final String ORDER_COLUMN = "orderColumn";

    private static final String ORDER_RULE = "asc";

    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInteger(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInteger(PAGE_SIZE));
        pageDomain.setOrderColumn(ServletUtils.getParameter(ORDER_COLUMN));
        pageDomain.setOrderRule(ServletUtils.getParameter(ORDER_RULE));
        return pageDomain;
    }

    public static PageDomain buildPageDomain() {
        return getPageDomain();
    }
}
