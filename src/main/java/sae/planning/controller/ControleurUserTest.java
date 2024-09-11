package sae.planning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sae.planning.repository.UserRepository;

@Controller
public class ControleurUserTest {

	@Autowired
	private UserRepository userRepository;
	@ResponseBody
	@RequestMapping("test")
	public String hello() {
		userRepository.findAll();
		return userRepository.findAll().toString();
	}
	
}
