package br.com.congressodeti.workjava.rh.validacoes.funcionario.reajuste;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.congressodeti.workjava.rh.exceptions.BusinessException;
import br.com.congressodeti.workjava.rh.models.Funcionario;
import br.com.congressodeti.workjava.rh.models.Reajuste;
import br.com.congressodeti.workjava.rh.repositories.ReajusteRepository;

public class ValidadorPeriodicidadeReajustesTest {
	
	private ValidadorPeriodicidadeReajustes validador;
	private ReajusteRepository repository;
	
	@Before
	public void setup() {
		this.repository = Mockito.mock(ReajusteRepository.class);
		this.validador = new ValidadorPeriodicidadeReajustes(repository);
	}

	@Test(expected = BusinessException.class)
	public void funcionarioNaoPodeReceberReajusteSeTiverRecebidoOutroAMenosDe6Meses() {
		Funcionario comReajusteRecente = criarFuncionario();
		Reajuste bonus = criarReajuste(comReajusteRecente, LocalDate.now());

		//representa um reajuste que o funcionario recebeu no mes passado
		Reajuste doMesPassado = criarReajuste(comReajusteRecente, LocalDate.now().minusMonths(1));
		
		//fingindo que foi no BD e retornou o ultimo reajuste do funcionario
		Mockito.when(repository.findTopByFuncionarioOrderByIdDesc(comReajusteRecente)).thenReturn(doMesPassado);
		
		validador.valida(bonus);
	}
	
	@Test
	public void funcionarioPodeReceberReajusteSeTiverRecebidoOutroAExatos6Meses() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(fulano, LocalDate.now());

		//representa um reajuste que o funcionario recebeu 6 meses atras
		Reajuste de6MesesAtras = criarReajuste(fulano, LocalDate.now().minusMonths(6));
		
		//fingindo que foi no BD e retornou o ultimo reajuste do funcionario
		Mockito.when(repository.findTopByFuncionarioOrderByIdDesc(fulano)).thenReturn(de6MesesAtras);
		
		validador.valida(bonus);
	}
	
	@Test
	public void funcionarioPodeReceberReajusteSeTiverRecebidoOutroAMaisDe6Meses() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(fulano, LocalDate.now());

		//representa um reajuste que o funcionario recebeu 1 ano atras
		Reajuste de1AnoAtras = criarReajuste(fulano, LocalDate.now().minusYears(1));
		
		//fingindo que foi no BD e retornou o ultimo reajuste do funcionario
		Mockito.when(repository.findTopByFuncionarioOrderByIdDesc(fulano)).thenReturn(de1AnoAtras);
		
		validador.valida(bonus);
	}
	
	@Test
	public void funcionarioQueNuncaRecebeuReajustesPodeReceberPrimeiroReajuste() {
		Funcionario fulano = criarFuncionario();
		Reajuste bonus = criarReajuste(fulano, LocalDate.now());
		
		//fingindo que foi no BD e retornou null, simulando que o funcionario nunca recebeu reajustes
		Mockito.when(repository.findTopByFuncionarioOrderByIdDesc(fulano)).thenReturn(null);
		
		validador.valida(bonus);
	}
	
	private Funcionario criarFuncionario() {
		Funcionario joao = new Funcionario();
		return joao;
	}
	
	private Reajuste criarReajuste(Funcionario funcionario, LocalDate data) {
		Reajuste bonus = new Reajuste();
		bonus.setFuncionario(funcionario);
		bonus.setData(data);
		
		return bonus;
	}

}
