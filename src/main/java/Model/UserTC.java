package Model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserTC implements CreatableFromList,ConvertibleToList,WritableToFile {
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNum;
    private final long tc;
    private final String password;
    private final Date birthDay;
    private final String avatarPath ;
    public UserTC(List<String> list){
        name = list.get(0);
        surname = list.get(1);
        email = list.get(2);
        phoneNum = list.get(3);
        tc = Long.parseLong(list.get(4));
        password = list.get(5);
        birthDay = Date.valueOf(list.get(6));
        avatarPath = null;
    }
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
    public UserTC(String _name, String _surname, String _email, String _phoneNum, long _tc,
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
    public UserTC(String _name, String _surname, String _email, String _phoneNum, long _tc,
                  String _password, Date _birthDay,String _avatarPath){
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

    public UserTC getUserWithPicture(UserTC user,String avatarPath){
        return new UserTC(user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNum(),user.getTc(),
                user.getPassword(),user.getBirthDay(),avatarPath);
    }

    public String getAvatarPath() {
        return avatarPath;
    }
}
