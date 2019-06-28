import java.io.Serializable;
import java.util.ArrayList;

public class UserNames implements Serializable {
    ArrayList<String>userNameArraylist=new ArrayList<>();
    public void setUserNameArraylist(String s)
    {
        userNameArraylist.add(s);
    }

    public ArrayList<String> getUserNameArraylist() {
        return userNameArraylist;
    }
}
