/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-11-19 22:15:17
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:23
 */
package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteId")
public class FavoriteBean {
    private int favoriteId;
    private int userId;
    private String url;
    private String comment;
    private int count;

    public int getFavoriteId()    { return favoriteId; }
    public int getUserId()        { return userId; }
    public String getUrl()        { return url; }
    public String getComment()    { return comment; }
    public int getCount()         { return count; }

    public void setFavoriteId(int s) { favoriteId = s; }
    public void setUserId(int s)     { userId = s; }
    public void setUrl(String s)     { url = s; }
    public void setComment(String s) { comment = s; }
    public void setCount(int s)      { count = s; }
}