package com.dbtest.demotest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "workers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Worker implements ModelBase, Serializable {

    @Id
    @Column(name="workerId")
    @SequenceGenerator(name = "workersIdGen", sequenceName = "Workers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workersIdGen")
    private Integer id;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "salary")
    private double salary;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @ManyToOne(targetEntity = CasinoBuilding.class)
    @JoinColumn(name="fk_casino_id",  referencedColumnName="casino_id")
    private CasinoBuilding casinoBuildingWorker;



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public CasinoBuilding getCasinoBuildingWorker() {
        return casinoBuildingWorker;
    }

    public void setCasinoBuildingWorker(CasinoBuilding casinoBuildingWorker) {
        this.casinoBuildingWorker = casinoBuildingWorker;
    }
}
