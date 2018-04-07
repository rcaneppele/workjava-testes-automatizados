package br.com.congressodeti.workjava.rh.validadores.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

public class ValidadorSalarioMinimoNacionalTest {

	private ValidadorSalarioMinimoNacional validador;
	
	@Before
	public void before() {
		validador = new ValidadorSalarioMinimoNacional();
	}

	@Test(expected = BusinessException.class)
	public void salarioMinimoDoCargoNaoPodeSerMenorQueSalarioMinimoNacional() {
		Cargo dba = criarCargo(new BigDecimal("900"), new BigDecimal("1900"));
		
		validador.validar(dba);
	}

	@Test
	public void salarioMinimoDoCargoPodeSerIgualAoSalarioMinimoNacional() {
		Cargo dba = criarCargo(new BigDecimal("954"), new BigDecimal("1900"));
		
		validador.validar(dba);
	}
	
	@Test
	public void salarioMinimoDoCargoPodeSerMaiorQueSalarioMinimoNacional() {
		Cargo dba = criarCargo(new BigDecimal("1000"), new BigDecimal("1900"));
		
		validador.validar(dba);
	}
	
	private Cargo criarCargo(BigDecimal salarioMinimo, BigDecimal salarioMaximo) {
		Cargo dba = new Cargo();
		dba.setNome("DBA");
		dba.setSalarioMinimo(salarioMinimo);
		dba.setSalarioMaximo(salarioMaximo);
		return dba;
	}

}
