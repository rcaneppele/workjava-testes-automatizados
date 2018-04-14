package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.time.Period;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Reajuste;
import br.com.congressodeti.workjava.rh.repositories.ReajusteRepository;

@Service
public class ValidadorPeriodicidadeReajustes implements ValidadorReajuste {
	
	private final ReajusteRepository repository;
	
	public ValidadorPeriodicidadeReajustes(ReajusteRepository repository) {
		this.repository = repository;
	}

	@Override
	public void valida(Reajuste reajuste) {
		Reajuste ultimoRecebido = repository.findTopByFuncionarioOrderByIdDesc(reajuste.getFuncionario());
		
		//se o funcionario nunca teve reajustes 
		if (ultimoRecebido == null) {
			return;
		}
		
		Period intervalo = ultimoRecebido.getData().until(reajuste.getData());
		if (intervalo.getYears() < 1 && intervalo.getMonths() < 6) {
			throw new BusinessException("Funcionario nao pode receber reajuste pois recebeu outro a menos de 6 meses");
		}
	}

}
