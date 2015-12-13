/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-11-23 22:46:40
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:31
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class AddFavoriteForm extends FormBean {
    private String url;
    private String comment;

    public String getUrl()     { return url;     }
    public String getComment() { return comment; }

    public void setUrl(String s)     { url = sanitize(s); }
    public void setComment(String s) { comment = sanitize(s);  }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (url == null || url.length() == 0) {
            errors.add("Url is required");
        }

        return errors;
    }

    private String sanitize(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}