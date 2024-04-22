package com.projects.passwc.service;

import com.projects.passwc.DTO.PasswdsDTO;
import com.projects.passwc.Entitys.Passwds;
import com.projects.passwc.Entitys.User;
import com.projects.passwc.data.PasswdsRepository;
import com.projects.passwc.response.PasswdsResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PasswdsService {

    private final PasswdsRepository passwdsRepository;

    private final UserService userService;

    public PasswdsService(PasswdsRepository passwdsRepository, UserService userService) {
        this.passwdsRepository = passwdsRepository;
        this.userService = userService;
    }

    @PreAuthorize("#username == authentication.principal.username")
    public PasswdsResponse getAllUserPasswds(int page, String username) {

        User user = userService.getAuthentication(username);

        List<Passwds> passwdsObj = passwdsRepository.findAllByUser(user);

        passwdsObj.sort(((o1, o2) -> o2.getCreation_date().compareTo(o1.getCreation_date())));

        return paginatedPasswds(page, passwdsObj);
    }

    @PreAuthorize("#username == authentication.principal.username")
    public void save(PasswdsDTO input, String username) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Passwds passwds = new Passwds(
                input.getResourceName(),
                input.getPasswd(),
                now.format(formatter)
        );

        passwds.assignUser(userService.getAuthentication(username));

        passwdsRepository.save(passwds);
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

    private PasswdsResponse paginatedPasswds(int page, List<Passwds> list) {
        PagedListHolder<Passwds> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(7);
        pagedListHolder.setPage(page);

        PasswdsResponse passwdsResponse = new PasswdsResponse();
        passwdsResponse.setPasswdsList(pagedListHolder.getPageList());
        passwdsResponse.setPageNumber(pagedListHolder.getPage());
        passwdsResponse.setPageSize(pagedListHolder.getPageSize());
        passwdsResponse.setTotalElements(pagedListHolder.getNrOfElements());
        passwdsResponse.setTotalPages(pagedListHolder.getPageCount());
        passwdsResponse.setLast(pagedListHolder.isLastPage());

        return passwdsResponse;
    }
}
