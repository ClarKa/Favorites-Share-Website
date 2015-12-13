/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-09 23:27:21
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:52:08
 */
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.*;

public class Model {
	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");

			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			userDAO  = new UserDAO(pool, "kaifuw_user");
			favoriteDAO = new FavoriteDAO(pool, "kaifuw_favorite");

			// populate intial data
			String[] names = {"Aaron", "Abe", "Ace"};
			String[] urls = {"gmail.com", "www.google.com", "www.cmu.edu", "www.yelp.com"};

			for (String n : names) {
				String email = n + "@gmail.com";
	            if (userDAO.checkDuplicate(email)) {
	                continue;
	            }

				UserBean user = new UserBean();
				user.setPassword("1");
				user.setFirstname(n);
				user.setLastname("Andrew");
				user.setEmail(email);
				userDAO.create(user);

				for (String url : urls) {
					FavoriteBean f = new FavoriteBean();
					f.setUserId(user.getUserid());
					f.setUrl(url);
					f.setComment(url);
					f.setCount(0);
					favoriteDAO.create(f);
				}
			}
		} catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			System.out.println(e);
		}
	}

	public FavoriteDAO getFavoriteDAO() { return favoriteDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
}
