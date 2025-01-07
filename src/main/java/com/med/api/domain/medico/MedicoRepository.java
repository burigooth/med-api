package com.med.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
           SELECT m FROM Medico m
           WHERE m.ativo = true
           AND m.especialidade = :especialidade
           AND m.id NOT IN(
                SELECT c.medico.id FROM Consulta c
                WHERE DATE(c.data) = DATE(:data)
           )
           ORDER BY rand()
           LIMIT 1
            """)
    Medico findRandomByEspecialidade(Especialidade especialidade, LocalDateTime data);
}
