package by.training.cryptomarket.markettag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;


/**
 * This class handles custom tag.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TimeTag extends TagSupport {


    /**
     * This method handles custom tag.
     * @return tag processing flag
     * @throws JspException JspException
     */
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<hr>Time : <b> " + gc.getTime() + " </b><hr/>";



        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }


    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
