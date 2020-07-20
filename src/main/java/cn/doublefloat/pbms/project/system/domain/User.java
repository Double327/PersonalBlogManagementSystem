package cn.doublefloat.pbms.project.system.domain;

import cn.doublefloat.pbms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/19 1:53 下午
 */
@Data
@ToString
public class User extends BaseEntity {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账户
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 上次登录IP
     */
    private String loginIp;

    /**
     * 上次登录时间
     */
    private Date loginDate;

    /**
     * 角色对象
     */
    private List<Role> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    /**
     * 备注
     */
    private String remark;
}
