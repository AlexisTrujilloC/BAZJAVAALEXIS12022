package com.wizeline.entregabletres.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Broma {
    private String category;
    private String type;
    private String joke;
    private String setup;
    private String delivery;
    private String id;
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
