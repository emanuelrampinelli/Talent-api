package com.talent.repository;

import com.talent.model.Questionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface QuestionarioRepository extends JpaRepository<Questionario, UUID> {
}
