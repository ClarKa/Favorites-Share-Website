/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-12 20:25:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:50:38
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;


/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

		request.setAttribute("message","You are now logged out");
        return "success.jsp";
    }
}
