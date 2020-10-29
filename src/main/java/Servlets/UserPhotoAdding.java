package Servlets;

import DAO.JDBCTemplate;
import DAO.JDBCTemplateGenericImpl;
import Model.UserTC;

import java.util.NoSuchElementException;

public class UserPhotoAdding {
    //language=sql
    private final static String updateQuery = "UPDATE usertc SET avatar_path = ? WHERE tc = ?";
    public void addPhotoPathFromUser(long userId,String avatarPath){
        JDBCTemplate<UserTC> jdbc= new JDBCTemplateGenericImpl<UserTC>();
        if (jdbc.update(updateQuery,avatarPath,userId) == 0){
            throw new NoSuchElementException("no such user");
        }
    }
}
