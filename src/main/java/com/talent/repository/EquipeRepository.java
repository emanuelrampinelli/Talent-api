package com.talent.repository;

import com.talent.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipeRepository  extends JpaRepository<Equipe, UUID> {
}
