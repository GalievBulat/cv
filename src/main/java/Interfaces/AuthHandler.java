package Interfaces;

import Model.UserTC;

import java.security.InvalidKeyException;
import java.util.Optional;

public interface AuthHandler {
    Optional<UserTC> authoriseByTC(long tc, String password) throws InvalidKeyException;
}
