package DAO;

import Interfaces.JDBCTemplate;
import Model.UserTC;

import java.util.NoSuchElementException;

public class UserPhotoAdding {
    //language=sql
    private final static String updateQuery = "UPDATE usertc SET avatar_path = ? WHERE tc = ?";
    public void addPhotoPathToUser(long userId, String avatarPath){
        JDBCTemplate<UserTC> jdbc= new JDBCTemplateGenericImpl<>();
        if (jdbc.update(updateQuery,avatarPath,userId) == 0){
            throw new NoSuchElementException("no such user");
        }
    }
}
