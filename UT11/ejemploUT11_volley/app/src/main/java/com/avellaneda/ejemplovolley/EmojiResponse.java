package com.avellaneda.ejemplovolley;


import com.google.gson.annotations.SerializedName;

public class EmojiResponse {


    /**
     *  USO DE @SerializedName
     *  Si el JSON usa nombres distintos a los de tu clase.
     *  Si quieres mantener un estándar en los nombres de variables en Java (camelCase) mientras usas JSON con otros formatos.
     *  Si trabajas con APIs externas donde no puedes cambiar los nombres de los campos JSON.
     */
    @SerializedName("character")
    private String character;

    @SerializedName("unicode")
    private String unicode;

    @SerializedName("name")
    private String name;

    // Getters
    public String getCharacter() {
        return character;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getName() {
        return name;
    }
}