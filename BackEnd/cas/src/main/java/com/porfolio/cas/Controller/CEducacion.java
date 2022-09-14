
package com.porfolio.cas.Controller;

import com.porfolio.cas.Dto.dtoEducacion;
import com.porfolio.cas.Entity.Educacion;
import com.porfolio.cas.Security.Controller.Mensaje;
import com.porfolio.cas.Service.SEducacion;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://frontendporfoliocas.web.app")
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>>list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        
        Educacion educacion = sEducacion.getone(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreEd())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sEducacion.existsByNombreEd(dtoeducacion.getNombreEd())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(dtoeducacion.getNombreEd(),dtoeducacion.getDescripcionEd());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Nueva Educacion creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sEducacion.existsByNombreEd(dtoeducacion.getNombreEd()) && sEducacion.getByNombreEd(dtoeducacion.getNombreEd()).get().getId() !=id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getNombreEd())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.getone(id).get();
        
        educacion.setNombreEd(dtoeducacion.getNombreEd());
        educacion.setDescripcionEd(dtoeducacion.getDescripcionEd());
        
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
     
}
