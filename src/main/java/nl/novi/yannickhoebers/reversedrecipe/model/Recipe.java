package nl.novi.yannickhoebers.reversedrecipe.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "recipes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recipe implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String recipeName;
    @Column
    private int portion;
    @Column
    private String meat;
    @Column
    private String vegetable;
    @Column
    private String other;
    @Column
    private String instructions;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cook_id", nullable = false)
    @JsonIgnore
    private Cook cook;

    public Recipe() {}

    public Recipe(String recipeName, int portion, String meat, String vegetable, String other, String instructions) {
        this.recipeName = recipeName;
        this.portion = portion;
        this.meat = meat;
        this.vegetable = vegetable;
        this.other = other;
        this.instructions = instructions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }


    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Cook getCook() {
        return this.cook;
    }
}
