package ru.gb.timesheet.model;

import lombok.Data;

@Data
public class Project {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}