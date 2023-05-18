package org.hub.sensors.model;

import javax.persistence.*;

@Entity
@Table(name = "sensors_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sensor_name")
    private String sensorName;
    @Column(name = "sensor_location")
    private String sensorLocation;
    @Column(name = "status")
    private int status;
    @Column(name = "date")
    private String date;

    public SensorData() {
    }

    public SensorData(String sensorName, String sensorLocation, int status, String date) {
        this.sensorName = sensorName;
        this.sensorLocation = sensorLocation;
        this.status = status;
        this.date = date;
    }

    public SensorData(String sensorName, int status, String date) {
        this.sensorName = sensorName;
        this.status = status;
        this.date = date;
    }

    public SensorData(int id, String sensorName, String sensorLocation, int status, String date) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorLocation = sensorLocation;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    // BŪTINAS, kad Model vaikščiotų tarp front-end ir back-end

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
