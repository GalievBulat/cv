package Model;

import Interfaces.ConvertibleToList;
import Interfaces.WritableToFile;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserTC implements  ConvertibleToList, WritableToFile {
    private String name;
    private String surname;
    private String email;
    private String phoneNum;
    private long tc;
    private String password;
    private Date birthDay;
    private String avatarPath ;
    public List<String> toList() {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(surname);
        list.add(email);
        list.add(phoneNum);
        list.add(Long.toString(tc));
        list.add(password);
        list.add(birthDay.toString());
        return list;
    }
    private UserTC(String _name, String _surname, String _email, String _phoneNum, long _tc,
                  String _password, Date _birthDay){
        name = _name;
        surname = _surname;
        email = _email;
        phoneNum = _phoneNum;
        tc = _tc;
        password = _password;
        birthDay = _birthDay;
        avatarPath = null;
    }

    private UserTC() {
    }

    public UserTC(String _name, String _surname, String _email, String _phoneNum, long _tc,
                  String _password, Date _birthDay, String _avatarPath){
        name = _name;
        surname = _surname;
        email = _email;
        phoneNum = _phoneNum;
        tc = _tc;
        password = _password;
        birthDay = _birthDay;
        avatarPath = _avatarPath;
    }

    public long getTc() {
        return tc;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public String toString() {
        return "UserTC{ "  + name + "; " +
                  surname +  "; "
                + email + "; "
                + phoneNum + "; "
                 + tc + "; "
                 + password  + "; "
                 + birthDay  +
                '}';
    }

    @Override
    public String getDBRepresentation() {
        return toString();
    }


    public String getAvatarPath() {
        return avatarPath;
    }
    public static class Builder {
        private final UserTC user;
        public Builder(){
            user = new UserTC();
        }
        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder surname(String surname) {
            user.surname = surname;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder phoneNum(String phoneNum) {
            user.phoneNum = phoneNum;
            return this;
        }

        public Builder tc(long tc) {
            user.tc = tc;
            return this;
        }


        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder birthDay(Date birthDay) {
            user.birthDay = birthDay;
            return this;
        }

        public Builder avatarPath(String avatarPath) {
            user.avatarPath = avatarPath;
            return this;
        }
        public UserTC build(){
            return user;
        }
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
