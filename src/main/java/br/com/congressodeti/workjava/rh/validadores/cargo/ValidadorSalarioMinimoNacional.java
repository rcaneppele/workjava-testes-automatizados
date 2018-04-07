package br.com.congressodeti.workjava.rh.validadores.cargo;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

@Service
public class ValidadorSalarioMinimoNacional implements ValidadorCadastroCargo {
	
	private static final BigDecimal SALARIO_MINIMO_2018 = new BigDecimal("954.00");

	@Override
	public void validar(Cargo cargo) {
		if (cargo.getSalarioMinimo().compareTo(SALARIO_MINIMO_2018) < 0) {
			throw new BusinessException("Salário mínimo do cargo nao pode ser menor que salário mínimo nacional(R$954.00)");
		}
	}

}
