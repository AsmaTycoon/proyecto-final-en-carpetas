package com.porfolio.cas.Repository;

import com.porfolio.cas.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RSkills extends JpaRepository<Skills, Integer> {
    public Optional<Skills> findByNombreSkill(String nombreSkill);
    public boolean existsByNombreSkill(String nombreSkill);
}
