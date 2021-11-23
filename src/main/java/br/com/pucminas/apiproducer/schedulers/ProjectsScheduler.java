package br.com.pucminas.apiproducer.schedulers;

import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDate;
import java.util.Calendar;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ProjectsScheduler {

    private final ProjectService projectService;

    @Scheduled(initialDelayString = "${app.schedule.initial_delay_time}", fixedDelayString = "${app.schedule.fixed_delay_time}")
    public void processProjectsWithShortDeadline(){
        log.info("Iniciando rotina procesamento de projetos  as {} ", Calendar.getInstance().getTime());
        projectService.processProjectsByDeadlineDateBetween(LocalDate.now(),  (LocalDate.now()).plusDays(5));
        log.info("Termino da rotina procesamento de projetos  de email as {}", Calendar.getInstance().getTime());
    }

}
