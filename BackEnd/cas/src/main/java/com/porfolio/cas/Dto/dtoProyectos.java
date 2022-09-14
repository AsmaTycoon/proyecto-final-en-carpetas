package com.porfolio.cas.Dto;

import javax.validation.constraints.NotBlank;


public class dtoProyectos {
    @NotBlank
    private String nombreProyecto;
    @NotBlank
    private String descripcioP;
    private String imagenP;
    
    //Constructor

    public dtoProyectos() {
    }

    public dtoProyectos(String nombreProyecto, String descripcioP, String imagenP) {
        this.nombreProyecto = nombreProyecto;
        this.descripcioP = descripcioP;
        this.imagenP = imagenP;
    }
    
    //Getter y Setter

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcioP() {
        return descripcioP;
    }

    public void setDescripcioP(String descripcioP) {
        this.descripcioP = descripcioP;
    }

    public String getImagenP() {
        return imagenP;
    }

    public void setImagenP(String imagenP) {
        this.imagenP = imagenP;
    }
    
}
