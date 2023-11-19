package com.talent.repository;

import com.talent.model.Cargo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório JPA para a entidade Cargo.
 */
@Repository
@Transactional
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

    /**
     * Busca um cargo pelo nome.
     *
     * @param nome Nome do cargo a ser buscado.
     * @return Um Optional contendo o cargo encontrado ou vazio se não encontrado.
     */
    Optional<Cargo> findByNome(String nome);

    /**
     * Busca um cargo pelo ID.
     *
     * @param id ID do cargo a ser buscado.
     * @return Um Optional contendo o cargo encontrado ou vazio se não encontrado.
     */
    Optional<Cargo> findById(UUID id);
}
