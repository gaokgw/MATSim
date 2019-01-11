package org.matsim.area.outputCreation.tripDurationAnalyzer;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;

import java.util.ArrayList;

/**
 * Created by carlloga on 17.03.2017.
 */
public class Trip {
    private double departureTime;
    private double arrivalTime;
    private double vehicleStartTime;
    private Id id;
    private String mode;
    private boolean atHome;
    private boolean atWorkPlace;
    private boolean atOther;
    private char purpose;
    private ArrayList<Id<Link>> listOfLinks;


    public Trip(Id id) {
        this.id = id;
        atWorkPlace = false;
        atHome = true;
        atOther = false;
        listOfLinks = new ArrayList<>();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public boolean isAtWorkPlace() {
        return atWorkPlace;
    }

    public void setAtWorkPlace(boolean atWorkPlace) {
        this.atWorkPlace = atWorkPlace;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getDuration() {
        return (arrivalTime - departureTime);
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setVehicleStartTime(double vehicleStartTime) {
        this.vehicleStartTime = vehicleStartTime;
    }

    public double getWaitingTimeBefore(){
        return (vehicleStartTime - departureTime);

    }

    public char getPurpose() {
        return purpose;
    }

    public void setPurpose(char purpose) {
        this.purpose = purpose;
    }

    public boolean isAtHome() {
        return atHome;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    public boolean isAtOther() {
        return atOther;
    }

    public void setAtOther(boolean atOther) {
        this.atOther = atOther;
    }

    public ArrayList<Id<Link>> getListOfLinks() {
        return listOfLinks;
    }

    public void addLinkToList(Id id) {
        this.listOfLinks.add(id);
    }
}
