package com.iris.auth;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.Serializable;

@Named
//@ViewScoped
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean loggedIn;


    @Inject
    private SecurityContext securityContext;

    // actions

    public String login() {
        Credential credential = new UsernamePasswordCredential(username,
                new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
                getRequest(), getResponse(),
                AuthenticationParameters.withParams().credential(credential));
        if (status == AuthenticationStatus.SUCCESS) {
            return "/patient/patients-list?faces-redirect=true";
        }
        // show error
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public void reset() {
        username = null;
        password = null;
    }

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    // helpers

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    private HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
    }

    public boolean isAdmin() {
        return securityContext.isCallerInRole("ADMIN");
    }
}
