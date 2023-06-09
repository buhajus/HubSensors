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

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "gpio_id", referencedColumnName = "id")
    private GpioPin gpio;

    public Sensor() {
    }

    public Sensor(String sensorName, String sensorModel, GpioPin gpio) {
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
        this.gpio = gpio;
    }

    public Sensor(int id, String sensorName, String sensorModel, GpioPin gpio) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorModel = sensorModel;
        this.gpio = gpio;
    }

    public Sensor(String sensorName, String sensorModel) {

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

    public GpioPin getGpio() {
        return gpio;
    }

    public void setGpio(GpioPin gpio) {
        this.gpio = gpio;
    }
}
