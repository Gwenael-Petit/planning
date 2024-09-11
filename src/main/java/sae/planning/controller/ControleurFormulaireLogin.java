package sae.planning.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sae.planning.pojo.Principal;
import sae.planning.pojo.User;
import sae.planning.repository.UserRepository;



@Controller
public class ControleurFormulaireLogin {
	
	@Autowired
	UserRepository userRepository;
	

	@GetMapping("/deconnexion")
	String deconnexion(HttpSession session) {
		
		session.removeAttribute("user");
		return "redirect:/logout";
	}

	
@GetMapping("/loginAccount")
String home()
{
	
	return "login";
}

@Autowired
PasswordEncoder encoder;
@PostMapping("/loginAccount")
String homePost(@RequestParam String password,@RequestParam String username,HttpSession session,ModelMap modelmap,RedirectAttributes attributes) throws UnsupportedEncodingException {
    	Optional<User> user = userRepository.findByEmail(username);
    	User result = user.get();
    	
    	
    	if(result != null && encoder.matches(password, result.getPwd())) {
    		Principal connectedUser = new Principal(result.getUno(),result.getEmail(), result.getRole());
        	attributes.addFlashAttribute("user",connectedUser);
        	session.setAttribute("user", connectedUser);
    		Calendar calendar = Calendar.getInstance();
    		attributes.addFlashAttribute("month",""+calendar.get(Calendar.MONTH));
    		attributes.addFlashAttribute("year",""+calendar.get(Calendar.YEAR));
    		return "redirect:/planning";
    	}else {
    		modelmap.put("fail", "true");
    		return "redirect:/loginAccount";    		
    	}

	
   
	
}
}
