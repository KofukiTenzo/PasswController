package com.projects.passwc.DTO;

import javax.validation.constraints.NotBlank;

public class PasswdsDTO {

    @NotBlank(message = "Enter resource name")
    private String resourceName;

    @NotBlank(message = "Enter or generate password")
    private String passwd;

    public PasswdsDTO(){

    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resource_n) {
        this.resourceName = resource_n;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
