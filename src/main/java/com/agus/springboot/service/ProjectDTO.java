package com.agus.springboot.service;

import jakarta.validation.constraints.NotNull;

public class ProjectDTO {
    @NotNull
    private int id;
    @NotNull
    private String name;

    public ProjectDTO(){

    }

    public ProjectDTO(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
