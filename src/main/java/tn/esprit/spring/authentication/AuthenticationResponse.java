package tn.esprit.spring.authentication;

public class AuthenticationResponse {

   private String jwt;

    public AuthenticationResponse(String jwt) {

    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
