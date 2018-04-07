package br.com.congressodeti.workjava.rh.services.funcionario;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.congressodeti.workjava.rh.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;
import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;

public class CadastroFuncionarioServiceTest {
	
	private CadastroFuncionarioService service;
	private FuncionarioRepository repository;
	
	@Before
	public void before() {
		repository = Mockito.mock(FuncionarioRepository.class);
		service = new CadastroFuncionarioService(repository);
	}

	@Test(expected = BusinessException.class)
	public void naoDeveriaCadastrarFuncionarioComSalarioMenorQueSalarioMinimoDoCargo() {
		Funcionario fulano = criarFuncionario(new BigDecimal("1000"));
		service.salvar(fulano);
	}

	@Test(expected = BusinessException.class)
	public void naoDeveriaCadastrarFuncionarioComSalarioMaiorQueSalarioMaximoDoCargo() {
		Funcionario fulano = criarFuncionario(new BigDecimal("6000"));
		service.salvar(fulano);
	}
	
	@Test
	public void deveriaCadastrarFuncionarioComSalarioCompativelComOCargo() {
		Funcionario fulano = criarFuncionario(new BigDecimal("4000"));
		
		service.salvar(fulano);
		Mockito.verify(repository).save(fulano);
	}
	
	@Test
	public void funcionarioPodeTerSalarioIgualAoSalarioMinimoDoCargo() {
		Funcionario fulano = criarFuncionario(new BigDecimal("2000"));
		
		service.salvar(fulano);
		Mockito.verify(repository).save(fulano);
	}
	
	@Test
	public void funcionarioPodeTerSalarioIgualAoSalarioMaximoDoCargo() {
		Funcionario fulano = criarFuncionario(new BigDecimal("5000"));
		
		service.salvar(fulano);
		Mockito.verify(repository).save(fulano);
	}
	
	private Funcionario criarFuncionario(BigDecimal salario) {
		Funcionario fulano = new Funcionario();
		fulano.setNome("fulano da silva");
		fulano.setSalario(salario);
		
		Cargo dba = new Cargo();
		dba.setSalarioMinimo(new BigDecimal("2000"));
		dba.setSalarioMaximo(new BigDecimal("5000"));
		
		fulano.setCargo(dba);
		return fulano;
	}

}
