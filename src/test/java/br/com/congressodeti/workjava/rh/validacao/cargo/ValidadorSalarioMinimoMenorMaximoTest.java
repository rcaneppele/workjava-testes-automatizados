package br.com.congressodeti.workjava.rh.validacao.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.exception.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

public class ValidadorSalarioMinimoMenorMaximoTest {

	private ValidadorSalarioMinimoMenorQueSalarioMaximo validador;
	
	@Before
	public void setup() {
		validador = new ValidadorSalarioMinimoMenorQueSalarioMaximo();
	}

	@Test(expected = BusinessException.class)
	public void salarioMinimoNaoPodeSerMaiorDoQueSalarioMaximo() {
		Cargo cargo = criaCargo(new BigDecimal("2000"), new BigDecimal("1000"));
		validador.valida(cargo);
	}

	@Test
	public void salarioMinimoPodeSerIgualAoSalarioMaximo() {
		Cargo cargo = criaCargo(new BigDecimal("2000"), new BigDecimal("2000"));
		validador.valida(cargo);
	}
	
	@Test
	public void salarioMinimoPodeSerMenorDoQueSalarioMaximo() {
		Cargo cargo = criaCargo(new BigDecimal("2000"), new BigDecimal("3000"));
		validador.valida(cargo);
	}
	
	private Cargo criaCargo(BigDecimal minimo, BigDecimal maximo) {
		Cargo cargo = new Cargo();
		cargo.setSalarioMaximo(maximo);
		cargo.setSalarioMinimo(minimo);
		return cargo;
	}
}
