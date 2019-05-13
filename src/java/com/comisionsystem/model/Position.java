/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.model;

import java.util.ArrayList;

/**
 *
 * @author alexis
 */
public class Position {
    private int id;
    private String name;
    private ArrayList<function> functions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<function> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<function> functions) {
        this.functions = functions;
    }

    
    
}
