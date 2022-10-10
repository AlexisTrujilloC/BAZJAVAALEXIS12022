package com.wizeline.entregabletres.otd;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BromaOTD {
    @JsonProperty("categoria")
    private String category;
    @JsonProperty("tipo")
    private String type;
    @JsonProperty("broma")
    private String joke;
    @JsonProperty("inicioBroma")
    private String setup;
    @JsonProperty("finBroma")
    private String delivery;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lenguaje")
    private String lang;

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getSetup() {return setup;}
    public void setSetup(String setup) {this.setup = setup;}
    public String getDelivery() {return delivery;}
    public void setDelivery(String delivery) {this.delivery = delivery;}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getLang() {return lang;}
    public void setLang(String lang) {this.lang = lang;}
    public String getJoke() {return joke;}
    public void setJoke(String joke) {this.joke = joke;}
}
