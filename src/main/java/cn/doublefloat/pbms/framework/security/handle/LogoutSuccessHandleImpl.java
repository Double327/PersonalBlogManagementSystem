package cn.doublefloat.pbms.framework.security.handle;

import cn.doublefloat.pbms.common.utils.ServletUtils;
import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.framework.security.LoginUser;
import cn.doublefloat.pbms.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李广帅
 * @date 2020/7/21 6:30 下午
 */
@Component
public class LogoutSuccessHandleImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(httpServletRequest);
        if (StringUtils.isNotNull(loginUser)) {
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(httpServletResponse, "退出登录");
    }
}
