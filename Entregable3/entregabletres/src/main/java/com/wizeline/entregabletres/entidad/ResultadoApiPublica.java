package com.wizeline.entregabletres.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoApiPublica {
    private String amount;
    private ArrayList<Broma> jokes;

    public String getAmount() {return amount;}
    public void setAmount(String amount) {this.amount = amount;}
    public ArrayList<Broma> getJokes() {return jokes;}
    public void setJokes(ArrayList<Broma> jokes) {this.jokes = jokes;}

    @Override
    public String toString() {
        return "amount='" + amount + '\'' +
                ", jokes=" + jokes
                ;
    }
}
