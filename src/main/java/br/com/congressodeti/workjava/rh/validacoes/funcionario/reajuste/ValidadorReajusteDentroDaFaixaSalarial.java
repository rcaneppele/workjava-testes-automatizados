package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.models.Reajuste;

@Service
public class ValidadorReajusteDentroDaFaixaSalarial implements ValidadorReajuste {
	
	@Override
	public void valida(Reajuste reajuste) {
		BigDecimal salarioAtual = reajuste.getFuncionario().getSalario();
		BigDecimal salarioReajustado = salarioAtual.add(reajuste.getValor());
		Cargo cargo = reajuste.getFuncionario().getCargo();
		
		if (salarioReajustado.compareTo(cargo.getSalarioMaximo()) > 0) {
			throw new BusinessException("Salario reajustado do funcionario nao pode ultrapassar o valor do salario maximo de seu cargo");
		}
	}

}
