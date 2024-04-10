package com.example.mepro.layout_saving.model;

public class   FluctuatingBalancesHistory {
    private int id;
    private String name;
    private String time;
    private double fluctuatingBalances;
    private int idCategory;
    
    public FluctuatingBalancesHistory(int idCategory) {
        this.idCategory = idCategory;
    }
    
    public FluctuatingBalancesHistory(int id,String name, String time, double fluctuatingBalances, int idCategory) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.fluctuatingBalances = fluctuatingBalances;
        this.idCategory = idCategory;
    }

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
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getFluctuatingBalances() {
        return fluctuatingBalances;
    }

    public void setFluctuatingBalances(double fluctuatingBalances) {
        this.fluctuatingBalances = fluctuatingBalances;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
