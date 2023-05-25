package org.hub.sensors.controller;

//import com.pi4j.io.gpio.*;
//import com.pi4j.util.Console;

import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorData;
import org.hub.sensors.model.User;
import org.hub.sensors.repository.SensorDataRepository;
import org.hub.sensors.service.SecurityService;
import org.hub.sensors.service.SensorDataService;
import org.hub.sensors.service.SensorService;
import org.hub.sensors.service.UserService;
import org.hub.sensors.validator.SensorValidator;
import org.hub.sensors.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.stream.IntStream;

// @RestController negrąžina view.
// Kadangi mums reikia grąžinti view pagal Spring MVC, naudojame @Controller
@Controller
//  @EnableAutoConfiguration - žymi konfigūracijos komponentą. Viduje leidžia kurti bean per metodus su @Bean
//  Ši klasės lygio anotacija nurodo Spring karkasui “atspėti” konfigūraciją,
//  remiantis priklausomybėmis (jar bibliotekos), kurias programuotojas įtraukė į projektą.
//  Šiuo atveju ji veikia kartu su main metodu.
@EnableAutoConfiguration
public class HubSensorsController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SensorValidator sensorValidator;

    @Autowired
    private SensorDataRepository sensorDataRepository;


    final GpioController gpio = GpioFactory.getInstance();
    final GpioController gpio2 = GpioFactory.getInstance();
    final Console console = new Console();

//    final GpioController gpio = GpioFactory.getInstance();
//    final Console console = new Console();


    // autowire- naudojamas automatinei priklausomybių injekcijai
    // Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("SensorDataServiceImpl")
    public SensorDataService sensorDataService;

    @Autowired
    @Qualifier("SensorServiceImpl")
    public SensorService sensorService;

    @Autowired
    @Qualifier("UserServiceImpl")
    public UserService getUserService;

    /**
     * Method action to take values from JSP input form and store into DB
     *
     * @param sensor
     * @param bindingResult
     * @param inputForm
     * @param outputForm
     * @return redirect to list of all sensors
     */
    @PostMapping("/add-new-sensor")
    public String addSensor(@Valid @ModelAttribute("sensor") Sensor sensor,
                            BindingResult bindingResult,
                            @RequestParam HashMap<String, String> inputForm,
                            ModelMap outputForm) {

        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add_new_sensor";
        }
        String sensorName = inputForm.get("sensorName");
        String sensorModel = inputForm.get("sensorModel");
        int gpio = Integer.parseInt(inputForm.get("gpio"));

        outputForm.put("sensorName", sensorName);
        outputForm.put("sensorModel", sensorModel);
        outputForm.put("gpio", gpio);

        sensorService.save(new Sensor(sensorName, sensorModel, gpio));
        return "redirect:/sensors";
    }

    /**
     * Method for add new sensor
     *
     * @param model
     * @return JSP template
     */
    @GetMapping("/add_new_sensor")
    public String addNewSensor(Model model) {

        //Jeigu Model "number" nepraeina validacijos - per jį grąžinamos validacijos
        //klaidos į View
        model.addAttribute("sensor", new Sensor());
        //grąžiname JSP failą, kuris turi būti talpinamas "webapp -> WEB-INF ->  JSP" folderi
        return "add_new_sensor";
    }

    /**
     * Method for checking sensors every 5 sec and if they are active - store data into DB
     */

    //  @GetMapping("/status")
    //@Scheduled(fixedDelay = 3600000 ) //every one hour
    @Scheduled(fixedDelay = 5000)
    public void checkSensorStatus() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);
        SensorData sensorData = new SensorData();
        int gpioPinNumber27 = 27;
        int gpioPinNumber17 = 17;

        // Set pin numbering mode to BCM
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // Create digital input pin

//        GpioPinDigitalInput pin[] = {
//                PinState pinValue27 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber27)).getState(),
//                gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber17))
//        };

        GpioPinDigitalInput pin27 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber27), PinPullResistance.PULL_DOWN);
        GpioPinDigitalInput pin17 = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber17), PinPullResistance.PULL_DOWN);

        PinState pinValue27 = pin27.getState();
        PinState pinValue17 = pin17.getState();

        int activePin = 0;

        if (pin27.isHigh()) {
            activePin = gpioPinNumber27;
        } else if (pin17.isHigh()) {
            activePin = gpioPinNumber17;
        }

        switch (activePin) {
            case 27:
                sensorData.setSensorName("Sensor 27");
                sensorData.setSensorLocation("Pool");
                //if pin state is low, do this:
                //check which pin was high and get data sensorData.getSensorLocation(), sensorData.getSensorName() ....
                //switch,
                //send email after trigger

                sensorDataService.insertSensorDataStatus(dateTime, sensorData.getSensorLocation(), sensorData.getSensorName(), 0);
                console.println("GPIO " + gpioPinNumber27 + "is :" + pinValue27);

                //   pin27.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

                break;

            case 17:
                sensorData.setSensorName("Sensor 17");
                sensorData.setSensorLocation("Chloride");
                //if pin state is low, do this:
                //check which pin was high and get data sensorData.getSensorLocation(), sensorData.getSensorName() ....
                //switch,
                //send email after trigger
                sensorDataService.insertSensorDataStatus(dateTime, sensorData.getSensorLocation(), sensorData.getSensorName(), 0);
                console.println("GPIO " + gpioPinNumber17 + "is :" + pinValue17);
                //pin17.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
                break;

            default:
                console.println("GPIO " + gpioPinNumber17 + " is :" + pinValue17);

                console.println("GPIO " + gpioPinNumber27 + " is :" + pinValue27);
                break;

        }

        gpio.shutdown();
        //TODO:for loop to close all pins
        gpio.unprovisionPin(pin27);
        gpio.unprovisionPin(pin17);

    }

    /**
     * Index page
     *
     * @param model
     * @return all sensor data records
     */
    @GetMapping({"/", "/list"})
    public String getList(Model model) {

        model.addAttribute("list", sensorDataService.getAll());

        return "list";
    }

    @GetMapping("/pagination")
    public String pagination(Model model,
                             @RequestParam(required = false, defaultValue = "0") int page,
                             @RequestParam(required = false, defaultValue = "5") int size
    ) {
        Page<SensorData> sensorDataPage = sensorDataRepository.findAll(PageRequest.of(page, size));
        model.addAttribute("pagination", sensorDataPage);
        model.addAttribute("numbers", IntStream.range(0, sensorDataPage.getTotalPages()).toArray());

        return "list";
    }


    /**
     * Method to get sensor by it`s id
     *
     * @param id
     * @param model
     * @return sensor details
     */
    @GetMapping("/show{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("sensor", sensorService.getById(id));

        return "sensor";
    }

    /**
     * Method to delete sensor
     *
     * @param id
     * @param model
     * @return redirects to sensors list
     */
    @GetMapping("/delete{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        sensorService.delete(id);
        model.addAttribute("sensor", sensorService.getAll());

        return "redirect:/sensors";
    }

    /**
     * Method to show update form for sensor
     *
     * @param id
     * @param model
     * @return JSP template
     */
    //atnaujinat išrašą, pirmiausia reikia jį parodyti
    @GetMapping("/update{id}")
    public String updateById(@RequestParam("id") int id, Model model) {
        //Kai užkrauname įrašo redagavimo formą, privalome jos laukelius užpildyti įrašo informacija
        model.addAttribute("sensor", sensorService.getById(id));

        return "update";
    }

    /**
     * Method action to update sensor
     *
     * @param sensor
     * @return redirect to sensor details
     */
    @PostMapping("/update-sensor")
    public String update(@ModelAttribute("sensor") Sensor sensor) {
        sensorService.update(sensor);

        return "redirect:/show?id=" + sensor.getId();
    }

    /**
     * Method to show for registration form
     *
     * @param model
     * @return JSP registration form
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    /**
     * Method action to store new user
     *
     * @param userForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {

            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    /**
     * Method to show login form
     *
     * @param model
     * @param error
     * @param logout
     * @return JSP login form
     */
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi");

        if (logout != null)
            model.addAttribute("message", "Sėkmingai atsijungėte");

        return "login";
    }

    @GetMapping("/403")
    public String _403() {

        return "403";
    }

    /**
     * Method to show all sensors
     *
     * @param model
     * @return sensors list
     */
    @GetMapping("/sensors")
    public String getAllSensors(Model model) {
        model.addAttribute("sensors", sensorService.getAll());

        return "sensors";
    }

    /**
     * Method to show all users
     *
     * @param model
     * @return users list
     */
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", getUserService.getAll());

        return "users";
    }

    /**
     * Method to show update form for user edit
     *
     * @param id
     * @param model
     * @return JSP form TO update user
     */
    //atnaujinat išrašą, pirmiausia reikia jį parodyti
    @GetMapping("/update_user{id}")
    public String updateUserById(@RequestParam("id") int id, Model model) {
        //Kai užkrauname įrašo redagavimo formą, privalome jos laukelius užpildyti įrašo informacija
        model.addAttribute("user", getUserService.getById(id));

        return "update_user";
    }

    /**
     * Method to show user details
     *
     * @param id
     * @param model
     * @return JSP form to show user details
     */
    @GetMapping("/show_user{id}")
    public String getUserById(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", getUserService.getById(id));

        return "user";
    }

    /**
     * Method action to update user
     *
     * @param user
     * @return redirect to user details by it`s id
     */
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        getUserService.update(user);

        return "redirect:/show_user?id=" + user.getId();
    }

    /**
     * Method to delete user
     *
     * @param id
     * @param model
     * @return redirect to user list
     */
    @GetMapping("/delete_user{id}")
    public String deleteUser(@RequestParam("id") int id, Model model) {
        getUserService.delete(id);
        model.addAttribute("delete_user", getUserService.getAll());

        return "redirect:/users";
    }

    /**
     * Method to show pool sensors data
     *
     * @return JSP form of pool sensors triggers
     */
    @GetMapping("/pool_data")
    public String getPoolData() {
        return "pool_data";
    }


}
