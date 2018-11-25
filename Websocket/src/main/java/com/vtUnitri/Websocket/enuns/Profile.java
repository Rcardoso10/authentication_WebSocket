package com.vtUnitri.Websocket.enuns;

public enum Profile {

    ADMIN(1L, "ROLE_ADMIN");

    private Long id;
    private String description;

    Profile(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
