package dev.jonkursani.restapigr2.schedulers;

import dev.jonkursani.restapigr2.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//        ┌───────────── second (0-59)
//        │ ┌───────────── minute (0 - 59)
    //    │ │ ┌───────────── hour (0 - 23)
    //    │ │ │ ┌───────────── day of the month (1 - 31)
    //    │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
    //    │ │ │ │ │ ┌───────────── day of the week (0 - 7)
    //    │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
//        │ │ │ │ │ │
//        * * * * * *
@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeReportScheduler {
    private final EmployeeRepository employeeRepository;

    // Every monday 9 AM
    @Scheduled(cron = "* * 9 * * MON")
    // Every 10 seconds
//    @Scheduled(fixedRate = 10000)
    public void generateEmployeeReport() {
        log.info("Generating employee report...");

        var employees = employeeRepository.findAll();

        employees.forEach(employee -> {
            log.info("Employee first name: {} Last name: {}, is active", employee.getFirstName(), employee.getLastName());
        });

        log.info("Employee report generated successfully!");
    }
}