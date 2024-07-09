package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    public Project create(Project project) {
        project.setId(sequence++);
        projects.add(project);
        return project;
    }

    public void delete(Long id) {
        projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(projects::remove);
    }

    public List<Project> getProjects() {
        return projects;
    }


    public void fulProjectList() {
        Project project1 = new Project();project1.setId(37L);project1.setName("windows");
        projects.add(project1);
        Project project2 = new Project();project2.setId(38L);project2.setName("linux");
        projects.add(project2);
        Project project3 = new Project(); project3.setId(39L); project3.setName("macos");
        projects.add(project3);
    }


}
