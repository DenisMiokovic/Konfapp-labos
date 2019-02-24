package hr.vvg.Miokovic.jobs;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import hr.vvg.Miokovic.controller.ControllerPredavanje;
import hr.vvg.Miokovic.entitet.Predavanje;

@Component
public class UnosPredavanjaJob implements Job {
	
	List<Predavanje> lista = ControllerPredavanje.listaPredavanja;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
				
		for (Predavanje predavanje : lista) {
			
			Duration duration = Duration.between(predavanje.getVrijemeUnosa(), LocalDateTime.now()).abs();
			
			if (duration.getSeconds() < 10) {
				
				System.out.println("Objavljeno je novo predavanje: " + predavanje.getTema());
			}
		}
	}
}
