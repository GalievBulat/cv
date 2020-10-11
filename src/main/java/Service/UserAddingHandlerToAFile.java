package Service;

import Model.UserTC;

import java.util.List;

public final class UserAddingHandlerToAFile implements UserAddingHandler {
    private final String fileName;
    public UserAddingHandlerToAFile(String fileName){
        this.fileName = fileName;
    }

    public UserAddingHandlerToAFile() {
        fileName = "C:\\Users\\Kakad\\Documents\\cv\\src\\main\\java\\users_tc.txt";
    }

    public boolean addUser(List<String> userData){
        if (!userData.contains("")){
            UserTC user = new UserTC(userData);
            if ( user.getPhoneNum().matches("\\+\\d{11}")) {
                FileOutputHandler fH = new FileOutputHandler(fileName);
                fH.writeTheUserToTheFile(user);
                return true;
            }
        }
        return false;
    }
}
