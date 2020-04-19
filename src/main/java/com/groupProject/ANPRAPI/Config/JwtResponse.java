package com.groupProject.ANPRAPI.Config;

import java.io.Serializable;

/**
 * Object that is used to map the response of sending back a JWT token
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}