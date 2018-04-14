package br.com.congressodeti.workjava.rh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.congressodeti.workjava.rh.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
