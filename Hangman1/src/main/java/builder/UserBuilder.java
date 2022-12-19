package builder;


import model.User;
import java.net.UnknownHostException;


public class UserBuilder {


    public User user;


    public UserBuilder() throws UnknownHostException {
        this.user = new User();
    }

    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder myWord(String myWord){
        user.setMyWord(myWord);
        return this;
    }

    public UserBuilder address() {
        user.getAddress();
        return this;
    }

    public UserBuilder userWinner() {
        user.isUserWinner();
        return this;
    }

    public User build() {
        return this.user;
    }

}
