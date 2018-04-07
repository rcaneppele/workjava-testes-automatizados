package br.com.congressodeti.workjava.rh.validadores.cargo;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

@Service
public class ValidadorSalarioMaximoMenorQue100Mil implements ValidadorCadastroCargo {
	
	private static final BigDecimal SALARIO_MAXIMO_PERMITIDO = new BigDecimal("100000.00");

	@Override
	public void validar(Cargo cargo) {
		if (cargo.getSalarioMaximo().compareTo(SALARIO_MAXIMO_PERMITIDO) > 0) {
			throw new BusinessException("Salário máximo não pode ser maior que R$100.000,00");
		}
	}

}
