package com.example.BSUIRProject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "response")
public class Numbers {

    @Basic
    @Column(name = "decimal_number", nullable = false)
    private int decimalNumber;

    @Basic
    @Column(name = "binary_number", nullable = false)
    private int binaryNumber;
    
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Numbers(int decimalNumber, int binaryNumber) {
        this.decimalNumber = decimalNumber;
        this.binaryNumber = binaryNumber;
    }

    public Numbers(int decimalNumber, int binaryNumber, Integer id) {
        this.decimalNumber = decimalNumber;
        this.binaryNumber = binaryNumber;
        this.id = id;
    }

    public int getDecimalNumber() {
        return decimalNumber;
    }

    public int getBinaryNumber() {
        return binaryNumber;
    }

    public int getId() {
        return id;
    }

    public Numbers() {

    }

    public void setDecimalNumber(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public void setBinaryNumber(int binaryNumber) {
        this.binaryNumber = binaryNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
