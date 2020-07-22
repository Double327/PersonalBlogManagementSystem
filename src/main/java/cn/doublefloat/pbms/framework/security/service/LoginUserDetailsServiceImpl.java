package cn.doublefloat.pbms.framework.security.service;

import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.framework.security.LoginUser;
import cn.doublefloat.pbms.project.system.domain.User;
import cn.doublefloat.pbms.project.system.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 李广帅
 * @date 2020/7/21 9:39 下午
 */
@Slf4j
@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUserByUsername(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户:{}不存在", username);
            throw new Exception();
        }
        return createUserDetails(user);
    }

    public UserDetails createUserDetails(User user) {
        return new LoginUser(user);
    }
}
