package com.lym.utils;

import cn.hutool.core.convert.Convert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServletUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    public static void setAttribute(String key, Object data) {
        getHttpSession().setAttribute(key, data);
    }

    public static HttpSession getHttpSession() {
        return getRequest().getSession();
    }

    public static Object getSessionAttribute(String var1) {
        return getHttpSession().getAttribute(var1);
    }

    public static Object getRequestAttribute(String var1) {
        return getRequest().getAttribute(var1);
    }

    /**
     * 获取所有请求参数
     *
     * @return 返回所有请求参数
     */
    public static Map<String, String> getAllParam() {
        Map params = new HashMap();
        HttpServletRequest request = getRequest();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String val = request.getParameter(paraName);
            params.put(paraName, val);
        }
        return params;
    }

    /**
     * 获取请求参数
     *
     * @param key
     * @return 根据key返回指定参数
     */
    public static Object getRequestParam(String key) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getParameter(key);
        }
        return null;
    }

    /**
     * 获取请求参数返回指定的强制转换对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getRequestParam(String key, Class<T> clazz) {
        Object obj = getRequestParam(key);
        if (obj != null) {
            return (T) obj;
        }
        return null;
    }

    /**
     * 获取请求参数返回字符串
     *
     * @param key
     * @return
     */
    public static String getRequestParamString(String key) {
        Object obj = getRequestParam(key);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    public static HttpServletResponse getImageRes() {
        HttpServletResponse response = ServletUtils.getResponse();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expirres", 0);
        response.setContentType("image/jpeg");
        return response;
    }

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    public static String getHeaderParameter(String name) {
        return getRequest().getHeader(name);
    }

    public static String getHeaderParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getHeader(name), defaultValue);
    }

    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }


    public static Integer getHeaderParameterToInt(String name) {
        return Convert.toInt(getRequest().getHeader(name));
    }

    public static Integer getHeaderParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getHeader(name), defaultValue);
    }


    public static Boolean getParameterToBoolean(String name) {
        return Convert.toBool(getRequest().getHeader(name));
    }

    public static Boolean getParameterToBoolean(String name, Boolean defaultValue) {
        return Convert.toBool(getRequest().getHeader(name), defaultValue);
    }

    public static Boolean getHeaderParameterToBoolean(String name) {
        return Convert.toBool(getRequest().getHeader(name));
    }

    public static Boolean getHeaderParameterToBoolean(String name, Boolean defaultValue) {
        return Convert.toBool(getRequest().getHeader(name), defaultValue);
    }

}
