/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-10 03:53:13
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:50:43
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.FavoriteDAO;
import model.UserDAO;

import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.UserBean;

public class ManageAction extends Action {

	private FavoriteDAO favDAO;
	private UserDAO  userDAO;

	public ManageAction(Model model) {
    	favDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

	public String getName() { return "manage.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			UserBean user = (UserBean) request.getSession(false).getAttribute("user");
        	FavoriteBean[] favList = favDAO.getUserFavorites(user.getUserid());
	        request.setAttribute("favList",favList);

	        return "manage.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
