package pl.kodokan.fcp.server.authentication;

public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public String getToken(){
        return jwtToken;
    }
}
