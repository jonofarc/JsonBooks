
package com.example.jonathanmaldonado.bookslist.Frutas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fruta_ {

    @SerializedName("nombre_fruta")
    @Expose
    private String nombreFruta;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;

    public String getNombreFruta() {
        return nombreFruta;
    }

    public void setNombreFruta(String nombreFruta) {
        this.nombreFruta = nombreFruta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
