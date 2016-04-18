package com.jrcplanet.beetl.ext.spring;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringView;

import java.util.Locale;

/**
 * 覆盖BeetlSpringView的checkResource方法，未找到交由其他处理器
 * Created by rxb on 2016/4/18.
 */
public class RichBeetlSpringView extends BeetlSpringView {
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        BeetlGroupUtilConfiguration config = getApplicationContext().getBean(BeetlGroupUtilConfiguration.class);
        return config.getGroupTemplate().getResourceLoader().exist(getUrl());
    }
}
