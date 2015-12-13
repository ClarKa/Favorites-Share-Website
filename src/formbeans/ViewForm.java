/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 02:06:00
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:46
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class ViewForm extends FormBean {
    private int favId;
    private String url;

    public int getFavId()  { return favId; }
    public String getUrl() { return url;   }

    public void setFavId(int i)  { favId = i; }
    public void setUrl(String s) { url = s;   }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (favId == 0 || favId < 0) errors.add("favorite is required");
        if (url == null || url.length() == 0) errors.add("url is required");

        if (errors.size() > 0) return errors;
        return errors;
    }
}