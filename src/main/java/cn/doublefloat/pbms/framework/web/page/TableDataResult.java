package cn.doublefloat.pbms.framework.web.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/19 4:24 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDataResult implements Serializable {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据行
     */
    private List<?> rows;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;
}
