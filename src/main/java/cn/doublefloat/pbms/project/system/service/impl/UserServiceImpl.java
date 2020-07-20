package cn.doublefloat.pbms.project.system.service.impl;

import cn.doublefloat.pbms.common.utils.IdUtils;
import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.mapper.UserMapper;
import cn.doublefloat.pbms.project.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 李广帅
 * @date 2020/7/19 4:55 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (StringUtils.isNull(user.getNickname()) || StringUtils.isEmpty(user.getNickname())) {
            user.setNickname("PBMS-" + user.getUsername());
        }
        user.setCreateBy("Double");
        user.setUpdateBy("Double");
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return userMapper.insertUser(user);
    }

    @Override
    public Integer deleteUserByUsername(String username) {
        return userMapper.deleteUserByUsername(username);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public Integer deleteUserByIds(Long[] ids) {
        return userMapper.deleteUserByIds(ids);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer resetUserPassword(String username, String password) {
        return userMapper.resetUserPassword(username, passwordEncoder.encode(password));
    }

    @Override
    public Integer updateUserStatus(User user) {
        return userMapper.updateUserStatus(user);
    }

    @Override
    public Integer updateUserAvatar(User user) {
        return userMapper.updateUserAvatar(user);
    }

    @Override
    public List<User> queryUserList(User user) {
        return userMapper.queryUserList(user);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public String checkUsernameUnique(String username) {
        return userMapper.checkUsernameUnique(username);
    }

    @Override
    public String checkPhoneNumUnique(String phoneNum) {
        return userMapper.checkPhoneNumUnique(phoneNum);
    }

    @Override
    public String checkEmailUnique(String email) {
        return userMapper.checkEmailUnique(email);
    }
}
