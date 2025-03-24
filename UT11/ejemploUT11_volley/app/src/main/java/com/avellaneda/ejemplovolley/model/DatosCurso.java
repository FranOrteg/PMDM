package com.avellaneda.ejemplovolley.model;


import java.util.ArrayList;


public class DatosCurso {

    public String clave;
    public String titulo;
    public String subtitulo;
    public ArrayList<String> instructores;
    public String paginaWeb;

 // https://raw.githubusercontent.com/nataliainformatica/ejercicios_clase_PR/refs/heads/main/Recursos/ficheroCursos.json
    public DatosCurso(String clave, String titulo, String subtitulo, ArrayList<String> instructores, String paginaWeb) {
        this.clave = clave;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.instructores = instructores;
        this.paginaWeb = paginaWeb;
    }



    public DatosCurso(String clave, String titulo, String subtitulo, String paginaWeb) {

        this.clave = clave;

        this.titulo = titulo;

        this.subtitulo = subtitulo;

        this.paginaWeb = paginaWeb;

    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTitulo() {
        return titulo;
    }



    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }



    public String getSubtitulo() {

        return subtitulo;

    }



    public void setSubtitulo(String subtitulo) {

        this.subtitulo = subtitulo;

    }



    public ArrayList<String> getInstructores() {

        return instructores;

    }



    public void setInstructores(ArrayList<String> instructores) {

        this.instructores = instructores;

    }



    public String getPaginaWeb() {

        return paginaWeb;

    }



    public void setPaginaWeb(String paginaWeb) {

        this.paginaWeb = paginaWeb;

    }

}