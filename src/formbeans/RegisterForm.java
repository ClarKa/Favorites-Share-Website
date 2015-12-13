/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-10 11:18:50
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:38
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String confirm;

    public String getEmail()     { return email;     }
    public String getPassword()  { return password;  }
    public String getFirstname() { return firstname; }
    public String getLastname()  { return lastname;  }
    public String getConfirm()   { return confirm;   }

    public void setEmail(String s)     { email  = trimAndConvert(s,"<>\"");  }
    public void setFirstname(String s) { firstname = trimAndConvert(s,"<>\"");  }
    public void setLastname(String s)  { lastname  = trimAndConvert(s,"<>\"");  }
    public void setPassword(String s)  { password  = s.trim();                  }
    public void setConfirm(String s)   { confirm   = s.trim();                  }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (firstname == null || firstname.length() == 0) {
            errors.add("First Name is required");
        }

        if (lastname == null || lastname.length() == 0) {
            errors.add("Last Name is required");
        }

        if (email == null || email.length() == 0) {
            errors.add("User Name is required");
        }

        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }

        if (confirm == null || confirm.length() == 0) {
            errors.add("Confirm Password is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (!password.equals(confirm)) {
            errors.add("Passwords are not the same");
        }

        return errors;
    }
}
