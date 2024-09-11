package sae.planning.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import sae.planning.pojo.Principal;
import sae.planning.pojo.User;
import sae.planning.repository.UserRepository;

@Controller
public class SpringFileUploadController {

	@Autowired
	ServletContext context;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/upload")
	public String upload() {
		return "uploader";
	}

	@PostMapping("/upload")
	public String handleFileUpload(HttpSession session, @RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		session.getServletContext().getContextPath();
		Principal p = (Principal) session.getAttribute("user");
		User user = userRepository.findByEmail(p.getLogin()).get();
		user.setPhoto("upload/" + fileName);
		userRepository.save(user);
		try {
			file.transferTo(new File(context.getRealPath("upload/") + fileName));
		} catch (Exception e) {
			return "redirect:/upload";
		}

		return "redirect:/modifAccount";
	}

}
