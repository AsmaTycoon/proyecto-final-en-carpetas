
package com.porfolio.cas.Controller;

import com.porfolio.cas.Dto.dtoBanner;
import com.porfolio.cas.Entity.Banner;
import com.porfolio.cas.Security.Controller.Mensaje;
import com.porfolio.cas.Service.SBanner;
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
@RequestMapping("/banner")
@CrossOrigin(origins = "https://frontendporfoliocas.web.app")
public class CBanner {
    @Autowired
    SBanner sBanner;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Banner>> list(){
        List<Banner> list = sBanner.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoBanner dtobanner){
        if (StringUtils.isBlank(dtobanner.getImagenB())){
            return new ResponseEntity(new Mensaje("La URL es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (sBanner.existsByImagenB(dtobanner.getImagenB())){
            return new ResponseEntity(new Mensaje("Esa URL ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Banner banner = new Banner(dtobanner.getImagenB());
        sBanner.save(banner);
        return new ResponseEntity(new Mensaje("Banner agregado"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Banner> getById(@PathVariable("id") int id){
        if(!sBanner.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Banner banner = sBanner.getOne(id).get();
        return new ResponseEntity(banner, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoBanner dtobanner){
        if(!sBanner.existsById(id))
            return new ResponseEntity(new Mensaje("El Id no existe"), HttpStatus.BAD_REQUEST);
        
        if(sBanner.existsByImagenB(dtobanner.getImagenB()) && sBanner.getByImagenB(dtobanner.getImagenB()).get().getId() !=id)
            return new ResponseEntity(new Mensaje("Esa URL ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtobanner.getImagenB()))
            return new ResponseEntity(new Mensaje("La URL es obligatoria"), HttpStatus.BAD_REQUEST);
        
        Banner banner = sBanner.getOne(id).get();
        banner.setImagenB(dtobanner.getImagenB());
        
        sBanner.save(banner);
        return new ResponseEntity(new Mensaje("URL banner actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sBanner.existsById(id))
            return new ResponseEntity(new Mensaje("El Id no existe"), HttpStatus.BAD_REQUEST);
        
        sBanner.delete(id);
        
        return new ResponseEntity(new Mensaje("Banner eliminado"), HttpStatus.OK);
    }
}
