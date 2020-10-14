/*

package Servants;

import Model.UserTC;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public final class AuthHandler implements Auth {
    private final int USER_ATTRIBUTES ;
    private final String USERS_FILE;
    public AuthHandler(){
        USER_ATTRIBUTES = 6;
        USERS_FILE = "C:\\Users\\Kakad\\Documents\\webb\\src\\main\\java\\users.txt";
    }
    public AuthHandler(String filename,int attrs){
        USER_ATTRIBUTES = attrs;
        USERS_FILE = filename;
    }

public Optional<UserTC> authorise(String login, String password){
        InstanceCreating findSth = new FindSth();
        try {
            */
/*int i = 0;
            UserTC user = findSth.getMe(i,USERS_FILE,USER_ATTRIBUTES,UserTC.class);
            while (user!=null){
              if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                  return Optional.of(user);
              }
              i++;
              user = findSth.getMe(i,USERS_FILE,USER_ATTRIBUTES,User.class);
            }
            return Optional.empty();*//*

        } catch (FileNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

}
*/
