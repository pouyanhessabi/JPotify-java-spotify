import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Responder class keeping userNAme for better serialize
 *
 * @author omid mahyar and pouyan hesabi *
 * @version 1.0  (1398/04/08)
 */
public class UserNames implements Serializable {
    ArrayList<String> userNameArraylist = new ArrayList<>();

    public void setUserNameArraylist(String s) {
        userNameArraylist.add(s);
    }

    public ArrayList<String> getUserNameArraylist() {
        return userNameArraylist;
    }
}
