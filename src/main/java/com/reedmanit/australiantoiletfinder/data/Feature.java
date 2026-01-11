package com.reedmanit.australiantoiletfinder.data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "parking")
    private Boolean parking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"toilet-id\"")
    private Toilet toiletId;

    @Column(name = "\"parking-accessible\"")
    private Boolean parkingAccessible;

    @Column(name = "\"key-required\"")
    private Boolean keyRequired;

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

    @Column(name = "byosling")
    private Boolean byosling;

    @Column(name = "acshower")
    private Boolean acshower;

    @Column(name = "acmlak")
    private Boolean acmlak;

    @Column(name = "\"baby-change\"")
    private Boolean babyChange;

    @Column(name = "\"baby-care-room\"")
    private Boolean babyCareRoom;

    @Column(name = "\"dump-point\"")
    private Boolean dumpPoint;

    @Column(name = "dpwashout")
    private Boolean dpwashout;

    @Column(name = "dpafterhours")
    private Boolean dpafterhours;

    @Column(name = "male")
    private Boolean male;

    @Column(name = "female")
    private Boolean female;

    @Column(name = "allgender")
    private Boolean allgender;

    @Column(name = "ambulant")
    private Boolean ambulant;

    @Column(name = "accessible")
    private Boolean accessible;

    @Column(name = "lhtransfer")
    private Boolean lhtransfer;

    @Column(name = "rhtransfer")
    private Boolean rhtransfer;

    @Column(name = "\"sharp-disposal\"")
    private Boolean sharpDisposal;

    @Column(name = "\"drinking-water\"")
    private Boolean drinkingWater;

    @Column(name = "\"sanitary-disposal\"")
    private Boolean sanitaryDisposal;

    @Column(name = "\"mens-pad-disposal\"")
    private Boolean mensPadDisposal;

    @Column(name = "shower")
    private Boolean shower;

    @Column(name = "\"start-date\"")
    private LocalDate startDate;

    @Column(name = "\"end-date\"")
    private LocalDate endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Toilet getToiletId() {
        return toiletId;
    }

    public void setToiletId(Toilet toiletId) {
        this.toiletId = toiletId;
    }

    public Boolean getParkingAccessible() {
        return parkingAccessible;
    }

    public void setParkingAccessible(Boolean parkingAccessible) {
        this.parkingAccessible = parkingAccessible;
    }

    public Boolean getKeyRequired() {
        return keyRequired;
    }

    public void setKeyRequired(Boolean keyRequired) {
        this.keyRequired = keyRequired;
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

    public Boolean getByosling() {
        return byosling;
    }

    public void setByosling(Boolean byosling) {
        this.byosling = byosling;
    }

    public Boolean getAcshower() {
        return acshower;
    }

    public void setAcshower(Boolean acshower) {
        this.acshower = acshower;
    }

    public Boolean getAcmlak() {
        return acmlak;
    }

    public void setAcmlak(Boolean acmlak) {
        this.acmlak = acmlak;
    }

    public Boolean getBabyChange() {
        return babyChange;
    }

    public void setBabyChange(Boolean babyChange) {
        this.babyChange = babyChange;
    }

    public Boolean getBabyCareRoom() {
        return babyCareRoom;
    }

    public void setBabyCareRoom(Boolean babyCareRoom) {
        this.babyCareRoom = babyCareRoom;
    }

    public Boolean getDumpPoint() {
        return dumpPoint;
    }

    public void setDumpPoint(Boolean dumpPoint) {
        this.dumpPoint = dumpPoint;
    }

    public Boolean getDpwashout() {
        return dpwashout;
    }

    public void setDpwashout(Boolean dpwashout) {
        this.dpwashout = dpwashout;
    }

    public Boolean getDpafterhours() {
        return dpafterhours;
    }

    public void setDpafterhours(Boolean dpafterhours) {
        this.dpafterhours = dpafterhours;
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

    public Boolean getAllgender() {
        return allgender;
    }

    public void setAllgender(Boolean allgender) {
        this.allgender = allgender;
    }

    public Boolean getAmbulant() {
        return ambulant;
    }

    public void setAmbulant(Boolean ambulant) {
        this.ambulant = ambulant;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public Boolean getLhtransfer() {
        return lhtransfer;
    }

    public void setLhtransfer(Boolean lhtransfer) {
        this.lhtransfer = lhtransfer;
    }

    public Boolean getRhtransfer() {
        return rhtransfer;
    }

    public void setRhtransfer(Boolean rhtransfer) {
        this.rhtransfer = rhtransfer;
    }

    public Boolean getSharpDisposal() {
        return sharpDisposal;
    }

    public void setSharpDisposal(Boolean sharpDisposal) {
        this.sharpDisposal = sharpDisposal;
    }

    public Boolean getDrinkingWater() {
        return drinkingWater;
    }

    public void setDrinkingWater(Boolean drinkingWater) {
        this.drinkingWater = drinkingWater;
    }

    public Boolean getSanitaryDisposal() {
        return sanitaryDisposal;
    }

    public void setSanitaryDisposal(Boolean sanitaryDisposal) {
        this.sanitaryDisposal = sanitaryDisposal;
    }

    public Boolean getMensPadDisposal() {
        return mensPadDisposal;
    }

    public void setMensPadDisposal(Boolean mensPadDisposal) {
        this.mensPadDisposal = mensPadDisposal;
    }

    public Boolean getShower() {
        return shower;
    }

    public void setShower(Boolean shower) {
        this.shower = shower;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}