package com.porfolio.cas.Repository;

import com.porfolio.cas.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProyectos extends JpaRepository<Proyectos, Integer> {

    public Optional<Proyectos> findByNombreProyecto(String nombreProyecto);

    public boolean existsByNombreProyecto(String nombreProyecto);
}
