package br.com.congressodeti.workjava.rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.congressodeti.workjava.rh.model.Funcionario;
import br.com.congressodeti.workjava.rh.model.Reajuste;

@Repository
public interface ReajusteRepository extends JpaRepository<Reajuste, Long> {

	List<Reajuste> findAllByFuncionario(Funcionario funcionario);

	Reajuste findTopByFuncionarioOrderByIdDesc(Funcionario funcionario);

}
