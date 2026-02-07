package com.reedmanit.australiantoiletfinder.data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "toilet")
public class Toilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "facilityid")
    private Integer facilityid;

    @Column(name = "url", length = 200)
    private String url;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "address1", length = 300)
    private String address1;

    @Column(name = "town", length = 200)
    private String town;

    @Column(name = "state", length = 200)
    private String state;

    @Column(name = "\"address-note\"", length = 600)
    private String addressNote;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Column(name = "\"parking-note\"", length = 600)
    private String parkingNote;

    @Column(name = "\"access-note\"", length = 600)
    private String accessNote;

    @Column(name = "\"adult-change-note\"", length = 600)
    private String adultChangeNote;

    @Column(name = "\"baby-change-note\"", length = 600)
    private String babyChangeNote;

    @Column(name = "\"dump-point-note\"", length = 600)
    private String dumpPointNote;

    @Column(name = "\"opening-hours\"", length = 600)
    private String openingHours;

    @Column(name = "\"opening-hours-note\"", length = 600)
    private String openingHoursNote;

    @Column(name = "\"toilet-note\"", length = 600)
    private String toiletNote;

    @Column(name = "\"start-date\"")
    private LocalDate startDate;

    @Column(name = "\"end-date\"")
    private LocalDate endDate;

    @OneToMany(mappedBy = "toiletId")
    private Set<Feature> features = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "favouriteToilets")
    private Set<Member> favouritedByMembers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(Integer facilityid) {
        this.facilityid = facilityid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressNote() {
        return addressNote;
    }

    public void setAddressNote(String addressNote) {
        this.addressNote = addressNote;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getParkingNote() {
        return parkingNote;
    }

    public void setParkingNote(String parkingNote) {
        this.parkingNote = parkingNote;
    }

    public String getAccessNote() {
        return accessNote;
    }

    public void setAccessNote(String accessNote) {
        this.accessNote = accessNote;
    }

    public String getAdultChangeNote() {
        return adultChangeNote;
    }

    public void setAdultChangeNote(String adultChangeNote) {
        this.adultChangeNote = adultChangeNote;
    }

    public String getBabyChangeNote() {
        return babyChangeNote;
    }

    public void setBabyChangeNote(String babyChangeNote) {
        this.babyChangeNote = babyChangeNote;
    }

    public String getDumpPointNote() {
        return dumpPointNote;
    }

    public void setDumpPointNote(String dumpPointNote) {
        this.dumpPointNote = dumpPointNote;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getOpeningHoursNote() {
        return openingHoursNote;
    }

    public void setOpeningHoursNote(String openingHoursNote) {
        this.openingHoursNote = openingHoursNote;
    }

    public String getToiletNote() {
        return toiletNote;
    }

    public void setToiletNote(String toiletNote) {
        this.toiletNote = toiletNote;
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

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<Member> getFavouritedByMembers() {
        return favouritedByMembers;
    }

    public void setFavouritedByMembers(Set<Member> favouritedByMembers) {
        this.favouritedByMembers = favouritedByMembers;
    }
}