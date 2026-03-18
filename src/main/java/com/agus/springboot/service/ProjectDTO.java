package com.agus.springboot.service;

import jakarta.validation.constraints.NotNull;

public class ProjectDTO {

    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    public ProjectDTO(){

    }

    public ProjectDTO(String name, String description){
        this.name = name;
        this.description = description;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
