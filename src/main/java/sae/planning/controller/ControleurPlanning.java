package sae.planning.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sae.planning.repository.CreneauRepository;
import sae.planning.repository.ParametresRepository;

@Controller
class ControleurPlanning {
	@Autowired
	CreneauRepository creneauRepository;
	@Autowired
	ParametresRepository parametresRepository;
	
	@RequestMapping(value = {"/","/planning"})
	public String service(@RequestParam(required = false) String month,@RequestParam(required = false) String year, ModelMap modelmap) {
		Calendar calendar = Calendar.getInstance();
		if(month==null) {
			modelmap.put("month", ""+calendar.get(Calendar.MONTH));
		}else {
			modelmap.put("month", month);
		}
		if(month==null) {
			modelmap.put("year", ""+calendar.get(Calendar.YEAR));
		}else {
			modelmap.put("year", year);
		}
		return "Planning";		
		
		
	}
	

	
}