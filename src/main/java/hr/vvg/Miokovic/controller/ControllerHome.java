package hr.vvg.Miokovic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * Predstavlja kontroler klasu za "Home" stranicu.
 * 
 * @author Denis
 *
 */

@Controller
@RequestMapping()
public class ControllerHome {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
}