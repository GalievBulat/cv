package Service;

import Interfaces.AuthHandler;
import Interfaces.DAO;
import DAO.UserTCRepositoryImpl;
import Interfaces.Repository;
import Interfaces.UserAddingHandler;
import Model.UserTC;

import javax.servlet.ServletRequest;
import java.security.InvalidKeyException;
import java.sql.Date;
import java.util.Optional;

public final class UserOperatingHandlerDB implements AuthHandler, UserAddingHandler {
    Repository<UserTC> repository = new UserTCRepositoryImpl();
    @Override
    public Optional<UserTC> authoriseByTC(long tc, String password) throws InvalidKeyException {
        Optional<UserTC> oUser = repository.get( tc);
        if (oUser.isPresent()){
            UserTC user = oUser.get();
            if (user.getPassword().equals(password))
                return oUser;
        }else throw new InvalidKeyException("no user found for this tc");

        return Optional.empty();
    }
    public Optional<UserTC> searchForUser(long id){
        return repository.get(id);
    }
    public void updateUser(UserTC user){
        repository.update(user);
    }

    @Override
    public void addUser(ServletRequest req){
        TextEncoding textEncoding = new TextEncoding();
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String phoneNum = req.getParameter("phone_num");
        final String tc = req.getParameter("tc");
        final String password = textEncoding.encodeText(req.getParameter("password"));
        final String birthDay = req.getParameter("birth_day");
        if (name== null || name.equals(""))
            throw new RuntimeException("not a name");
        if (surname== null || surname.equals(""))
            throw new RuntimeException("not a surname");
        if (email== null ||!email.matches("\\w+@\\w+"))
            throw new RuntimeException("not an email");
        if (phoneNum== null ||!phoneNum.matches("\\+\\d{11}"))
            throw new RuntimeException("not a number");
        if (password== null || password.length()<8)
            throw new RuntimeException("wrong tc have been stated");
        if (birthDay== null || !birthDay.matches("\\d{2}.\\d{2}"))
            throw new RuntimeException("wrong date format");
        UserTC user = new UserTC.Builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNum(phoneNum)
                .tc(Long.parseLong(tc))
                .password(password)
                .birthDay(Date.valueOf(birthDay)).build();
        repository.add(user);
    }
}
