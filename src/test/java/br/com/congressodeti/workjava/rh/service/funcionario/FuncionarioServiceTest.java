package br.com.congressodeti.workjava.rh.service.funcionario;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.congressodeti.workjava.rh.exception.BusinessException;
import br.com.congressodeti.workjava.rh.model.Cargo;
import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.repositories.FuncionarioRepository;

public class FuncionarioServiceTest {

	private FuncionarioService service;
	private FuncionarioRepository repository;
	
	@Before
	public void setup() {
		this.repository = Mockito.mock(FuncionarioRepository.class);
		this.service = new FuncionarioService(this.repository);
	}
	
	@Test(expected = BusinessException.class)
	public void salarioDoFuncionarioNaoPodeSerMenorQueSalarioMinimoDeSeuCargo() {
		Funcionario joao = criaFuncionario(new BigDecimal("1500"));
		service.salvar(joao);
	}
	
	@Test(expected = BusinessException.class)
	public void salarioDoFuncionarioNaoPodeSerMaiorQueSalarioMaximoDeSeuCargo() {
		Funcionario joao = criaFuncionario(new BigDecimal("6000"));
		service.salvar(joao);
	}
	
	@Test
	public void salarioDoFuncionarioPodeSerIgualAoSalarioMinimoDeSeuCargo() {
		Funcionario joao = criaFuncionario(new BigDecimal("2000"));
		service.salvar(joao);
		Mockito.verify(repository).save(joao);
	}
	
	@Test
	public void salarioDoFuncionarioPodeSerIgualAoSalarioMaximoDeSeuCargo() {
		Funcionario joao = criaFuncionario(new BigDecimal("5000"));
		service.salvar(joao);
		Mockito.verify(repository).save(joao);
	}
	
	@Test
	public void salarioDoFuncionarioPodeTerValorQueEstejaEntreAFaixaSalarialDeSeuCargo() {
		Funcionario joao = criaFuncionario(new BigDecimal("3000"));
		service.salvar(joao);
		Mockito.verify(repository).save(joao);
	}
	
	private Funcionario criaFuncionario(BigDecimal salario) {
		Cargo dba = new Cargo();
		dba.setSalarioMinimo(new BigDecimal("2000"));
		dba.setSalarioMaximo(new BigDecimal("5000"));
		
		Funcionario joao = new Funcionario();
		joao.setCargo(dba);
		joao.setSalario(salario);
		
		return joao;
	}

}
