package com.projects.passwc.response;

import com.projects.passwc.DAO.Passwds;
import lombok.Data;

import java.util.List;

@Data
public class PasswdsResponse {
    private List<Passwds> passwdsList;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
