package br.com.congressodeti.workjava.rh.validacoes.cargo;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;

@Service
public class ValidadorSalarioMaximo implements ValidadorCargo {
	
	private static final BigDecimal SALARIO_MAXIMO_EMPRESA = new BigDecimal("100000.00");

	@Override
	public void valida(Cargo cargo) {
		if (cargo.getSalarioMaximo().compareTo(SALARIO_MAXIMO_EMPRESA) > 0) {
			throw new BusinessException("Salario maximo do cargo nao pode ser maior do que R$100.000,00)");
		}
	}

}
