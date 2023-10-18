package com.example.project2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    private String image; //I can figure out how to pull images in but making it possible to add a new image is ?????
                          //It's a string for now, this can be edited later into a different type rather easily.

    protected Employee() {}

    public Employee(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;

    }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, name='%s', description='%s', image='%s']",
                id, name, description, image);
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
}
