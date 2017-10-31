package tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DebugTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        if (request.getParameter("debug") != null && request.getServerName().startsWith("test")){
            return EVAL_BODY_INCLUDE; 
        }
        else{
            return SKIP_BODY; 
        }
    }  
}