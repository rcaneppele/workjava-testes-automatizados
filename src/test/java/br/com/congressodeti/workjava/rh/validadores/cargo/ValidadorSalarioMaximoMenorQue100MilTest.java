package br.com.congressodeti.workjava.rh.validadores.cargo;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;

public class ValidadorSalarioMaximoMenorQue100MilTest {

	private ValidadorSalarioMaximoMenorQue100Mil validador;
	
	@Before
	public void before() {
		validador = new ValidadorSalarioMaximoMenorQue100Mil();
	}

	@Test(expected = BusinessException.class)
	public void salarioMaximoNaoPodeSerMaiorQue100Mil() {
		Cargo dba = criarCargo(new BigDecimal("1000"), new BigDecimal("110000"));
		
		validador.validar(dba);
	}

	@Test
	public void salarioMaximoPodeSerIgualA100Mil() {
		Cargo dba = criarCargo(new BigDecimal("1000"), new BigDecimal("100000"));
		
		validador.validar(dba);
	}
	
	@Test
	public void salarioMaximoPodeSerMenorQue100Mil() {
		Cargo dba = criarCargo(new BigDecimal("1000"), new BigDecimal("99000"));
		
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
