package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;


import java.util.List;
import java.util.Optional;

@Service // то же самое, что и Component
public class TimesheetService {

    private final TimesheetRepository repository;
private  final ProjectRepository projRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projRepository) {
        this.repository = repository;
        this.projRepository = projRepository;
        repository.fulTimesheetList();
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if(!existProject(timesheet)){
            timesheet.setId(-1L);
            return timesheet;
        }
        return repository.create(timesheet);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public TimesheetRepository getRepository() {
        return repository;
    }

    private Boolean existProject(Timesheet timesheet){
        for (Project item:projRepository.getProjects()){
            if(item.getId().equals(timesheet.getProjectId())) return true;
        }
//        if(projectService.getRepository().getProjects().stream()
//                .map(it -> it.getId())
//                .toList().contains(timesheet.getProjectId())){
//            return true;
//        }
        return false;
    }
    public List<Timesheet> getByProjectId(Long projectId) {
        return   repository.getByProjectId(projectId);
    }
}
