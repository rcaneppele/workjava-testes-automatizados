package br.com.congressodeti.workjava.rh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;
import br.com.congressodeti.workjava.rh.repositories.CargoRepository;
import br.com.congressodeti.workjava.rh.validadores.cargo.ValidadorCadastroCargo;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	private final CargoRepository repository;
	private final List<ValidadorCadastroCargo> validadoresCadastroCargo;
	
	public CargoController(CargoRepository repository, List<ValidadorCadastroCargo> validadoresCadastroCargo) {
		this.repository = repository;
		this.validadoresCadastroCargo = validadoresCadastroCargo;
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
	public String salvar(Cargo novo, Model model) {
		try {
			validadoresCadastroCargo.forEach(v -> v.validar(novo));
			return "redirect:/cargos";
		} catch (BusinessException e) {
			model.addAttribute("msgErro", e.getMessage());
			return "cargo/form";
		}
	}
	
	@DeleteMapping
	public String excluir(Long id) {
		repository.deleteById(id);
		
		return "redirect:/cargos";
	}

}
