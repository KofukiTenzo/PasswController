package com.projects.passwc.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswdForm {
    @NotNull
    @Size(min=1, max=50)
    private String resource_n;

    @NotNull
    @Size(min=6, max=15)
    private String passwd;

    public String getResource_n() {
        return resource_n;
    }

    public void setResource_n(String resource_n) {
        this.resource_n = resource_n;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
