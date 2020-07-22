package cn.doublefloat.pbms.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 李广帅
 * @date 2020/7/18 12:18 下午
 */
public class ServletUtils {

    /**
     * 获取String类型参数
     *
     * @param name 参数名
     * @return 参数值
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取Integer类型的参数
     *
     * @param name 参数名
     * @return 参数值
     */
    public static Integer getParameterToInteger(String name) {
        String value = getRequest().getParameter(name);
        if (StringUtils.isNotNull(value)) {
            return Integer.valueOf(value);
        } else {
            return 0;
        }
    }


    /**
     * 获取请求信息
     *
     * @return 请求信息
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取相应信息
     *
     * @return 相应信息
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取Session
     *
     * @return Session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取全部请求参数
     *
     * @return 请求参数
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
