package com.projects.passwc.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswdsDTO {

    @Size(max = 20)
    private String resourceName;

    @NotEmpty
    @Size(max = 20)
    private String passwd;

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
