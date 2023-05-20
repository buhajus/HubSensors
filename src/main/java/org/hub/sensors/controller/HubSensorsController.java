package org.hub.sensors.controller;

//import com.pi4j.io.gpio.*;
//import com.pi4j.util.Console;

import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorData;
import org.hub.sensors.model.User;
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


    private static GpioController pin;
    final GpioController gpio = GpioFactory.getInstance();
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


    @GetMapping("/add_new_sensor")
    public String addNewSensor(Model model) {


        //Jeigu Model "number" nepraeina validacijos - per jį grąžinamos validacijos
        //klaidos į View
        model.addAttribute("sensor", new Sensor());
        //grąžiname JSP failą, kuris turi būti talpinamas "webapp -> WEB-INF ->  JSP" folderi
        return "add_new_sensor";
    }

    //  @GetMapping("/status")
    //@Scheduled(fixedDelay = 3600000 ) //every one hour
    @Scheduled(fixedDelay = 5000)
    public void checkSensorStatus() throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = dtf.format(now);
        SensorData sensorData;
        int gpioPinNumber = 27;

        // Set pin numbering mode to BCM
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // Create digital input pin
        GpioPinDigitalInput pin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(gpioPinNumber), PinPullResistance.PULL_DOWN);

        PinState pinValue = pin.getState();

        if (pin.isHigh()) {
            //if pin state is high, do this:

            //check which pin was high and get data sensorData.getSensorLocation(), sensorData.getSensorName() ....
            //switch,
            //send email after trigger
            sensorDataService.insertSensorDataStatus(dateTime, "under desk", "wood bench", 1);
            console.println("GPIO " + gpioPinNumber + "is - :" + pinValue);
            gpio.shutdown();
            gpio.unprovisionPin(pin);
            System.exit(0);


            // Delay for 2 seconds
            //  Thread.sleep(2000);

        }

        console.println("GPIO " + gpioPinNumber + " is :" + pinValue);
        gpio.shutdown();
        gpio.unprovisionPin(pin);


    }


    @GetMapping({"/", "/list"})
    public String getList(Model model) {
        model.addAttribute("list", sensorDataService.getAll());
        return "list";
    }

    @GetMapping("/show{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("sensor", sensorService.getById(id));
        return "sensor";
    }

    @GetMapping("/delete{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        sensorService.delete(id);
        model.addAttribute("sensor", sensorService.getAll());
        return "redirect:/sensors";
    }

    //atnaujinat išrašą, pirmiausia reikia jį parodyti
    @GetMapping("/update{id}")
    public String updateById(@RequestParam("id") int id, Model model) {
        //Kai užkrauname įrašo redagavimo formą, privalome jos laukelius užpildyti įrašo informacija
        model.addAttribute("sensor", sensorService.getById(id));
        return "update";
    }

    @PostMapping("/update-sensor")
    public String update(@ModelAttribute("sensor") Sensor sensor) {
        sensorService.update(sensor);
        return "redirect:/show?id=" + sensor.getId();
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

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

    @GetMapping("/sensors")
    public String getAllSensors(Model model) {
        model.addAttribute("sensors", sensorService.getAll());
        return "sensors";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", getUserService.getAll());
        return "users";
    }

    //atnaujinat išrašą, pirmiausia reikia jį parodyti
    @GetMapping("/update_user{id}")
    public String updateUserById(@RequestParam("id") int id, Model model) {
        //Kai užkrauname įrašo redagavimo formą, privalome jos laukelius užpildyti įrašo informacija
        model.addAttribute("user", getUserService.getById(id));
        return "update_user";
    }

    @GetMapping("/show_user{id}")
    public String getUserById(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", getUserService.getById(id));
        return "user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        getUserService.update(user);
        return "redirect:/show_user?id=" + user.getId();
    }

    @GetMapping("/delete_user{id}")
    public String deleteUser(@RequestParam("id") int id, Model model) {
        getUserService.delete(id);
        model.addAttribute("delete_user", getUserService.getAll());
        return "redirect:/users";
    }

    @GetMapping("/pool_data")
    public String getPoolData() {
        return "pool_data";
    }


}
