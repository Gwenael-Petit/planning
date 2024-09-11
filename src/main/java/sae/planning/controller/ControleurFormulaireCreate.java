package sae.planning.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sae.planning.pojo.Principal;
import sae.planning.pojo.User;
import sae.planning.repository.UserRepository;


@Controller
public class ControleurFormulaireCreate {
	
	@Autowired
	UserRepository userRepository;
	
	
@GetMapping("/createAccount")
String home()
{
	
	return "newAccount";
}

@Autowired
PasswordEncoder encoder;

@PostMapping("/createAccount")
String homePost(HttpSession session,@Valid @ModelAttribute User user,BindingResult result,ModelMap modelmap) throws UnsupportedEncodingException {
    //Gestion des erreurs
	if(!userRepository.findByEmail(user.getEmail()).isEmpty()) {
		modelmap.put("error", "Cet email est déjà utilisé");
		return "newAccount";
	}
	if(result.hasFieldErrors("email")) {
		modelmap.put("error", "Ce n'est pas un email valide");
		return "newAccount";
	}
	if(result.hasFieldErrors("pwd")) {
		modelmap.put("error", "Ce n'est pas un mot de passe valide");
		return "newAccount";
	}
	if(result.hasFieldErrors("nom")) {
		modelmap.put("error", "Ce n'est pas un nom valide");
		return "newAccount";
	}
	if(result.hasFieldErrors("prenom")) {
		modelmap.put("error", "Ce n'est pas un prenom valide");
		return "newAccount";
	}
	if(result.hasFieldErrors("telephone")) {
		modelmap.put("error", "Ce n'est pas un numéro de telephone valide");
		return "newAccount";
	}
	if(result.hasFieldErrors("date_naiss")) {
		modelmap.put("error", "Ce n'est pas une date de naissance valide");
		return "newAccount";
	}
	
	
	
	String pwd = user.getPwd();
    user.setPwd(encoder.encode(pwd));
    user.setRole("user");
    user.setPhoto("upload/ProfilPicture.png");
    userRepository.save(user);
    Principal p = new Principal(user.getUno(), user.getEmail(), user.getRole());
    session.setAttribute("user", p);
    Calendar calendar = Calendar.getInstance();
	modelmap.put("month", ""+calendar.get(Calendar.MONTH));
	modelmap.put("year", ""+calendar.get(Calendar.YEAR));
	modelmap.remove("fail");
	return "Planning";
}
}
