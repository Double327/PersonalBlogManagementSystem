package cn.doublefloat.pbms.framework.security.service;

import cn.doublefloat.pbms.common.constant.Constants;
import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.framework.redis.RedisCacheService;
import cn.doublefloat.pbms.framework.security.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author 李广帅
 * @date 2020/7/21 6:12 下午
 */
@Slf4j
@Service
public class LoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCacheService redisCacheService;

    public String login(String username, String password, String code, String uuid) throws Exception {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String captcha = redisCacheService.getCacheObject(verifyKey);
        log.debug("取回验证码:" + captcha);
        redisCacheService.deleteObject(verifyKey);

        if (StringUtils.isNull(captcha)) {
            throw new Exception();
        }

        if (!code.equalsIgnoreCase(captcha)) {
            log.debug(code + "===验证码错误!===>" + captcha);
        }

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }
}
