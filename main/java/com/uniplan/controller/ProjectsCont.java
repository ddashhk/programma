package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Projects;
import com.uniplan.model.Tasks;
import com.uniplan.model.Users;
import com.uniplan.model.perechisl.TypeOFtask;
import com.uniplan.model.perechisl.Status;
import com.uniplan.model.perechisl.TaskStatus;
import com.uniplan.model.perechisl.vazhnost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectsCont extends Attributes {

    @GetMapping
    public String Projects(Model model) {
        AddAttributesProjects(model);
        return "projects";
    }

    @PostMapping("/add")
    public String Add(@RequestParam String name, @RequestParam TypeOFtask typeOFtask, @RequestParam vazhnost vazhnost, @RequestParam String date) {
        projectsRepo.save(new Projects(name, date, typeOFtask, vazhnost));
        return "redirect:/projects";
    }

    @PostMapping("/{id}/edit")
    public String Update(Model model,@RequestParam Status status, @PathVariable Long id) {
        Projects project = projectsRepo.getReferenceById(id);

        if (status == Status.FINISH){
            for (Tasks t : project.getTasks()){
                if (t.getStatus() != TaskStatus.FINISH){
                    model.addAttribute("message","Задачи не выполнены!");
                    AddAttributesProjects(model);
                    return "projects";
                }
            }
        }

        project.setStatus(status);
        projectsRepo.save(project);
        return "redirect:/projects";
    }
    @PostMapping("/{id}/tasks/add/")
    public String addTask(Model model,
                          @RequestParam String name,
                          @RequestParam String date,
                          @RequestParam TypeOFtask typeOFtask,
                          @RequestParam Long idEmployee,
                          @PathVariable Long id) {

        Users employee = usersRepo.findById(idEmployee)
                .orElseThrow(() -> new IllegalArgumentException("Неверный идентификатор пользователя"));

        Tasks newTask = new Tasks(name, date, typeOFtask, projectsRepo.getReferenceById(id), employee);
        tasksRepo.save(newTask);

        return "redirect:/projects/{id}/tasks/";
    }



    @GetMapping("/{id}/tasks/")
    public String Tasks(Model model, @PathVariable Long id) {
        AddAttributesTasks(model, id);
        return "tasks";
    }

    @PostMapping("/{idProject}/tasks/{idTask}/verification")
    public String TaskVerification(@PathVariable Long idProject, @PathVariable Long idTask, @RequestParam int score) {
        Tasks task = tasksRepo.getReferenceById(idTask);
        task.setStatus(TaskStatus.FINISH);
        tasksRepo.save(task);
        return "redirect:/projects/{idProject}/tasks/";
    }

    @GetMapping("/{idProject}/tasks/{idTask}/description")
    public String TaskDescription(Model model, @PathVariable Long idProject, @PathVariable Long idTask) {
        AddAttributesTaskDescription(model, idProject, idTask);
        return "tasksDescription";
    }@GetMapping("/{idProject}/tasks/{idTask}/remake")
    public String TaskRemake(Model model, @PathVariable Long idProject, @PathVariable Long idTask) {
        Tasks task = tasksRepo.getReferenceById(idTask);
        task.setStatus(TaskStatus.IN_PROGRESS);
        tasksRepo.save(task);
        return "redirect:/projects/{idProject}/tasks/";
    }

    @PostMapping("/{idProject}/tasks/{idTask}/edit/description")
    public String TaskDescriptionEdit(@PathVariable Long idProject, @PathVariable Long idTask, @RequestParam String description) {
        Tasks task = tasksRepo.getReferenceById(idTask);
        task.getDescription().setDescription(description);
        tasksRepo.save(task);
        return "redirect:/projects/{idProject}/tasks/{idTask}/description";
    }

    @GetMapping("/{idProject}/tasks/{idTask}/delete")
    public String TaskDelete(@PathVariable Long idProject, @PathVariable Long idTask) {
        tasksRepo.deleteById(idTask);
        return "redirect:/projects/{idProject}/tasks/";
    }

    @GetMapping("/projects/{idProject}/delete/{id}")
    public String Delete(@PathVariable Long id) {
        projectsRepo.deleteById(id);
        return "tasks";
    }

}
