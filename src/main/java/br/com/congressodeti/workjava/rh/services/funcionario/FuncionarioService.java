package br.com.congressodeti.workjava.rh.services.funcionario;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void salvar(Funcionario novo) {
		BigDecimal salario = novo.getSalario();
		Cargo cargo = novo.getCargo();
		
		if (salario.compareTo(cargo.getSalarioMinimo()) < 0) {
			throw new BusinessException("salario do funcionario nao pode ser menor do que salario minimo de seu cargo");
		}
		
		if (salario.compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("salario do funcionario nao pode ser maior do que salario maximo de seu cargo");
		}
		
		funcionarioRepository.save(novo);
	}
	

}
