package com.avellaneda.ejemploretrofit;


import java.util.ArrayList;

import java.util.List;



/**

 * Created by dell on 26/03/2018.

 */



public class Curso {



    public String title;

    public String subtitle;
    public String homepage;
    public ArrayList<Instructor> instructors;



    public String getTitle() {

        return title;

    }



    public void setTitle(String title) {

        this.title = title;

    }



    public String getSubtitle() {

        return subtitle;

    }



    public void setSubtitle(String subtitle) {

        this.subtitle = subtitle;

    }



    public List<Instructor> getInstructors() {

        return instructors;

    }



    public void setInstructors(ArrayList<Instructor> instructors) {

        this.instructors = instructors;

    }

}