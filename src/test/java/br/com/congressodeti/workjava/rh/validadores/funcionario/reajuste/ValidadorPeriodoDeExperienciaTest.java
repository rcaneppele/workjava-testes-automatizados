package br.com.congressodeti.workjava.rh.validadores.funcionario.reajuste;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.model.Reajuste;

public class ValidadorPeriodoDeExperienciaTest {
	
	private ValidadorPeriodoDeExperiencia validador;
	
	@Before
	public void before() {
		validador = new ValidadorPeriodoDeExperiencia();
	}

	@Test(expected = BusinessException.class)
	public void funcionarioEmPeriodoDeExperienciaNaoPodeReceberReajusteSalarial() {
		Funcionario novo = criarFuncionario(LocalDate.now().minusMonths(1));
		Reajuste bonus = bonus(novo);
		
		validador.validar(bonus);
	}

	@Test
	public void funcionarioRecemSaidoDoPeriodoDeExperienciaPodeReceberReajuste() {
		Funcionario novo = criarFuncionario(LocalDate.now().minusMonths(3));
		Reajuste bonus = bonus(novo);
		
		validador.validar(bonus);
	}

	@Test
	public void funcionarioContratadoHa6MesesPodeReceberReajuste() {
		Funcionario novo = criarFuncionario(LocalDate.now().minusMonths(6));
		
		Reajuste bonus = bonus(novo);
		
		validador.validar(bonus);
	}
	
	private Reajuste bonus(Funcionario novo) {
		Reajuste bonus = new Reajuste();
		bonus.setFuncionario(novo);
		bonus.setValor(new BigDecimal("100"));
		return bonus;
	}
	
	private Funcionario criarFuncionario(LocalDate admissao) {
		Funcionario novo = new Funcionario();
		novo.setDataDeAdmissao(admissao);
		return novo;
	}

}
