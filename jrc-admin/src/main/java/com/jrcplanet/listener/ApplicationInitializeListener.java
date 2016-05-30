package com.jrcplanet.listener;

import com.jrcplanet.mapper.PermissionMapper;
import com.jrcplanet.service.PermissionService;
import com.jrcplanet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializeListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void contextDestroyed(ServletContextEvent event) {
        logger.debug("---------项目结束...");
    }

    public void contextInitialized(ServletContextEvent event) {
        logger.debug("---------项目启动...");
        WebApplicationContext applicationContext = getApplicationContext(event);
        try {
            initData(applicationContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取WebApplicationContext
     *
     * @param event
     * @return
     */
    public WebApplicationContext getApplicationContext(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    public void initData(WebApplicationContext applicationContext) {
        ServletContext context = applicationContext.getServletContext();
        if (context == null) {
            logger.warn("ServletContext is null!");
        }

        /*获取权限注解并自动导入数据库*/
        logger.debug("==> 检验权限中...");
        PermissionService permissionService = applicationContext.getBean(PermissionService.class);
        permissionService.autoSavePerm();
    }

}
