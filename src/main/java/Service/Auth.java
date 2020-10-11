package Service;

import Model.UserTC;

import java.util.Optional;

public interface Auth {
    public Optional<UserTC> authorise(String login, String password);
}
