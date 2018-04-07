package br.com.congressodeti.workjava.rh.validadores.cargo;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

@Service
public class ValidadorSalarioMinimoMenorQueSalarioMaximo implements ValidadorCadastroCargo {
	
	@Override
	public void validar(Cargo cargo) {
		if (cargo.getSalarioMinimo().compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("Salário mínimo deve ser menor que salário máximo");
		}
	}

}
