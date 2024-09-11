package sae.planning.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import sae.planning.pojo.Creneau;
import sae.planning.pojo.Reservation;
import sae.planning.pojo.User;
import sae.planning.repository.CreneauRepository;
import sae.planning.repository.ReservationRepository;



@Controller
class ControleurReservationDayManager {
	@Autowired ReservationRepository reservationRepository;
	@Autowired CreneauRepository creneauRepository;
	
	
	@RequestMapping("/reservationDay")
	public String service(@RequestParam String month,@RequestParam Time heure,@RequestParam String day,@RequestParam String year,@RequestParam(required = false) User user, ModelMap modelmap) {
			Calendar calendar = Calendar.getInstance();
			int mois = Integer.parseInt(month);
			int annee = Integer.parseInt(year);
			int jour = Integer.parseInt(day);
			calendar.set(Calendar.MONTH, mois);
			calendar.set(Calendar.YEAR, annee);
			calendar.set(Calendar.DAY_OF_MONTH, jour);
			Date date =  new Date(calendar.getTime().getTime());
			modelmap.put("reservations", reservationRepository.findByDateAndHeure(date,heure));
			modelmap.put("calendar", calendar);
			modelmap.put("month", month);
			modelmap.put("year", year);
			modelmap.put("day", day);
		
		
		return "creneauDay";
	}
	@Autowired
	private JavaMailSender sender;
	
	@PostMapping("/deleteAllSlot")
	public String deleteAllSlot(@RequestParam String month,@RequestParam String day,@RequestParam String year, ModelMap modelmap) throws MessagingException {
		Calendar calendar = Calendar.getInstance();
		int mois = Integer.parseInt(month);
		int annee = Integer.parseInt(year);
		int jour = Integer.parseInt(day);
		calendar.set(Calendar.MONTH, mois);
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.DAY_OF_MONTH, jour);
		Date date =  new Date(calendar.getTime().getTime());
		List<Creneau> slotday = creneauRepository.findByDate(date) ;
		List<Reservation> reserv = reservationRepository.findByDate(date);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("benoit.sae.planning@gmail.com");
		for(Reservation r : reserv) {
			try {
				helper.setTo(r.getUser().getEmail());
				helper.setSubject("Reservation Annulée");
				helper.setText("Votre reservation du " + r.getCreneau().getDate() + " à " + r.getCreneau().getHeure() + " viens d'être annulée. \nMerci de votre compréhension.");
				sender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			reservationRepository.delete(r);
		}
		for(Creneau c: slotday) {
			
			//Creneau c = creneauRepository.findById(i).get();
			System.out.println(c);
			creneauRepository.delete(c);
		}
		modelmap.put("creneaux", creneauRepository.findByDate(date));
		modelmap.put("calendar", calendar);
		modelmap.put("month", month);
		modelmap.put("year", year);
		modelmap.put("day", day);
		
		
	
		return "redirect:/planning-day?year="+year+"&month="+month+"&day="+day;
	}
	
	
	@PostMapping("/deleteSlot")
	public String deleteSlot(@RequestParam int creneau, @RequestParam String month,@RequestParam String day,@RequestParam String year, ModelMap modelmap) throws MessagingException {
		Calendar calendar = Calendar.getInstance();
		int mois = Integer.parseInt(month);
		int annee = Integer.parseInt(year);
		int jour = Integer.parseInt(day);
		calendar.set(Calendar.MONTH, mois);
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.DAY_OF_MONTH, jour);
		Date date =  new Date(calendar.getTime().getTime());
		Creneau cren = creneauRepository.findById(creneau).get();
		List<Reservation> reserv = reservationRepository.findByDate(cren.getDate());
		reservationRepository.deleteByCreneau(cren);
		creneauRepository.deleteById(creneau);
		modelmap.put("creneaux", creneauRepository.findByDate(date));
		modelmap.put("calendar", calendar);
		modelmap.put("month", month);
		modelmap.put("year", year);
		modelmap.put("day", day);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("benoit.sae.planning@gmail.com");
		reserv.forEach(r -> {
			try {
				helper.setTo(r.getUser().getEmail());
				helper.setSubject("Reservation Annulée");
				helper.setText("Votre reservation du " + cren.getDate() + " à " + cren.getHeure() + " viens d'être annulée. \nMerci de votre compréhension.");
				sender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
		
	
		return "redirect:/planning-day?year="+year+"&month="+month+"&day="+day;
	}
	
}