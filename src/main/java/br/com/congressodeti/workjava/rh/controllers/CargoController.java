package br.com.congressodeti.workjava.rh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.repositories.CargoRepository;
import br.com.congressodeti.workjava.rh.validacoes.cargo.ValidadorCargo;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	private final CargoRepository repository;
	private final List<ValidadorCargo> validadores;
	
	public CargoController(CargoRepository repository, List<ValidadorCargo> validadores) {
		this.repository = repository;
		this.validadores = validadores;
	}

	@GetMapping
	public String lista(Model model) {
		List<Cargo> todos = repository.findAll();
		model.addAttribute("cargos", todos);
		return "cargo/lista";
	}
	
	@GetMapping("/form")
	public String formulario() {
		return "cargo/form";
	}
	
	@PostMapping
	public String salvar(Cargo novo, Model model, RedirectAttributes attributes) {
		try {
			validadores.stream().forEach(v -> v.valida(novo));
			this.repository.save(novo);
			attributes.addFlashAttribute("msgSucesso", "Cargo cadastrado!");
			return "redirect:/cargos";
		} catch (BusinessException e) {
			model.addAttribute("msgErro", e.getMessage());
			return "cargo/form";
		}
	}
	
	@DeleteMapping
	public String excluir(Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("msgSucesso", "Cargo excluido!");
		return "redirect:/cargos";
	}

}
