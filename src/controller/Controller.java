/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-12 19:35:57
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:20:11
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import databeans.UserBean;
import model.UserDAO;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
    private Model model;

    public void init() throws ServletException {
        model = new Model(getServletConfig());

        Action.add(new ChangePwdAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new ManageAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new AddAction(model));
        Action.add(new ViewAction(model));
        Action.add(new RemoveAction(model));
        Action.add(new ListAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    /*
     * Extracts the requested action and (depending on whether the user is
     * logged in) perform it (or make the user login).
     *
     * @param request
     *
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        UserBean user = (UserBean) session.getAttribute("user");
        String action = getActionName(servletPath);

        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        try {
            UserDAO userDAO  = model.getUserDAO();
            request.setAttribute("userList",userDAO.getUsers());
        } catch (Exception e) {
            System.out.println("71::" + e);
        }

        // Allow these actions without logging in
        if (action.equals("register.do") || action.equals("login.do") || action.equals("list.do") || action.equals("view.do")) {
            return Action.perform(action, request);
        }

        if (user == null) {
            // If the user hasn't logged in, direct him to the login page
            return Action.perform("login.do", request);
        }

        // Let the logged in user run his chosen action
        return Action.perform(action, request);
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
                    + nextPage);
            d.forward(request, response);
            return;
        }

        if (nextPage.length() > 0) {
            response.sendRedirect(nextPage);
            // RequestDispatcher d = request.getRequestDispatcher(nextPage);
            // d.forward(request, response);
            return;
        }

        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    private String getActionName(String path) {
        int q = path.indexOf('?');
        String substr;
        if (q == -1) {
            substr = path;
        } else {
            substr = path.substring(0,q);
        }
        int slash = substr.lastIndexOf('/');
        String action = substr.substring(slash + 1);
        System.out.println("action == " + action);
        return action;
    }
}
