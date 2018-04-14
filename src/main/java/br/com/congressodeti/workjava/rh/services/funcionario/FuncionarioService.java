package br.com.congressodeti.workjava.rh.services.funcionario;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.repositories.CargoRepository;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
	}
	
	public void salvar(Funcionario novo) {
		BigDecimal salario = novo.getSalario();
		Cargo cargo = cargoRepository.getOne(novo.getCargo().getId());
		
		if (salario.compareTo(cargo.getSalarioMinimo()) < 0) {
			throw new BusinessException("salario do funcionario nao pode ser menor do que salario minimo de seu cargo");
		}
		
		if (salario.compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("salario do funcionario nao pode ser maior do que salario maximo de seu cargo");
		}
		
		funcionarioRepository.save(novo);
	}
	
	public Funcionario buscarPorId(Long id) {
		return funcionarioRepository.getOne(id);
	}
	
	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}

	public void excluir(Long id) {
		funcionarioRepository.deleteById(id);
	}

}
