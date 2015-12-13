/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-11-19 20:51:03
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:38:53
 */
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import java.util.Arrays;
import databeans.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBean.class, tableName, cp);
    }

    public UserBean[] getUsers(String email) throws RollbackException {
        UserBean[] users;
        try {
            Transaction.begin();
            users = match(MatchArg.equals("email", email));
            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }

        return users;
    }

    public UserBean[] getUsers() throws RollbackException {
        UserBean[] users = match();
        Arrays.sort(users);
        return users;
    }

    public void create(UserBean newUser) throws RollbackException {
        try {
            Transaction.begin();

            createAutoIncrement(newUser);

            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public boolean checkDuplicate(String email) throws RollbackException {
        try {
            UserBean[] user = match(MatchArg.equals("email", email));
            if (user.length > 0) {
                return true;
            } else {
                return false;
            }
        } catch (RollbackException e) {
            System.out.println(e);
            return true;
        }
    }

    public void setPassword(int id, String newPassword) throws RollbackException {
        try {
            Transaction.begin();

            UserBean u = read(id);
            u.setPassword(newPassword);
            update(u);

            Transaction.commit();
        } catch (RollbackException e) {
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }
}