package com.iris.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml?error=true"
        )
)
@ApplicationScoped
/*@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/PostgreSQLDS",
        callerQuery = "#"  // dummy, we won't use this store
)*/
public class SecurityConfig { }