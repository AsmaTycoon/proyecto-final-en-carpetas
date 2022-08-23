
package com.porfolio.cas.Service;

import com.porfolio.cas.Entity.Educacion;
import com.porfolio.cas.Repository.REducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducacion {
    @Autowired
    REducacion rEducacion;
    
    public List<Educacion> list(){
        return rEducacion.findAll();
    }
    
    public Optional<Educacion> getone(int id){
        return rEducacion.findById(id);
    }
    
    public Optional<Educacion> getByNombreEd(String nombreEd){
        return rEducacion.findByNombreEd(nombreEd);
    }
    
    public void save(Educacion educacion){
        rEducacion.save(educacion);
    }
    
    public void delete(int id){
        rEducacion.deleteById(id);
    }
    
    public boolean existsById (int id){
        return rEducacion.existsById(id);
    }
    
    public boolean existsByNombreEd (String nombreEd){
        return rEducacion.existsByNombreEd(nombreEd);
    }
}
