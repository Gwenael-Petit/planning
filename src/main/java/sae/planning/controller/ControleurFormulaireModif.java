package sae.planning.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sae.planning.pojo.User;
import sae.planning.repository.UserRepository;



@Controller
public class ControleurFormulaireModif {
	
	@Autowired
	UserRepository userRepository;
	
	
@GetMapping("/modifAccount")
String home(HttpServletRequest request,ModelMap modelmap,HttpSession session)
{
	
	Principal p = request.getUserPrincipal();
	User user = userRepository.findByEmail(p.getName()).get();
	modelmap.put("user", user);
	return "modifAccount";
}
@Autowired
PasswordEncoder encoder;
@PostMapping("/modifAccount")
String homePost(HttpServletRequest request,HttpSession session,@Valid @ModelAttribute User user,BindingResult result,@RequestParam String pwdNew,@RequestParam String pwdConfi,ModelMap modelmap) throws UnsupportedEncodingException {
	 //Gestion des erreurs
		
		
		if(result.hasFieldErrors("nom")) {
			modelmap.put("error", "Ce n'est pas un nom valide");
			return "modifAccount";
		}
		if(result.hasFieldErrors("prenom")) {
			modelmap.put("error", "Ce n'est pas un prenom valide");
			return "modifAccount";
		}
		if(result.hasFieldErrors("telephone")) {
			modelmap.put("error", "Ce n'est pas un numéro de telephone valide");
			return "modifAccount";
		}
		if(result.hasFieldErrors("date_naiss")) {
			modelmap.put("error", "Ce n'est pas une date de naissance valide");
			return "modifAccount";
		}
	
    	Principal p = request.getUserPrincipal();
    	User userNew = userRepository.findByEmail(p.getName()).get();
    	
    	if(user.getPwd() != "" || pwdNew != "" || pwdConfi != "") {
    		if( encoder.matches(user.getPwd(), userNew.getPwd()) && pwdNew.equals(pwdConfi)) {
    			userNew.setPwd(encoder.encode(pwdNew));
    			userNew.setEmail(user.getEmail());
    			userNew.setNom(user.getNom());
    			userNew.setPrenom(user.getPrenom());
    			userNew.setTelephone(user.getTelephone());
    			userNew.setDate_naiss(user.getDate_naiss());
    			userRepository.save(userNew);
    			
    			
    			
    			session.setAttribute("user", userNew);
    			modelmap.put("fail", "false");
    			return "redirect:/planning";
    		}else {
    			modelmap.put("fail", "Mauvais mot de passe !");
    			return "modifAccount";
    		}
    	}else {
    		userNew.setEmail(user.getEmail());
    		userNew.setNom(user.getNom());
    		userNew.setPrenom(user.getPrenom());
    		userNew.setTelephone(user.getTelephone());
    		userNew.setDate_naiss(user.getDate_naiss());
    		userRepository.save(userNew);
    		modelmap.put("user", userNew);
			modelmap.put("fail", "false");
			/*Calendar calendar = Calendar.getInstance();
    		modelmap.put("month", ""+calendar.get(Calendar.MONTH));
    		modelmap.put("year", ""+calendar.get(Calendar.YEAR));*/
    		return "redirect:/planning";		
    	}
	
   
	
}
}
