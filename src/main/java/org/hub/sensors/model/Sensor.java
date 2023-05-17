package org.hub.sensors.model;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sensor_name")
    private String sensorName;
    @Column(name = "sensor_model")
    private String sensorModel;

    public Sensor() {
    }

    public Sensor(String sensorName, String sensorModel) {
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
    }

    public Sensor(int id, String sensorName, String sensorModel) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(String sensorModel) {
        this.sensorModel = sensorModel;
    }
}
