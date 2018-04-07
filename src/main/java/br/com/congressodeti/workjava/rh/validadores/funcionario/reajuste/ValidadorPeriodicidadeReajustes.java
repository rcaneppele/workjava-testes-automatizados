package br.com.congressodeti.workjava.rh.validadores.funcionario.reajuste;

import java.time.Period;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Reajuste;
import br.com.congressodeti.workjava.rh.repositories.ReajusteRepository;

@Service
public class ValidadorPeriodicidadeReajustes implements ValidadorReajusteSalarial {

	private final ReajusteRepository repository;
	
	public ValidadorPeriodicidadeReajustes(ReajusteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validar(Reajuste reajuste) {
		Reajuste ultimoReajuste = repository.findTopByFuncionarioOrderByIdDesc(reajuste.getFuncionario());
		
		
		Period intervalo = ultimoReajuste.getData().until(reajuste.getData());
		if (intervalo.getMonths() < 6) {
			throw new BusinessException("Funcionario nao pode receber reajuste pois recebeu outro a menos de 6 meses");
		}
	}

}
