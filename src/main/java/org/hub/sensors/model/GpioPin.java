package org.hub.sensors.model;




import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;

@Entity
@Table(name = "gpio_config")
public class GpioPin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "gpio")
    private Long gpio;
    @OneToOne(mappedBy = "gpio")
    private Sensor sensor;


    public GpioPin() {
    }

    public GpioPin(Long id, Long gpio, Sensor sensor) {
        this.id = id;
        this.gpio = gpio;
        this.sensor = sensor;
    }

    public GpioPin(Long gpio, Sensor sensor) {
        this.gpio = gpio;
        this.sensor = sensor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGpio() {
        return gpio;
    }

    public void setGpio(Long gpio) {
        this.gpio = gpio;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
