package br.com.congressodeti.workjava.rh.validadores.funcionario.reajuste;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Reajuste;

@Service
public class ValidadorPeriodoDeExperiencia implements ValidadorReajusteSalarial {

	@Override
	public void validar(Reajuste reajuste) {
		LocalDate dataAdmissao = reajuste.getFuncionario().getDataDeAdmissao();
		LocalDate dataReajuste = reajuste.getData();
		
		Period intervalo = dataAdmissao.until(dataReajuste);
		if (intervalo.getMonths() < 3) {
			throw new BusinessException("Funcionario em periodo de experiencia nao pode receber reajuste salarial");
		}
	}

}
