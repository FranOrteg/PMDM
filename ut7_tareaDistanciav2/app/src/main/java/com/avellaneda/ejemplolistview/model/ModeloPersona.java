package com.avellaneda.ejemplolistview.model;

import java.io.Serializable;

public class ModeloPersona implements Serializable {

    private String nombre;
    private String descripcion;
    private int codigo;
    private int foto;
    // Constructor
    public ModeloPersona(int codigo, String nombre, String descripcion, int foto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

 public ModeloPersona(){

 }
    public String getNombre() {

        return nombre;

    }

    public void setNombre(String nombre){

        this.nombre=nombre;

    }

    public String getDescripcion() {

        return descripcion;

    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;

    }


    public int getCodigo() {

        return codigo;

    }

    public void setCodigo(int codigo) {

        this.codigo = codigo;

    }

    public int getFoto() {

        return foto;

    }



    public void setFoto(int foto) {

        this.foto = foto;

    }

}