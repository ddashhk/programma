package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Tasks;
import com.uniplan.model.perechisl.TaskStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TasksCont extends Attributes {

    @GetMapping
    public String TasksEmployee(Model model) {
        AddAttributesTasksEmployee(model);
        return "tasksEmployee";
    }

    @GetMapping("/{id}/description")
    public String TasksEmployeeDescription(Model model, @PathVariable Long id) {
        AddAttributesTasksEmployeeDescription(model, id);
        return "tasksEmployeeDescription";
    }

    @PostMapping("/{id}/edit")
    public String TasksEmployeeEdit(@PathVariable Long id, @RequestParam TaskStatus  status) {
        Tasks task = tasksRepo.getReferenceById(id);
        task.setStatus(status);
        tasksRepo.save(task);
        return "redirect:/tasks";
    }
}
