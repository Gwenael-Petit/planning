package sae.planning.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sae.planning.pojo.User;
import sae.planning.repository.CreneauRepository;



@Controller
class ControleurDay {
	@Autowired CreneauRepository creneauRepository;
	
	
	@RequestMapping("/planning-day")
	public String service(@RequestParam String month,@RequestParam String day,@RequestParam String year,@RequestParam(required = false) User user,@RequestParam(required = false) String fail, ModelMap modelmap) {
			Calendar calendar = Calendar.getInstance();
			int mois = Integer.parseInt(month);
			int annee = Integer.parseInt(year);
			int jour = Integer.parseInt(day);
			calendar.set(Calendar.MONTH, mois);
			calendar.set(Calendar.YEAR, annee);
			calendar.set(Calendar.DAY_OF_MONTH, jour);
			Date date =  new Date(calendar.getTime().getTime());
			modelmap.put("creneaux", creneauRepository.findByDate(date));
			modelmap.put("calendar", calendar);
			modelmap.put("fail", fail);
			modelmap.put("month", month);
			modelmap.put("year", year);
			modelmap.put("day", day);
		
		
		return "day";
	}
	
}