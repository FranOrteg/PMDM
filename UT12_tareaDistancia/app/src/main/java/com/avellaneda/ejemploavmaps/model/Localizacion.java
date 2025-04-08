package com.avellaneda.ejemploavmaps.model;


import java.io.Serializable;

public class Localizacion implements Serializable {
    /**
     * {
     *     "id": 707860,
     *     "name": "Hurzuf",
     *     "country": "UA",
     *     "coord": {
     *       "lon": 34.283333,
     *       "lat": 44.549999
     *     }
     *   },
     */

    private String country;
    private String name;
    private Coordenadas coord;

    public Localizacion(String country, String name, Coordenadas coord) {
        this.country = country;
        this.name = name;
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }


    public Coordenadas getCoord() {
        return coord;
    }


    public String getName() {
        return name;
    }
}
