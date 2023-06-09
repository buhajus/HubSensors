package org.hub.sensors.controller;

import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorData;
import org.hub.sensors.repository.GpioRepository;
import org.hub.sensors.service.GpioPinService;
import org.hub.sensors.service.SensorDataService;
import org.hub.sensors.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Controller
@EnableAutoConfiguration


public class PiGpioController {
    final GpioController gpio = GpioFactory.getInstance();

    final Console console = new Console();


    @Autowired
    @Qualifier("SensorDataServiceImpl")
    public SensorDataService sensorDataService;

    private Sensor sensor;



    @Autowired
    private GpioPinService gpioPinService;

    @Autowired
    private SensorService sensorService;


    public GpioPinDigitalInput gpioPin() {
        Sensor listOfActiveSensors[] = sensorService.getAll().toArray(new Sensor[0]);
        int pinAddress = Integer.parseInt(null);
        for (Sensor gpioPin : listOfActiveSensors) {
            pinAddress = gpioPin.getGpio().getGpio();

        }


        return gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(pinAddress), PinPullResistance.PULL_DOWN);

    }


    @Scheduled(fixedDelay = 5000)
    public void checkSensorStatus() throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);
        SensorData sensorData = new SensorData();
        int gpioPinNumber27 = 27;
        int gpioPinNumber17 = 17;


        // Set pin numbering mode to BCM
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));


//


        // Create digital input pin

//        GpioPinDigitalInput pin[] = {
//                PinState pinValue27 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber27)).getState(),
//                gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber17))
//        };

//
        // GpioPinDigitalInput pin27 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber27), PinPullResistance.PULL_DOWN);
        // GpioPinDigitalInput pin17 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber17), PinPullResistance.PULL_DOWN);

        //PinState pinValue27 = pin27.getState();
        //PinState pinValue17 = pin17.getState();

        //  int activePin = 0;

        int activePin = 0;
        int gPin = gpioPin().getPin().getAddress();

//        for (Sensor listOfActiveSensors : sensorService.getAll()) {
//            System.out.println("is hihj " + listOfActiveSensors.getGpio().getGpio());
//
//
//        }
//        if (pin27.isHigh()) {
//
//            activePin = gpioPinNumber27;
//        } else if (pin17.isHigh()) {
//            activePin = gpioPinNumber17;
        for (Sensor listOfActiveSensors : sensorService.getAll()) {
            if (gpioPin().isHigh()) {
                System.out.println(listOfActiveSensors.getSensorName());
            }


        }

//        }

        Map<Integer, String> sensorMap = new HashMap<>();
        //TODO:aprasyti nauja metoda kuris ieskotu pagal gpio o ne pagal id
        sensorMap.put(gPin, sensorService.getByGpio(gPin).getSensorName());

        while (gpioPin().isHigh()) {

            sensor.setSensorName(sensorMap.get(gPin));
            sensorDataService.insertSensorDataStatus("2022-12-15", "tvarktas", sensor.getSensorName(), 0);
            Thread.sleep(5000);
        }


//        switch (activePin) {
//            case 27:
//                sensorData.setSensorName("Sensor 27");
//                sensorData.setSensorLocation("Pool");
//                //if pin state is low, do this:
//                //check which pin was high and get data sensorData.getSensorLocation(), sensorData.getSensorName() ....
//                //switch,
//                //send email after trigger
//
//                sensorDataService.insertSensorDataStatus(dateTime, sensorData.getSensorLocation(), sensor.getSensorName(), 0);
//                console.println("GPIO " + gpioPinNumber27 + "is :" + pinValue27);
//
//                //   pin27.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
//
//                break;
//
//            case 17:
//                sensorData.setSensorName("Sensor 17");
//                sensorData.setSensorLocation("Chloride");
//                //if pin state is low, do this:
//                //check which pin was high and get data sensorData.getSensorLocation(), sensorData.getSensorName() ....
//                //switch,
//                //send email after trigger
//                sensorDataService.insertSensorDataStatus(dateTime, sensorData.getSensorLocation(), sensorData.getSensorName(), 0);
//                console.println("GPIO " + gpioPinNumber17 + "is :" + pinValue17);
//                //pin17.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
//                break;
//
//            default:
//                console.println("GPIO " + gpioPinNumber17 + " is :" + pinValue17);
//
//                console.println("GPIO " + gpioPinNumber27 + " is :" + pinValue27);
//                break;
//
//        }

        gpio.shutdown();

        for (int i = 0; i <= sensorService.getAll().size(); i++) {
            gpio.unprovisionPin(gpioPin());
        }

        //TODO:for loop to close all pins

        // gpio.unprovisionPin(pin27);
        // gpio.unprovisionPin(pin17);

    }
}
