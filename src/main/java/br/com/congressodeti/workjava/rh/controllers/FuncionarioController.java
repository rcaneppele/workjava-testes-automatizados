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

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.model.Reajuste;
import br.com.congressodeti.workjava.rh.repositories.CargoRepository;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;
import br.com.congressodeti.workjava.rh.repositories.ReajusteRepository;
import br.com.congressodeti.workjava.rh.services.funcionario.CadastroFuncionarioService;
import br.com.congressodeti.workjava.rh.validadores.funcionario.reajuste.ValidadorReajusteSalarial;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final ReajusteRepository reajusteRepository;
	private final CadastroFuncionarioService cadastroFuncionarioService;
	private final List<ValidadorReajusteSalarial> validadores;
	
	public FuncionarioController(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, ReajusteRepository reajusteRepository, CadastroFuncionarioService cadastroFuncionarioService, List<ValidadorReajusteSalarial> validadores) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.reajusteRepository = reajusteRepository;
		this.cadastroFuncionarioService = cadastroFuncionarioService;
		this.validadores = validadores;
	}

	@GetMapping
	public String lista(Model model) {
		List<Funcionario> todos = funcionarioRepository.findAll();
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
			cadastroFuncionarioService.salvar(novo);
			return "redirect:/funcionarios";
		} catch (BusinessException e) {
			model.addAttribute("msgErro", e.getMessage());
			return "funcionario/form";
		}
	}
	
	@DeleteMapping
	public String excluir(Long id) {
		funcionarioRepository.deleteById(id);
		
		return "redirect:/funcionarios";
	}
	
	@GetMapping("/{id}/reajustes")
	public String reajustes(@PathVariable("id") Long id, Model model) {
		Funcionario selecionado = funcionarioRepository.getOne(id);
		List<Reajuste> reajustesConcedidos = reajusteRepository.findAllByFuncionario(selecionado);
		model.addAttribute("funcionario", selecionado);
		model.addAttribute("reajustes", reajustesConcedidos);
		return "funcionario/reajuste";
	}
	
	@PostMapping("/{id}/reajustes")
	public String reajustar(@PathVariable("id") Long id, Reajuste novo, RedirectAttributes model) {
		try {
			Funcionario selecionado = funcionarioRepository.getOne(id);
			novo.setFuncionario(selecionado);
			validadores.forEach(v -> v.validar(novo));
			reajusteRepository.save(novo);
			return "redirect:/funcionarios";
		} catch (BusinessException e) {
			model.addFlashAttribute("msgErro", e.getMessage());
			return "redirect:/funcionarios/"+id +"/reajustes";
		}
	}

}
