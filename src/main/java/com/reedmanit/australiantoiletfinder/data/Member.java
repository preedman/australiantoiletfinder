package com.reedmanit.australiantoiletfinder.data;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", length = 200)
    private String firstname;

    @Column(name = "lastname", length = 200)
    private String lastname;

    @Column(name = "userid", length = 200)
    private String userid;

    @OneToMany(mappedBy = "memberIdFk")
    private Set<Feature> features = new LinkedHashSet<>();
    @Column(name = "parking")
    private Boolean parking;
    @Column(name = "\"parking-accessible\"")
    private Boolean parkingAccessible;
    @Column(name = "mlak24")
    private Boolean mlak24;
    @Column(name = "mlakafterhours")
    private Boolean mlakafterhours;
    @Column(name = "\"payment-required\"")
    private Boolean paymentRequired;
    @Column(name = "\"adult-change\"")
    private Boolean adultChange;
    @Column(name = "\"changing-places\"")
    private Boolean changingPlaces;
    @Column(name = "acshower")
    private Boolean acshower;
    @Column(name = "\"baby-change\"")
    private Boolean babyChange;
    @Column(name = "\"dump-point\"")
    private Boolean dumpPoint;
    @Column(name = "male")
    private Boolean male;
    @Column(name = "female")
    private Boolean female;
    @Column(name = "unisex")
    private Boolean unisex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getParkingAccessible() {
        return parkingAccessible;
    }

    public void setParkingAccessible(Boolean parkingAccessible) {
        this.parkingAccessible = parkingAccessible;
    }

    public Boolean getMlak24() {
        return mlak24;
    }

    public void setMlak24(Boolean mlak24) {
        this.mlak24 = mlak24;
    }

    public Boolean getMlakafterhours() {
        return mlakafterhours;
    }

    public void setMlakafterhours(Boolean mlakafterhours) {
        this.mlakafterhours = mlakafterhours;
    }

    public Boolean getPaymentRequired() {
        return paymentRequired;
    }

    public void setPaymentRequired(Boolean paymentRequired) {
        this.paymentRequired = paymentRequired;
    }

    public Boolean getAdultChange() {
        return adultChange;
    }

    public void setAdultChange(Boolean adultChange) {
        this.adultChange = adultChange;
    }

    public Boolean getChangingPlaces() {
        return changingPlaces;
    }

    public void setChangingPlaces(Boolean changingPlaces) {
        this.changingPlaces = changingPlaces;
    }

    public Boolean getAcshower() {
        return acshower;
    }

    public void setAcshower(Boolean acshower) {
        this.acshower = acshower;
    }

    public Boolean getBabyChange() {
        return babyChange;
    }

    public void setBabyChange(Boolean babyChange) {
        this.babyChange = babyChange;
    }

    public Boolean getDumpPoint() {
        return dumpPoint;
    }

    public void setDumpPoint(Boolean dumpPoint) {
        this.dumpPoint = dumpPoint;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Boolean getFemale() {
        return female;
    }

    public void setFemale(Boolean female) {
        this.female = female;
    }

    public Boolean getUnisex() {
        return unisex;
    }

    public void setUnisex(Boolean unisex) {
        this.unisex = unisex;
    }

}