package cn.doublefloat.pbms.project.system.mapper;

import cn.doublefloat.pbms.project.system.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/19 5:38 下午
 */

public interface UserMapper {

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public Integer insertUser(User user);

    /**
     * 根据用户账户删除用户信息
     *
     * @param username 用户账户
     * @return 结果
     */
    public Integer deleteUserByUsername(String username);

    /**
     * 根据用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    public Integer deleteUserById(Long id);

    /**
     * 批量删除用户信息
     *
     * @param ids 用户ID
     * @return 结果
     */
    public Integer deleteUserByIds(Long[] ids);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public Integer updateUser(User user);

    /**
     * 重置用户密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public Integer resetUserPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    public Integer updateUserStatus(User user);

    /**
     * 修改用户头像
     *
     * @param user 用户信息
     * @return 结果
     */
    public Integer updateUserAvatar(User user);

    /**
     * 查询用户信息
     *
     * @param user 查询条件
     * @return 结果
     */
    public List<User> queryUserList(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    public User queryUserByUsername(String username);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return 结果
     */
    public String checkUsernameUnique(String username);

    /**
     * 检查手机号是否存在
     *
     * @param phoneNum 手机号
     * @return 结果
     */
    public String checkPhoneNumUnique(String phoneNum);

    /**
     * 检查邮箱是否存在
     *
     * @param email 邮箱
     * @return 结果
     */
    public String checkEmailUnique(String email);
}
