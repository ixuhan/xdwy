package cn.ixuhan.xdwy.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with Hank.
 * User: Hank
 * Date: 2016/11/8 0008
 * Time: 19:49
 * Des: Base Action Class
 */
public class BaseSupport extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    protected String msg = "ok";

    public void setServletRequest(HttpServletRequest request) {
        this.request=request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response=response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
