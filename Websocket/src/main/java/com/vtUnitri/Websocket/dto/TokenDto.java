package com.vtUnitri.Websocket.dto;

import java.io.Serializable;

public class TokenDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
