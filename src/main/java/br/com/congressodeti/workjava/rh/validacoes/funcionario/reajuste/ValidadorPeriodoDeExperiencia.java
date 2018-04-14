package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.time.LocalDate;
import java.time.Period;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Reajuste;

public class ValidadorPeriodoDeExperiencia implements ValidadorReajuste {
	
	@Override
	public void valida(Reajuste reajuste) {
		LocalDate dataAdmissao = reajuste.getFuncionario().getDataDeAdmissao();
		LocalDate dataReajuste = reajuste.getData();
		
		Period intervalo = dataAdmissao.until(dataReajuste);
		if (intervalo.getMonths() < 3) {
			throw new BusinessException("Funcionario em periodo de experiencia nao pode receber reajuste");
		}
	}

}
