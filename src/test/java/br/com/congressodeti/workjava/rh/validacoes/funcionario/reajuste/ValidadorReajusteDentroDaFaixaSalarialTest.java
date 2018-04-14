package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Cargo;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.models.Reajuste;

public class ValidadorReajusteDentroDaFaixaSalarialTest {

	private ValidadorReajusteDentroDaFaixaSalarial validador;
	
	@Before
	public void setup() {
		this.validador = new ValidadorReajusteDentroDaFaixaSalarial();
	}
	
	@Test(expected = BusinessException.class)
	public void salarioReajustadoDoFuncionarioNaoPodeUltrapassarFaixaSalarialDeSeuCargo() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(new BigDecimal("2000"), fulano);
		
		validador.valida(bonus);
	}
	
	@Test
	public void salarioReajustadoDoFuncionarioPodeSerIgualAoSalarioMaximoDeSeuCargo() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(new BigDecimal("1000"), fulano);
		
		validador.valida(bonus);
	}
	
	@Test
	public void salarioReajustadoDoFuncionarioPodeSerMenorDoQueSalarioMaximoDeSeuCargo() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(new BigDecimal("300"), fulano);
		
		validador.valida(bonus);
	}

	private Funcionario criarFuncionario() {
		Cargo programador = new Cargo();
		programador.setSalarioMinimo(new BigDecimal("2000"));
		programador.setSalarioMaximo(new BigDecimal("4000"));
		
		Funcionario funcionario = new Funcionario();
		funcionario.setSalario(new BigDecimal("3000"));
		funcionario.setCargo(programador);
		
		return funcionario;
	}
	
	private Reajuste criarReajuste(BigDecimal valor, Funcionario funcionario) {
		Reajuste bonus = new Reajuste();
		bonus.setValor(valor);
		bonus.setFuncionario(funcionario);
		return bonus;
	}

}
