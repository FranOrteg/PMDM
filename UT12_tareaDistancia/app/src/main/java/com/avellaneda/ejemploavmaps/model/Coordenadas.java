package com.avellaneda.ejemploavmaps.model;

import java.io.Serializable;

public class Coordenadas implements Serializable {

    private double lat;
    private double lon;

    public Coordenadas() {
    }

    public Coordenadas(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


}
