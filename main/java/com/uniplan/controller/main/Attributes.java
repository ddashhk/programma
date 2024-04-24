package com.uniplan.controller.main;

import com.uniplan.model.perechisl.*;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("categories", TypeOFtask.values());
        model.addAttribute("urgencies", vazhnost.values());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("taskStatuses", TaskStatus.values());
    }

    protected void AddAttributesTasks(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("project", projectsRepo.getReferenceById(id));
        model.addAttribute("employees", usersRepo.findAllByRole(Role.EMPLOYEE));
    }

    protected void AddAttributesTasksEmployeeDescription(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("task", tasksRepo.getReferenceById(id));
    }

    protected void AddAttributesTaskDescription(Model model, Long idProject, Long idTask) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("project", projectsRepo.getReferenceById(idProject));
        model.addAttribute("task", tasksRepo.getReferenceById(idTask));
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
    }

    protected void AddAttributesTasksEmployee(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesProjects(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("projects", projectsRepo.findAll());
    }

    protected void AddAttributesProfiles(Model model) {
        AddAttributes(model);
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
    }

    protected void AddAttributesAddUser(Model model) {
        AddAttributes(model);
        model.addAttribute("roles", Role.values());
    }
}
