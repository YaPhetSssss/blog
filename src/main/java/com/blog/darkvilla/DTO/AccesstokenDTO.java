package com.blog.darkvilla.DTO;

public class AccesstokenDTO {
    private String client_id;
    private String client_serect;
    private String code;
    private String redict_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_serect;
    }

    public String getClient_serect() {
        return client_serect;
    }

    public String getCode() {
        return code;
    }

    public String getRedict_uri() {
        return redict_uri;
    }

    public String getState() {
        return state;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_serect(String client_serect) {
        this.client_serect = client_serect;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRedict_uri(String redict_uri) {
        this.redict_uri = redict_uri;
    }

    public void setState(String state) {
        this.state = state;
    }
}
