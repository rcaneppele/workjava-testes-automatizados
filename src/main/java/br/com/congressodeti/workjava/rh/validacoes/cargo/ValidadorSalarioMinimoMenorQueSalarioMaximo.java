package br.com.congressodeti.workjava.rh.validacoes.cargo;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;

@Service
public class ValidadorSalarioMinimoMenorQueSalarioMaximo implements ValidadorCargo {
	
	@Override
	public void valida(Cargo cargo) {
		if (cargo.getSalarioMinimo().compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("Salario minimo deve ser menor do que salario maximo");
		}
	}

}
