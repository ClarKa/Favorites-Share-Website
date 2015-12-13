/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-10 11:16:32
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:35
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
    private String email;
    private String password;

    public String getEmail()     { return email; }
    public String getPassword()  { return password; }

    public void setEmail(String s) { email = trimAndConvert(s,"<>\"");  }
    public void setPassword(String s) { password = s.trim(); }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (email == null || email.length() == 0) errors.add("Email is required");
        if (password == null || password.length() == 0) errors.add("Password is required");

        if (errors.size() > 0) return errors;

        if (email.matches(".*[<>\"].*")) errors.add("Email may not contain angle brackets or quotes");

        return errors;
    }
}
