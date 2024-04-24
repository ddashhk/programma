package com.uniplan.controller.main;

import com.uniplan.model.Users;
import com.uniplan.repo.ProjectsRepo;
import com.uniplan.repo.TaskDescriptionRepo;
import com.uniplan.repo.TasksRepo;
import com.uniplan.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Main {
    @Autowired
    protected ProjectsRepo projectsRepo;
    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected TasksRepo tasksRepo;
    @Autowired
    protected TaskDescriptionRepo taskDescriptionRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    protected String defaultAvatar = "def.jpeg";

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    protected String DateNow() {
        return LocalDateTime.now().toString().substring(0, 10);
    }
}