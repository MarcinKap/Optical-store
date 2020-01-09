package com.opticalstore.security.Admin;


import com.opticalstore.security.Role;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class AdminController {

    AdminService adminService;
    RoleRepository roleRepository;


    //    @RequestMapping(value = "/admin/{page}")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String openAdminAllUsersPage(Model model,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<UserApp> userAppPage = adminService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("userAppPage", userAppPage);
        int totalPages = userAppPage.getTotalPages();
        System.out.println("total pages = " + totalPages);

        for (UserApp u : userAppPage
             ) {
            System.out.println(u.getId());
            System.out.println(u.getRoles().iterator().next().getRole());
            u.setMainRole(u.getRoles().iterator().next().getRole());
        };


        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin";
    }


//    private Page<UserApp> getAllUsersPageable(int page){
//        int elements = 5;
//        Page<UserApp> pages = adminService.findAll(PageRequest.of(page, elements));
//        for (UserApp users: pages){
//            //todo - teoretycznie może być przypadek, że użytkownik posiada więcej niż 1 rolę - zabezpieczyć!
//            String roleName = users.getRoles().iterator().next().getRole();
//            users.setMainRole(roleName);
//        }
//        return pages;
//    }


}


//        page = 1;
//        Page<UserApp> pages = getAllUsersPageable(page - 1);
//
//        int totalPages = pages.getTotalPages();
//        int currentPage = pages.getNumber();
//
//
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pages", pages);
//
//            model.addAttribute("totalPages", totalPages);
//            model.addAttribute("currentPage", currentPage + 1);
//
//        }

//
//        List<UserApp> userAppList = pages.getContent();
//
//        model.addAttribute("userAppList", userAppList);