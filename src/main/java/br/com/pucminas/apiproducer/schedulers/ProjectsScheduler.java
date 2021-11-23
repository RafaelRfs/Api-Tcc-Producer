package br.com.pucminas.apiproducer.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;

@Slf4j
@Configuration
@EnableScheduling
public class ProjectsScheduler {


    @Scheduled(initialDelayString = "${app.schedule.initial_delay_time}", fixedDelayString = "${app.schedule.fixed_delay_time}")
    public void processEmailsNotSended(){
        log.info("Iniciando routina de reprocessamento de emails as {} ", Calendar.getInstance().getTime());

        log.info("Termino da rotina de reprocessamento de email as {}", Calendar.getInstance().getTime());
    }







}
