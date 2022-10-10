package com.wizeline.entregabletres.otd;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizeline.entregabletres.entidad.Broma;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultadoApiPublicaOTD {
    @JsonProperty("cantidad")
    private String amount;
    @JsonProperty("bromas")
    private ArrayList<BromaOTD> jokes;

    public String getAmount() {return amount;}
    public void setAmount(String amount) {this.amount = amount;}
    public ArrayList<BromaOTD> getJokes() {return jokes;}
    public void setJokes(ArrayList<BromaOTD> jokes) {this.jokes = jokes;}
}
