package sae.planning.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sae.planning.pojo.Creneau;
import sae.planning.pojo.JoursTravailles;
import sae.planning.pojo.Parametres;
import sae.planning.repository.CreneauRepository;
import sae.planning.repository.ParametresRepository;

@Controller
public class ControleurParametresPlanning {

	@Autowired
	CreneauRepository creneauRepository;
	@Autowired
	ParametresRepository paraRepository;

	@GetMapping("/addPlanning")
	String home(ModelMap modelmap) {

		return "addPlanning";
	}

	@PostMapping("/addPlanning")
	String homePost(@RequestParam(required = false) String month,@RequestParam(required = false) String year,@Valid @ModelAttribute Parametres parametres,BindingResult result, @ModelAttribute JoursTravailles j1,
			@RequestParam(required = false, defaultValue = "100") String lundi,
			@RequestParam(required = false, defaultValue = "100") String mardi,
			@RequestParam(required = false, defaultValue = "100") String mercredi,
			@RequestParam(required = false, defaultValue = "100") String jeudi,
			@RequestParam(required = false, defaultValue = "100") String vendredi,
			@RequestParam(required = false, defaultValue = "100") String samedi,
			@RequestParam(required = false, defaultValue = "100") String dimanche, HttpSession session,
			ModelMap modelmap) throws UnsupportedEncodingException {
		
		
		if(result.hasErrors()) {
			result.hasFieldErrors("nb_places");
			modelmap.put("error", "nombre de place trop petit");
			return "addPlanning";
		}
		session.setAttribute("parametres", parametres);
		paraRepository.save(parametres);
		List<Creneau> listCreneaux = new ArrayList<Creneau>();

		List<Integer> jourstravail = new ArrayList<Integer>();
		System.out.println(lundi+" "+mardi+" "+ mercredi+" "+jeudi+" "+vendredi+" "+samedi+" "+dimanche);
		for (String s : List.of(lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche)) {
			int svalue = Integer.parseInt(s);
			if (svalue != 100) {
				jourstravail.add(svalue);
			}
		}
		
		Calendar dateactu = Calendar.getInstance();
		Calendar datefin = (Calendar) dateactu.clone();
		datefin.add(Calendar.YEAR, 1);
		while (dateactu.getTime().before(datefin.getTime())) {
			if (jourstravail.contains(dateactu.get(Calendar.DAY_OF_WEEK))) {
				if(j1.getDebutmat() != null && j1.getFinmat() != null) {
					dateactu.set(Calendar.HOUR_OF_DAY, j1.getDebutmat().getHour());
					dateactu.set(Calendar.MINUTE, j1.getDebutmat().getMinute());
					while (dateactu.get(Calendar.HOUR_OF_DAY) < j1.getFinmat().getHour()
							|| (dateactu.get(Calendar.HOUR_OF_DAY) == j1.getFinmat().getHour()
									&& dateactu.get(Calendar.MINUTE) < j1.getFinmat().getMinute())) {
						LocalDate date = LocalDate.of(dateactu.get(Calendar.YEAR), dateactu.get(Calendar.MONTH) + 1,
								dateactu.get(Calendar.DAY_OF_MONTH));
						LocalTime time = LocalTime.of(dateactu.get(Calendar.HOUR_OF_DAY), dateactu.get(Calendar.MINUTE), 0);
						listCreneaux.add(new Creneau(Date.valueOf(date), Time.valueOf(time), parametres.getNb_places(),
								parametres.getDuree()));
						dateactu.add(Calendar.MINUTE, parametres.getDuree());
					}
				}
				
				if(j1.getDebutap() != null && j1.getFinap() != null) {
					dateactu.set(Calendar.HOUR_OF_DAY, j1.getDebutap().getHour());
					dateactu.set(Calendar.MINUTE, j1.getDebutap().getMinute());
					while (dateactu.get(Calendar.HOUR_OF_DAY) < j1.getFinap().getHour()
							|| (dateactu.get(Calendar.HOUR_OF_DAY) == j1.getFinap().getHour()
									&& dateactu.get(Calendar.MINUTE) < j1.getFinap().getMinute())) {
						LocalDate date = LocalDate.of(dateactu.get(Calendar.YEAR), dateactu.get(Calendar.MONTH) + 1,
								dateactu.get(Calendar.DAY_OF_MONTH));
						LocalTime time = LocalTime.of(dateactu.get(Calendar.HOUR_OF_DAY), dateactu.get(Calendar.MINUTE), 0);
						listCreneaux.add(new Creneau(Date.valueOf(date), Time.valueOf(time), parametres.getNb_places(),
								parametres.getNb_places()));
						dateactu.add(Calendar.MINUTE, parametres.getDuree());
					}
				}
				
				
			}
			dateactu.add(Calendar.DAY_OF_MONTH, 1);
		}

		creneauRepository.saveAll(listCreneaux);
		/*Calendar calendar = Calendar.getInstance();
		if(month==null) {
			modelmap.put("month", ""+calendar.get(Calendar.MONTH));
		}else {
			modelmap.put("month", month);
		}
		if(month==null) {
			modelmap.put("year", ""+calendar.get(Calendar.YEAR));
		}else {
			modelmap.put("year", year);
		}*/
		return "redirect:/planning";

		

	}

}
