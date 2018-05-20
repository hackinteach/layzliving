package io.muic.cs.ooc.lazyliving.backend.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseLogin {

    private String message;
    private boolean login;

    public ResponseLogin(){
        this("You're not logged in",false);
    }

    public ResponseLogin(String msg, boolean login){
        this.message = msg;
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public boolean isLogin(){
        return login;
    }

    @Override
    public String toString(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
