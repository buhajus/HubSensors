package org.hub.sensors.model;

import javax.persistence.*;

@Entity
@Table(name = "pool_data")
public class PoolData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "chloride")
    private double chloride;
    @Column(name = "ph")
    private double ph;
    @Column(name = "temperature")
    private double temp;
    @Column(name = "date_time")
    private String dateTime;

    @Column(name = "device_name")
    private String deviceName;

    public PoolData() {
    }

    public PoolData(int id, double chloride, double ph, double temp, String dateTime, String deviceName) {
        this.id = id;
        this.chloride = chloride;
        this.ph = ph;
        this.temp = temp;
        this.dateTime = dateTime;
        this.deviceName = deviceName;
    }

    public PoolData(double chloride, double ph, double temp, String dateTime, String deviceName) {
        this.chloride = chloride;
        this.ph = ph;
        this.temp = temp;
        this.dateTime = dateTime;
        this.deviceName = deviceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getChloride() {
        return chloride;
    }

    public void setChloride(double chloride) {
        this.chloride = chloride;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
