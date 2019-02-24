package hr.vvg.Miokovic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import hr.vvg.Miokovic.entitet.Predavac;
import hr.vvg.Miokovic.entitet.Predavanje;
import hr.vvg.Miokovic.repository.PredavacRepository;
import hr.vvg.Miokovic.repository.PredavanjeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Predstavlja kontroler klasu za unos predavanja.
 * 
 * @author Denis
 *
 */

@Slf4j
@Controller
@RequestMapping
@SessionAttributes({"vrste", "pozicije", "listaPredavanja", "objavljeno"})
public class ControllerPredavanje {
	
	@Autowired
	PredavanjeRepository predavanjeR;
	
	@Autowired
	PredavacRepository predavacR;
	
	public static List<Predavanje> listaPredavanja = new ArrayList<>();

	@RequestMapping
	public String novoPredavanje() {
		
		return "novoPredavanje";
	}
	
	@GetMapping("/novoPredavanje")
	public String showForm(Model model, Predavanje predavanje) {
		
		log.info("Punim podatke za prikaz forme.");
		
		model.addAttribute("predavanje", new Predavanje());
		model.addAttribute("vrste", Predavanje.VrstaPredavanja.values());
		model.addAttribute("pozicije", Predavac.PozicijaPredavaca.values());
		model.addAttribute("objavljeno", predavanje.getObjavljeno());

		return "novoPredavanje";
	}
	
	@GetMapping("/svaPredavanja")
	public String prikazPredavanja() {
		
		return "svaPredavanja";
	}
		
	@GetMapping("/resetBrojac")
	public String resetBrojac(SessionStatus status) {
		
		listaPredavanja.clear();
		status.setComplete();
		return "redirect:/novoPredavanje";
	}
	
	@PostMapping("/novoPredavanje")
	public String processForm(@Valid Predavanje predavanje, Errors errors, Model model) {
		
		log.info("Procesuiram predavanje: " + predavanje);
		
		if(errors.hasErrors()) {
			
			log.info("Predavanje ima grešaka. Prekida se slanje.");
			
			return "novoPredavanje";
			
		} else {
			
			listaPredavanja.add(predavanje);
			
			predavacR.save(predavanje.getPredavac());
			
			predavanjeR.save(predavanje);
			
			model.addAttribute("brojPredavanja", listaPredavanja.size());
			
			model.addAttribute("predavanje", predavanje);
			
			model.addAttribute("objavljeno", predavanje.getObjavljeno());
			
			model.addAttribute("listaPredavanja", listaPredavanja);
			
			log.info("Uspješno spremljeno predavanje: " + predavanje);

			return "ispis";
		}
	}
	
	@GetMapping("/pretragaPredavanja")
	public String pretragaPredavanja(Model model) {
		
		model.addAttribute("predavanje", new Predavanje());
		
		return "pretragaPredavanja";
	}
	
	@PostMapping("/pretragaPredavanja")
	public String pretragaPredavanja(Predavanje predavanje, Model model, String tema) {
		
		List<Predavanje> nadenaPredavanja = predavanjeR.findByTema(tema);
		model.addAttribute("nadenaPredavanja", nadenaPredavanja);
		
		return "pretragaPredavanja";
	}
}