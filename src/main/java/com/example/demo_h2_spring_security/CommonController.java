package com.example.demo_h2_spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String processRegistration(@ModelAttribute User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.addRoles(roleRepository.findById(1L).get());
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}
}

