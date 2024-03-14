package com.projects.passwc.DTO;

import com.projects.passwc.Entitys.Passwds;
import com.projects.passwc.service.PasswordGenerator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PasswdForm {

    @Size(max = 20)
    private String resourceName;

    @NotEmpty
    @Size(max = 20)
    private String passwd;

    private boolean isGanerated = false;

    private boolean useLower;

    private boolean useUpper;

    private boolean useDigits;

    private boolean usePunctuation;

    private int length;

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

    public boolean isGanerated() {
        return isGanerated;
    }

    public void setGanerated(boolean ganerated) {
        isGanerated = ganerated;
    }

    public Passwds toPasswds(String user) {
        return new Passwds(user, resourceName, passwd);
    }

    public boolean isUseLower() {
        return useLower;
    }

    public boolean isUseUpper() {
        return useUpper;
    }

    public boolean isUseDigits() {
        return useDigits;
    }

    public boolean isUsePunctuation() {
        return usePunctuation;
    }

    public int getLength() {
        return length;
    }

    public void setUseLower(boolean useLower) {
        this.useLower = useLower;
    }

    public void setUseUpper(boolean useUpper) {
        this.useUpper = useUpper;
    }

    public void setUseDigits(boolean useDigits) {
        this.useDigits = useDigits;
    }

    public void setUsePunctuation(boolean usePunctuation) {
        this.usePunctuation = usePunctuation;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String generatePasswd(boolean useLower, boolean useUpper, boolean useDigits, boolean usePunctuation, int length) {
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useLower(useLower)
                .useUpper(useUpper)
                .useDigits(useDigits)
                .usePunctuation(usePunctuation)
                .build();

        return passwordGenerator.generate(length);
    }
}
