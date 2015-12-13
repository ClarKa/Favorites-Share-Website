/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-11-19 20:51:21
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:52:05
 */
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.FavoriteBean;


public class FavoriteDAO extends GenericDAO<FavoriteBean> {
    public FavoriteDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(FavoriteBean.class, tableName, cp);
    }

    public FavoriteBean[] getUserFavorites(int userid) throws RollbackException {
        FavoriteBean[] list = null;

        try {
            Transaction.begin();
            list = match(MatchArg.equals("userId", userid));
            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }

        return list;
    }

    public void create(FavoriteBean newFavorite) throws RollbackException {
        try {
            Transaction.begin();

            createAutoIncrement(newFavorite);

            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public void updateCount(int favoriteId) throws RollbackException {
        try {
            Transaction.begin();

            FavoriteBean f = read(favoriteId);
            f.setCount(f.getCount()+1);
            update(f);

            Transaction.commit();
        } catch (RollbackException e) {
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public boolean checkDuplicate(int userid, String url) throws RollbackException {
        try {
            FavoriteBean[] f = match(MatchArg.and(
                                                MatchArg.equals("url", url),
                                                MatchArg.equals("userId", userid)));
            if (f.length > 0) {
                return true;
            } else {
                return false;
            }
        } catch (RollbackException e) {
            System.out.println(e);
            return true;
        }
    }

    public void remove(int favoriteId) throws RollbackException {
        try {
            Transaction.begin();

            delete(favoriteId);

            Transaction.commit();
        } catch (RollbackException e) {
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }
}