
package com.example.jonathanmaldonado.bookslist.Frutas;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fruta {

    @SerializedName("frutas")
    @Expose
    private List<Fruta_> frutas = null;

    public List<Fruta_> getFrutas() {
        return frutas;
    }

    public void setFrutas(List<Fruta_> frutas) {
        this.frutas = frutas;
    }

}
