/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-12 19:42:19
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:50:48
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.RegisterForm;

public class RegisterAction extends Action {
    private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            RegisterForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            // If no params were passed, return with no errors so that the form will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
                return "register.jsp";
            }

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "register.jsp";
            }

            // check duplicates
            String email = form.getEmail();
            if (userDAO.checkDuplicate(email)) {
                errors.add("Email already exists, please user another Email to register");
                return "register.jsp";
            }


            // Create the user bean
            UserBean user = new UserBean();
            user.setEmail(email);
            user.setFirstname(form.getFirstname());
            user.setLastname(form.getLastname());
            user.setPassword(form.getPassword());
            userDAO.create(user);

            // Attach (this copy of) the user bean to the session
            HttpSession session = request.getSession(false);
            session.setAttribute("user",user);

            return "manage.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        }
    }
}
