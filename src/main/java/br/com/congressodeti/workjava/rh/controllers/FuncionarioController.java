package br.com.congressodeti.workjava.rh.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.models.Reajuste;
import br.com.congressodeti.workjava.rh.repositories.CargoRepository;
import br.com.congressodeti.workjava.rh.repositories.ReajusteRepository;
import br.com.congressodeti.workjava.rh.services.funcionario.FuncionarioService;
import br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste.ValidadorReajuste;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private final CargoRepository cargoRepository;
	private final ReajusteRepository reajusteRepository;
	private final FuncionarioService funcionarioService;
	private final List<ValidadorReajuste> validadoresReajuste;
	
	public FuncionarioController(CargoRepository cargoRepository, ReajusteRepository reajusteRepository, FuncionarioService funcionarioService, List<ValidadorReajuste> validadoresReajuste) {
		this.cargoRepository = cargoRepository;
		this.reajusteRepository = reajusteRepository;
		this.funcionarioService = funcionarioService;
		this.validadoresReajuste = validadoresReajuste;
	}

	@GetMapping
	public String lista(Model model) {
		List<Funcionario> todos = funcionarioService.listarTodos();
		model.addAttribute("funcionarios", todos);
		return "funcionario/lista";
	}
	
	@GetMapping("/form")
	public String formulario(Model model) {
		model.addAttribute("cargos", cargoRepository.findAll());
		return "funcionario/form";
	}
	
	@PostMapping
	public String salvar(Funcionario novo, Model model) {
		try {
			funcionarioService.salvar(novo);
			return "redirect:/funcionarios";
		} catch (BusinessException e) {
			model.addAttribute("msgErro", e.getMessage());
			return "funcionario/form";
		}
	}
	
	@DeleteMapping
	public String excluir(Long id) {
		funcionarioService.excluir(id);
		return "redirect:/funcionarios";
	}
	
	@GetMapping("/{id}/reajustes")
	public String reajustes(@PathVariable("id") Long id, Model model) {
		Funcionario selecionado = funcionarioService.buscarPorId(id);
		List<Reajuste> reajustesConcedidos = reajusteRepository.findAllByFuncionario(selecionado);
		model.addAttribute("funcionario", selecionado);
		model.addAttribute("reajustes", reajustesConcedidos);
		return "funcionario/reajuste";
	}
	
	@PostMapping("/{id}/reajustes")
	public String reajustar(@PathVariable("id") Long id, Reajuste novo, RedirectAttributes model) {
		Funcionario selecionado = funcionarioService.buscarPorId(id);
		novo.setFuncionario(selecionado);
		
		try {
			validadoresReajuste.forEach(v -> v.valida(novo));
			reajusteRepository.save(novo);
			return "redirect:/funcionarios";
		} catch (BusinessException e) {
			model.addAttribute("msgErro", e.getMessage());
			return "funcionario/reajuste";
		}
		
	}

}
