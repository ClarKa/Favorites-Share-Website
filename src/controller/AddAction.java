/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-12 21:06:04
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:07:54
 */
package controller;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FavoriteBean;
import databeans.UserBean;
import formbeans.AddFavoriteForm;
import model.Model;
import model.FavoriteDAO;
import model.UserDAO;

public class AddAction extends Action {
    private FormBeanFactory<AddFavoriteForm> formBeanFactory = FormBeanFactory
            .getInstance(AddFavoriteForm.class);

    private FavoriteDAO favDAO;
    private UserDAO userDAO;

    public AddAction(Model model) {
        favDAO = model.getFavoriteDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "add.do";
    }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            // Set up user list for nav bar
            request.setAttribute("userList",userDAO.getUsers());

            // Set up fav list for user
            UserBean user = (UserBean) request.getSession(false).getAttribute("user");
            FavoriteBean[] favList = favDAO.getUserFavorites(user.getUserid());
            request.setAttribute("favList",favList);

            // deal with form
            AddFavoriteForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // check params
            if (!form.isPresent()) {
                return "manage.jsp";
            }

            // check validation error
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                System.out.println("Add error");
                return "manage.jsp";
            }

            // Truncate the prefix of http:// or https://
            String url = form.getUrl();
            if (url.startsWith("http://")) {
                url = url.substring(7);
            }
            if (url.startsWith("https://")) {
                url = url.substring(8);
            }

            // check duplicates
            int uid = user.getUserid();
            if (favDAO.checkDuplicate(uid, url)) {
                errors.add("Url already exists, please check in your list.");

                System.out.println("Add duplicates");
                return "manage.jsp";
            }

            // create the favorite.
            FavoriteBean fav = new FavoriteBean();
            fav.setUserId(uid);
            fav.setUrl(url);
            fav.setComment(form.getComment());
            fav.setCount(0);
            favDAO.create(fav);

            return "manage.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}