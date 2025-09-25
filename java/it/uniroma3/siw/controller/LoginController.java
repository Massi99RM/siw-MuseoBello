package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Pagina di login
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// Pagina di registrazione
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	// Salvataggio nuovo utente
	@PostMapping("/register")
	public String register(User user, Model model) {
		// cripta la password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// ruolo di default = USER
		user.setRole(User.Role.USER);

		try {
			userRepository.save(user);
			model.addAttribute("successMessage", "Registrazione completata! Ora effettua il login.");
			return "login";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "⚠️ Username già esistente! Scegline un altro.");
			return "register";
		}
	}

	// Homepage unificata per tutti gli utenti
	@GetMapping("/home")
	public String home() {
		return "home";
	}

	// Smista tutti gli utenti alla homepage unificata
	@GetMapping("/default")
	public String defaultAfterLogin(Authentication authentication) {
		System.out.println("=== DEBUG LOGIN ===");
		System.out.println("Username: " + authentication.getName());
		System.out.println("Authorities: " + authentication.getAuthorities());
		System.out.println("Authorities come Set: " + AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		System.out.println("==================");
		
		// Tutti gli utenti vanno alla homepage unificata
		return "redirect:/home";
	}
}