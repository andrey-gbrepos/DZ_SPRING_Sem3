package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    private final TimesheetService tsheetService;

    public ProjectController(ProjectService service, TimesheetService tsheetService) {
        this.service = service;
        this.tsheetService = tsheetService;
    }

    @GetMapping("/{id}") // получить все
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> ts = service.getById(id);

        if (ts.isPresent()) {
            //      return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping // получить все
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping // создание нового ресурса
    public ResponseEntity<Project> create(@RequestBody Project project) {
        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timesheets") // получить все по id проекта
    public ResponseEntity<List<Timesheet>> getByProjId(@PathVariable Long id) {
        return ResponseEntity.ok(tsheetService.getByProjectId(id));
    }

}
