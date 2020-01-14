package com.opticalstore.security.Admin;

import com.opticalstore.security.UserApp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    AdminRepository adminRepository;

    public Page<UserApp> findAll(Pageable pageable){
        Page<UserApp> userList = adminRepository.findAll(pageable);
        return userList;
    }


    public Page<UserApp> findPaginated(Pageable pageable) {
        List<UserApp> userApps = adminRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserApp> list;
        if (userApps.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userApps.size());
            list = userApps.subList(startItem, toIndex);
        }
        Page<UserApp> userAppPage
                = new PageImpl<UserApp>(list, PageRequest.of(currentPage, pageSize), userApps.size());

        return userAppPage;
    }






}
