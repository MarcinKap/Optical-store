package com.opticalstore.security.Admin;


import com.opticalstore.security.RoleRepository;
import com.opticalstore.security.UserApp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class AdminController {

    AdminService adminService;
    RoleRepository roleRepository;


    //    @RequestMapping(value = "/admin/{page}")
    @RequestMapping(value = "/account-menu/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String openAdminAllUsersPage(Model model,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<UserApp> userAppPage = adminService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("userAppPage", userAppPage);
        int totalPages = userAppPage.getTotalPages();
        //            //todo - teoretycznie może być przypadek, że użytkownik posiada więcej niż 1 rolę - zabezpieczyć!
        for (UserApp u : userAppPage
        ) {
            u.setMainRole(u.getRoles().iterator().next().getRole());
        }
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "account-menu/admin";
    }
}