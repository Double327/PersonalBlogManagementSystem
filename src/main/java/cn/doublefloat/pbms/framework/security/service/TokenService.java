package cn.doublefloat.pbms.framework.security.service;

import cn.doublefloat.pbms.common.constant.Constants;
import cn.doublefloat.pbms.common.utils.StringUtils;
import cn.doublefloat.pbms.framework.redis.RedisCacheService;
import cn.doublefloat.pbms.framework.security.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 李广帅
 * @date 2020/6/14 7:14 下午
 */
@Service
@Slf4j
public class TokenService {

    /**
     * 令牌密钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 令牌标示
     */
    @Value("${token.header}")
    private String header;

    /**
     * 令牌有效期
     */
    @Value("${token.expireTime}")
    private Integer expireTime;

    /**
     * 秒转毫秒
     */
    private static final Long MILLIS_SECOND = 1000L;

    /**
     * 分钟转毫秒
     */
    private static final Long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    /**
     * 十分钟
     */
    private static final Long MILLIS_MINUTE_TEN = 10 * MILLIS_MINUTE;

    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * 从数据声明生成Token
     * @param claims 数据声明
     * @return token字符串
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 创建token
     * @param loginUser 登录用户
     * @return token字符串
     */
    public String createToken(LoginUser loginUser) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        loginUser.setToken(token);
        refreshToken(loginUser);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 刷新Token有效期
     * @param loginUser 登录用户
     */
    public void refreshToken(LoginUser loginUser) {
        // 设置用户登录时间和有效期
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

        // 将用户信息存入Redis
        String userTokenKey = getTokenKey(loginUser.getToken());
        redisCacheService.setCacheObject(userTokenKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 验证token有效性
     * @param loginUser 登录用户
     */
    public void verifyToken(LoginUser loginUser) {
        // 获取失效时间和当前时间
        Long expireTime = loginUser.getExpireTime();
        Long currentTime = System.currentTimeMillis();

        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            String token = loginUser.getToken();
            loginUser.setToken(token);
            refreshToken(loginUser);
        }
    }


    /**
     * 解析Token
     * @param token token值
     * @return 内容
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 从请求中获取Token
     *
     * @param request 请求信息
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotNull(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 获取登录用户信息
     * @param request 请求
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNotNull(token) && StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String tokenKey = getTokenKey(uuid);
            return redisCacheService.getCacheObject(tokenKey);
        }
        return null;
    }

    /**
     * 删除登录用户信息
     * @param token 令牌
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCacheService.deleteObject(userKey);
        }
    }

    /**
     * 拼接带头标示的uuid
     * @param uuid uuid
     * @return uuid
     */
    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

}
