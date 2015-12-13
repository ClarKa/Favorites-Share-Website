/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 03:39:47
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:12:45
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.*;
import databeans.*;
import formbeans.UserForm;

public class ListAction extends Action {
	private FormBeanFactory<UserForm> formBeanFactory = FormBeanFactory.getInstance(UserForm.class);

	private FavoriteDAO favDAO;
	private UserDAO  userDAO;

    public ListAction(Model model) {
    	favDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "list.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			UserForm form = formBeanFactory.create(request);

			String userId = form.getUserId();
			if (userId == null || userId.length() == 0) {
				errors.add("User must be specified");
				return "error.jsp";
			}

	        // Set up fav list
        	UserBean user = userDAO.read(Integer.parseInt(userId));
        	if (user == null) {
    			errors.add("Invalid User: "+userId);
    			return "error.jsp";
    		}

        	FavoriteBean[] favList = favDAO.getUserFavorites(user.getUserid());
	        request.setAttribute("favList",favList);
	        return "list.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
