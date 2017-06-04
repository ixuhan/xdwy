package cn.ixuhan.xdwy.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * Created by hank on 2017/6/4 0004.
 */
public class Test extends BaseSupport{

    /**
     * 测试
     */
    @Action(value = "test", results = {@Result(name = "success", location = "/index.jsp")})
    public String test() {
        this.getRequest().setAttribute("result","asd");

        return SUCCESS;
    }
}
