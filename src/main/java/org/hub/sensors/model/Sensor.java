package org.hub.sensors.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sensor_name")
    //  @NotBlank(message = "Name is mandatory")
    private String sensorName;
    @Column(name = "sensor_model")
    @NotBlank(message = "Model is mandatory")
    private String sensorModel;

    @Column(name = "gpio")
    @Min(value = 0, message = "min 0" )
    private int gpio;

    private String status;

    public Sensor() {
    }

    public Sensor(String sensorName, String sensorModel, int gpio) {
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
        this.gpio = gpio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sensor(int id, String sensorName, String sensorModel, int gpio, String status) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
        this.gpio = gpio;
        this.status = status;
    }

    public int getGpio() {
        return gpio;
    }

    public Sensor(int id, String sensorName, String sensorModel, int gpio) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
        this.gpio = gpio;
    }

    public void setGpio(int gpio) {
        this.gpio = gpio;
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
