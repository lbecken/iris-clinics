package com.iris.security;

import com.iris.user.model.User;
import com.iris.user.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.util.Set;

@ApplicationScoped
public class DatabaseIdentityStore implements IdentityStore {
    @Inject
    UserRepository userRepository;

    //@Inject
    //Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;
        User user = userRepository.findByUsername(upc.getCaller());

        if (user != null && user.isActive() && plainPasswordMatches(upc.getPasswordAsString(), user.getPassword())) {
            return new CredentialValidationResult(user.getUsername(),
                    Set.of(user.getRoleName()));
        }
        return CredentialValidationResult.INVALID_RESULT;
    }

    // using plaintext in db
    private boolean plainPasswordMatches(String password1, String password2) {
        return password1.equals(password2);
    }

    //private boolean passwordMatches(String formPasswordPlainText, String userPasswordHash) {
    //    return pbkdf2PasswordHash.verify(userPasswordHash.toCharArray(), formPasswordPlainText);
    //}
}
