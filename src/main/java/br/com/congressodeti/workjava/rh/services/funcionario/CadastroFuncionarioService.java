package br.com.congressodeti.workjava.rh.services.funcionario;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;
import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;

@Service
public class CadastroFuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;
	
	public CadastroFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void salvar(Funcionario novo) {
		Cargo cargo = novo.getCargo();
		if (novo.getSalario().compareTo(cargo.getSalarioMinimo()) < 0 || novo.getSalario().compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("Salário do funcionario nao esta dentro dos limites da faixa salarial do cargo");
		}
		
		funcionarioRepository.save(novo);
	}

}
