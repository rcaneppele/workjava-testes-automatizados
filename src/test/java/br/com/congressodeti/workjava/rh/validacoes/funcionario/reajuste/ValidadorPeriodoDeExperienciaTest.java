package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.models.Reajuste;

public class ValidadorPeriodoDeExperienciaTest {

	private ValidadorPeriodoDeExperiencia validador;
	
	@Before
	public void setup() {
		this.validador = new ValidadorPeriodoDeExperiencia();
	}
	
	@Test(expected = BusinessException.class)
	public void funcionarioEmPeriodoDeExperienciaNaoPodeReceberReajuste() {
		LocalDate mesPassado = LocalDate.now().minusMonths(1);
		Funcionario recemContratado = criarFuncionario(mesPassado);
		
		Reajuste bonus = criarReajuste(recemContratado);
		validador.valida(bonus);
	}
	
	@Test
	public void funcionarioRecemSaidoDoPeriodoDeExperienciaPodeReceberReajuste() {
		LocalDate tresMesesAtras = LocalDate.now().minusMonths(3);
		Funcionario recemContratado = criarFuncionario(tresMesesAtras);
		
		Reajuste bonus = criarReajuste(recemContratado);
		validador.valida(bonus);
	}
	
	@Test
	public void funcionarioForaDoPeriodoDeExperienciaPodeReceberReajuste() {
		LocalDate seisMesesAtras = LocalDate.now().minusMonths(6);
		Funcionario recemContratado = criarFuncionario(seisMesesAtras);
		
		Reajuste bonus = criarReajuste(recemContratado);
		validador.valida(bonus);
	}
	
	private Funcionario criarFuncionario(LocalDate admissao) {
		Funcionario fulano = new Funcionario();
		fulano.setDataDeAdmissao(admissao);
		
		return fulano;
	}
	
	private Reajuste criarReajuste(Funcionario funcionario) {
		Reajuste bonus = new Reajuste();
		bonus.setData(LocalDate.now());
		bonus.setFuncionario(funcionario);
		
		return bonus;
	}

}
