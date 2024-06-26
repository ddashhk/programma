package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Controller
public class IndexCont extends Attributes {

    @GetMapping("/")
    public String Index1(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/index")
    public String Index2(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam String fio) {
        Users user = getUser();
        user.setFio(fio);
        usersRepo.save(user);
        return "redirect:/index";
    }

    @PostMapping("/user/edit/avatar")
    public String userAvatarEdit(Model model, @RequestParam MultipartFile avatar) {
        if (avatar != null && !Objects.requireNonNull(avatar.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            String res = "";
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "avatars/" + uuidFile + "_" + avatar.getOriginalFilename();
                    avatar.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Не удалось изменить аватарку");
                AddAttributesIndex(model);
                return "index";
            }
            Users user = getUser();
            if (!user.getAvatar().equals(defaultAvatar)) {
                try {
                    Files.delete(Paths.get(uploadImg + "/" + user.getAvatar()));
                } catch (IOException e) {
                    model.addAttribute("message", "Не удалось изменить аватарку");
                    AddAttributesIndex(model);
                    return "index";
                }
            }
            user.setAvatar(res);
            usersRepo.save(user);
        }
        return "redirect:/index";
    }
}
