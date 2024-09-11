package sae.planning.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import sae.planning.pojo.Creneau;
import sae.planning.pojo.Principal;
import sae.planning.pojo.Reservation;
import sae.planning.pojo.User;
import sae.planning.repository.CreneauRepository;
import sae.planning.repository.ReservationRepository;
import sae.planning.repository.UserRepository;

@Controller
public class ControleurReservation {

	@Autowired ReservationRepository reservationRepository;
	@Autowired CreneauRepository creneauRepository;
	@Autowired UserRepository userRepository;
	
	@GetMapping("/reserve")
	String reserve(ModelMap modelmap,HttpSession session) {
		Principal p = (Principal) session.getAttribute("user");
		if(p == null) {
			return "login";
		}
		modelmap.put("reservations", reservationRepository.findByUser(p.getId()));
		return  "userReservation";/*reservationRepository.findAll()+" "*/
	}
	
	@PostMapping("/reserve")
	String reserve(Integer id,int nb_personnes, String year, String month, String day, HttpSession session, ModelMap modelmap) {
		
		Optional<Creneau> c = creneauRepository.findById(id);
		Principal p = (Principal) session.getAttribute("user");
		if(p == null) {
			return "login";
		}
		
		User u = userRepository.findById(p.getId()).get();
		
		Reservation r = new Reservation(u, c.get(),nb_personnes);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + creneauRepository.findById(r.getCreneau().getCno()));
		if(reservationRepository.findByUnoCno(u.getUno(),r.getCreneau().getCno()) != null) {
			modelmap.put("reservations", reservationRepository.findByUser(u.getUno()));
			
			return  "userReservation";
		}else {
			if(c.get().getPlaces_restantes() > 0 && nb_personnes <= c.get().getPlaces_restantes()) {
				c.get().setPlaces_restantes(c.get().getPlaces_restantes()-nb_personnes);
			}else {
					
				return "redirect:/planning-day?month="+month+"&year="+year+"&day="+day+"&fail=true";
			}
				
				
			creneauRepository.save(c.get());
			reservationRepository.save(r);
			System.out.println("Reservation:"+r);
			modelmap.put("reservations", reservationRepository.findByUser(u.getUno()));
			
			return  "userReservation";/*reservationRepository.findAll()+" "*/			
		}
	}
	
	@RequestMapping("/annule")
	String annule( int uno, int cno, HttpSession session, ModelMap modelmap) {
		System.out.println("hesqqsffil HIIIIIIIsfqsIIIIIIIIIIIIIIIIIIIIIIIIsqsdqfIIIIIIIsIIIIIIIIdsdfqsfffqqIIIIIIITL"+uno+" "+cno);
		Principal p = (Principal) session.getAttribute("user");
		Reservation oldR = reservationRepository.findByUnoCno(uno, cno);
		Creneau c = creneauRepository.findById(cno).get();
		c.setPlaces_restantes(c.getPlaces_restantes() + oldR.getNb_personnes());
		creneauRepository.save(c);
		oldR.setAnnulee(true);
		reservationRepository.save(oldR);
		modelmap.put("reservations", reservationRepository.findByUser(p.getId()));
		
		return  "userReservation";/*reservationRepository.findAll()+" "*/
	}
	
	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("/annuleManager")
	String annuleManager(@RequestParam String month,@RequestParam String year,@RequestParam String day,int uno, int cno, HttpSession session, ModelMap modelmap) throws MessagingException {
		Principal p = (Principal) session.getAttribute("user");
		Reservation oldR = reservationRepository.findByUnoCno(uno, cno);
		Creneau c = creneauRepository.findById(cno).get();
		c.setPlaces_restantes(c.getPlaces_restantes() + oldR.getNb_personnes());
		oldR.setAnnulee(true);
		reservationRepository.save(oldR);
		Calendar calendar = Calendar.getInstance();
		int mois = Integer.parseInt(month);
		int annee = Integer.parseInt(year);
		int jour = Integer.parseInt(day);
		calendar.set(Calendar.MONTH, mois);
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.DAY_OF_MONTH, jour);
		Date date =  new Date(calendar.getTime().getTime());
		modelmap.put("reservations", reservationRepository.findByDateAndHeure(date,oldR.getCreneau().getHeure()));
		modelmap.put("calendar", calendar);
		modelmap.put("month", month);
		modelmap.put("year", year);
		modelmap.put("day", day);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("benoit.sae.planning@gmail.com");
		helper.setTo(oldR.getUser().getEmail());
		helper.setSubject("Reservation Annulée");
		helper.setText("Votre reservation du " + oldR.getCreneau().getDate() + " à " + oldR.getCreneau().getHeure() + " viens d'être annulée. \nMerci de votre compréhension.");
		sender.send(message);

		return  "creneauDay";/*reservationRepository.findAll()+" "*/
	}

	
}
