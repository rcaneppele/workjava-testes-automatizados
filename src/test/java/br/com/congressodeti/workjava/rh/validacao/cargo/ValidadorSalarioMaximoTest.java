package br.com.congressodeti.workjava.rh.validacao.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.exception.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

public class ValidadorSalarioMaximoTest {

	private ValidadorSalarioMaximo validador;
	
	@Before
	public void setup() {
		validador = new ValidadorSalarioMaximo();
	}

	@Test(expected = BusinessException.class)
	public void cargoNaoPodeTerSalarioMaximoSuperiorACemMil() {
		Cargo cargo = criaCargo(new BigDecimal("200000.00"));
		validador.valida(cargo);
	}

	@Test
	public void cargoPodeTerSalarioMaximoIgualACemMil() {
		Cargo cargo = criaCargo(new BigDecimal("100000.00"));
		validador.valida(cargo);
	}
	
	@Test
	public void cargoPodeTerSalarioMaximoMenorDoQueCemMil() {
		Cargo cargo = criaCargo(new BigDecimal("80000.00"));
		validador.valida(cargo);
	}
	
	private Cargo criaCargo(BigDecimal valor) {
		Cargo cargo = new Cargo();
		cargo.setSalarioMaximo(valor);
		return cargo;
	}

}
