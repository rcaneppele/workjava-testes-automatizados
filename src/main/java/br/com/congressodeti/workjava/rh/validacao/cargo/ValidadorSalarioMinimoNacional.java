package br.com.congressodeti.workjava.rh.validacao.cargo;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exception.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

@Service
public class ValidadorSalarioMinimoNacional implements ValidadorCargo {
	
	private static final BigDecimal SALARIO_MINIMO_NACIONAL = new BigDecimal("954.00");

	@Override
	public void valida(Cargo cargo) {
		if (cargo.getSalarioMinimo().compareTo(SALARIO_MINIMO_NACIONAL) < 0) {
			throw new BusinessException("Salario minimo do cargo nao pode ser menor do que o salario minimo nacional(R$954,00)");
		}
	}

}
