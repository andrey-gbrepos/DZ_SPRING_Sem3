package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository // @Component для классов, работающих с данными
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public void fulTimesheetList(){
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            Timesheet timesheet = new Timesheet();
            timesheet.setId((100L + i));
            timesheet.setProjectId(random.nextLong(37, 40));
            timesheet.setMinutes((random.nextInt(60, 481)/10)*10);
            String strDate = "2024-07-0" + random.nextInt(1, 9);
            timesheet.setCreatedAt(convertStringToDate(strDate));
            timesheets.add(timesheet);
        }
    }

    private LocalDate convertStringToDate(String strDate){
        LocalDate localDate = LocalDate.now();
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(strDate, formatter);
            return localDate;
        }
        catch (Exception e) {
            System.out.println("Ошибка при преобразовании стрки в дату "
                    + e.getMessage());
        }
        return localDate;
    }

    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove); // если нет - иногда посылают 404 Not Found
    }

    public List<Timesheet> getByProjectId(Long projectId){
        List<Timesheet> tmSheets = new ArrayList<>();
        for (Timesheet item: timesheets){
            if(Objects.equals(item.getProjectId(), projectId)){
                tmSheets.add(item);
            }
        }
        return tmSheets;
    }

    public List<Timesheet> getTimesheets() {
        return timesheets;
    }


}
