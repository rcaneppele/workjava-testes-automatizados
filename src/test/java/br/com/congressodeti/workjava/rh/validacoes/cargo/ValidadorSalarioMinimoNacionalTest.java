package br.com.congressodeti.workjava.rh.validacoes.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.validacoes.cargo.ValidadorSalarioMinimoNacional;

public class ValidadorSalarioMinimoNacionalTest {

	private ValidadorSalarioMinimoNacional validador;
	
	@Before
	public void setup() {
		validador = new ValidadorSalarioMinimoNacional();
	}

	@Test(expected = BusinessException.class)
	public void salarioMinimoNaoPodeSerMenorDoQueSalarioMinimoNacional() {
		Cargo cargo = criaCargo(new BigDecimal("900"));
		validador.valida(cargo);
	}

	@Test
	public void salarioMinimoPodeSerIgualAoSalarioMinimoNacional() {
		Cargo cargo = criaCargo(new BigDecimal("954.00"));
		validador.valida(cargo);
	}
	
	@Test
	public void salarioMinimoPodeSerMaiorDoQueSalarioMinimoNacional() {
		Cargo cargo = criaCargo(new BigDecimal("1000"));
		validador.valida(cargo);
	}
	
	private Cargo criaCargo(BigDecimal valor) {
		Cargo cargo = new Cargo();
		cargo.setSalarioMinimo(valor);
		return cargo;
	}

}
