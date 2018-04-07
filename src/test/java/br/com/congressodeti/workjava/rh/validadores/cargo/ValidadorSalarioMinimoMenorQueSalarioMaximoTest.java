package br.com.congressodeti.workjava.rh.validadores.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

public class ValidadorSalarioMinimoMenorQueSalarioMaximoTest {

	private ValidadorSalarioMinimoMenorQueSalarioMaximo validador;
	
	@Before
	public void before() {
		validador = new ValidadorSalarioMinimoMenorQueSalarioMaximo();
	}

	@Test(expected = BusinessException.class)
	public void salarioMinimoNaoPodeSerMaiorQueSalarioMaximo() {
		Cargo dba = criarCargo(new BigDecimal("2000"), new BigDecimal("1000"));
		
		validador.validar(dba);
	}

	@Test
	public void salarioMinimoPodeSerIgualAoSalarioMaximo() {
		Cargo dba = criarCargo(new BigDecimal("1000"), new BigDecimal("1000"));
		
		validador.validar(dba);
	}
	
	@Test
	public void salarioMinimoPodeSerMenorQueSalarioMaximo() {
		Cargo dba = criarCargo(new BigDecimal("999"), new BigDecimal("1000"));
		
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
