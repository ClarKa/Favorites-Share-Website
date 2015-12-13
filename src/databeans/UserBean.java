/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-11-19 22:14:57
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 03:51:18
 */
package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("userid")
public class UserBean implements Comparable<UserBean> {
    private int userid;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    public int getUserid()       { return userid; }
    public String getPassword()  { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname()  { return lastname; }
    public String getEmail()     { return email; }

    public void setUserid(int s)       { userid = s; }
    public void setPassword(String s)  { password = s; }
    public void setFirstname(String s) { firstname = s; }
    public void setLastname(String s)  { lastname = s; }
    public void setEmail(String s)     { email = s; }

    public int compareTo(UserBean other) {
        // Order first by lastName, firstName, userid
        int c = lastname.compareTo(other.lastname);
        if (c != 0) {
            return c;
        }

        c = firstname.compareTo(other.firstname);

        if (c != 0) {
            return c;
        }

        return userid - other.userid;
    }

    public boolean checkPassword(String pwd) {
        return password.equals(pwd);
    }
}
