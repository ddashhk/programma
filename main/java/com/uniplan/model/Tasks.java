package com.uniplan.model;

import com.uniplan.model.perechisl.TypeOFtask;
import com.uniplan.model.perechisl.TaskStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tasks {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    @Enumerated(EnumType.STRING)
    private TypeOFtask typeOFtask;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Projects project;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users employee;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TaskDescription description;

    public Tasks(String name, String date, TypeOFtask typeOFtask, Projects project, Users employee) {
        this.name = name;
        this.date = date;
        this.typeOFtask = typeOFtask;
        this.status = TaskStatus.WAITING;
        this.project = project;
        this.employee = employee;
        this.description = new TaskDescription("");
    }
}
