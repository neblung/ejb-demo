package webapp1.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import api.MyBean;

@Stateless(name = "MyBean")
public class MyBeanImpl implements MyBean {
    @Resource(lookup = "java:module/ModuleName")
    private String moduleName;

    @Override
    public String info() {
        return String.format("%s (%s)", getClass().getName(), moduleName);
    }
}
