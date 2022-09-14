package com.porfolio.cas.Dto;

import javax.validation.constraints.NotBlank;


public class dtoSkills {
   @NotBlank
   private String nombreSkill;
   @NotBlank
   private int porcentaje;
   
   //Constructor

    public dtoSkills() {
    }

    public dtoSkills(String nombreSkill, int porcentaje) {
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
    }
   
    //Getter y Setter

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
}
