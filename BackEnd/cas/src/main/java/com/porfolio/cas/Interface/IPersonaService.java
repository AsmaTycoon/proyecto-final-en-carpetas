
package com.porfolio.cas.Interface;

import com.porfolio.cas.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer una lista de Personas
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo Persona
    public void savePersona (Persona persona);
    
    //Eliminar un objeto por ID
    public void deletePersona (Long id);
    
    //Buscar Persona por ID
    public Persona findPersona(Long id);
}
