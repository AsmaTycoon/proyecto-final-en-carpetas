package com.porfolio.cas.Controller;

import com.porfolio.cas.Dto.dtoProyectos;
import com.porfolio.cas.Entity.Proyectos;
import com.porfolio.cas.Security.Controller.Mensaje;
import com.porfolio.cas.Service.SProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://frontendporfoliocas.web.app")
public class CProyectos {
    @Autowired
    SProyectos sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtoproyectos){
        if (StringUtils.isBlank(dtoproyectos.getNombreProyecto())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existsByNombreProyecto(dtoproyectos.getNombreProyecto())){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = new Proyectos(dtoproyectos.getNombreProyecto(),dtoproyectos.getDescripcioP(),dtoproyectos.getImagenP());
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK); 
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos,HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoProyectos dtoproyectos){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        
        if(sProyectos.existsByNombreProyecto(dtoproyectos.getNombreProyecto()) && sProyectos.getByNombreProyecto(dtoproyectos.getNombreProyecto()).get().getId() !=id)
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"),HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoproyectos.getNombreProyecto()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombreProyecto(dtoproyectos.getNombreProyecto());
        proyectos.setDescripcionP(dtoproyectos.getDescripcioP());
        proyectos.setImagenP(dtoproyectos.getImagenP());
        
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Skills actualizada"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        
        sProyectos.delete(id);
        
        return new ResponseEntity(new Mensaje("Skills eliminada"), HttpStatus.OK);
    }
}
