/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 01:26:57
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:50:53
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import model.*;

public class RemoveAction extends Action {
    // private FormBeanFactory<ViewForm> formBeanFactory = FormBeanFactory
    //         .getInstance(ViewForm.class);

    private FavoriteDAO favDAO;

    public RemoveAction(Model model) {
        favDAO = model.getFavoriteDAO();
    }

    public String getName() {
        return "remove.do";
    }

    public String perform(HttpServletRequest request) {
        try {
            int fid = Integer.parseInt(request.getParameter("id"));
            favDAO.remove(fid);

            return "manage.do";
        } catch (Exception e) {
            return "error.jsp";
        }
    }
}