package org.hub.sensors.model;




import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.lang.Long;

@Entity
@Table(name = "gpio_config")
public class GpioPin  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "gpio")

    @Min(message = "Min value 2", value = 2)
    @Max(message = "Max value 27", value = 27)
    private int gpio;
    @OneToOne(mappedBy = "gpio")
    private Sensor sensor;


    public GpioPin() {
    }

    public GpioPin(int id, int gpio, Sensor sensor) {
        this.id = id;
        this.gpio = gpio;
        this.sensor = sensor;
    }

    public GpioPin(int gpio, Sensor sensor) {
        this.gpio = gpio;
        this.sensor = sensor;
    }

    public GpioPin(int gpio) {
        this.gpio = gpio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGpio() {
        return gpio;
    }


    public void setGpio(int gpio) {
        this.gpio = gpio;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
