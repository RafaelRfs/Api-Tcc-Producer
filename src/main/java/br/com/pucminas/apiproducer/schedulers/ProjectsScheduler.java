package br.com.pucminas.apiproducer.schedulers;

import br.com.pucminas.apiproducer.dtos.HealthCheckDto;
import br.com.pucminas.apiproducer.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Calendar;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ProjectsScheduler {

    private final ProjectService projectService;
    private final RestTemplate restTemplate;

    @Scheduled(initialDelayString = "${app.schedule.initial_delay_time}", fixedDelayString = "${app.schedule.fixed_delay_time}")
    public void processProjectsWithShortDeadline(){
        log.info("Iniciando rotina procesamento de projetos  as {} ", Calendar.getInstance().getTime());
        projectService.processProjectsByDeadlineDateBetween(LocalDate.now(),  (LocalDate.now()).plusDays(5));
        verifyHealthCheckConsumer();
        log.info("Termino da rotina procesamento de projetos as {}", Calendar.getInstance().getTime());
    }

    public void verifyHealthCheckConsumer(){
        try{

            ResponseEntity<HealthCheckDto> healthCheckDtoResponse = restTemplate
                    .exchange(
                            "https://api-consumer-emails-tcc.herokuapp.com/actuator/health",
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            HealthCheckDto.class
                    );

            log.info("Health Check Consumer Sucess: {} ",healthCheckDtoResponse.getBody().getStatus());

        } catch (Exception e){
            log.error("HealthCheck Consumer Error: {}", e.getMessage());
        }
    }

}
