package org.hub.sensors.controller;


import com.pi4j.io.gpio.*;
import org.hub.sensors.model.GpioPin;
import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.SensorData;
import org.hub.sensors.model.User;
import org.hub.sensors.repository.GpioRepository;
import org.hub.sensors.repository.SensorDataRepository;
import org.hub.sensors.repository.SensorRepository;
import org.hub.sensors.service.*;
import org.hub.sensors.validator.GpioValidator;
import org.hub.sensors.validator.SensorValidator;
import org.hub.sensors.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    final GpioController gpio = GpioFactory.getInstance();


    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SensorValidator sensorValidator;

    @Autowired
    private GpioValidator gpioValidator;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private GpioRepository gpioRepository;
    @Autowired
    private GpioPinService gpioPinService;


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
     * @param outputForm
     * @return redirect to list of all sensors
     */
    @PostMapping("/add-new-sensor")
    public String addSensor(@Valid @ModelAttribute("sensor") Sensor sensor,
                            BindingResult bindingResult,
                            @RequestParam("gpio") int gpio,
                            ModelMap outputForm) {

        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add_new_sensor";
        }

        //GpioPin gpioPin = gpioPinService.getById(gpioId);
        //   GpioPin gpioPin = gpioPinService.getGpioPin(gpio);
        if (gpio != 0) {
            outputForm.put("sensorName", sensor.getSensorName());
            outputForm.put("sensorModel", sensor.getSensorModel());
            outputForm.put("gpio", String.valueOf(gpio));
            sensorService.save(sensor);

        }

        //sensorService.save(new Sensor(sensorName, sensorModel, gpio));
        return "redirect:/sensors_list";
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

        model.addAttribute("gpioPinList", gpioRepository.findAll());

        model.addAttribute("gpioList", sensorService.getGpio());
        //  model.addAttribute("gpioById", gpioPinList);
        model.addAttribute("gpioPins", gpioPinService.getAllGpioPins());


        model.addAttribute("sensor", new Sensor());

        // model.addAttribute("gpio", gpio.getGpio());

        //grąžiname JSP failą, kuris turi būti talpinamas "webapp -> WEB-INF ->  JSP" folderi
        return "add_new_sensor";
    }

    @GetMapping("/add_new_gpio")
    public String addNewGpio(Model model) {
        model.addAttribute("gpio", new GpioPin());

        return "add_new_gpio";
    }

    @PostMapping("/add-new-gpio")
    public String addGpio(Model model,
                          @Valid @ModelAttribute("gpio") GpioPin gpioPinValid,
                          BindingResult bindingResult,
                          @RequestParam("gpio") Integer gpioPin) {

        gpioValidator.validate(gpioPinValid, bindingResult);
        if (bindingResult.hasErrors()) {
            return "add_new_gpio";
        }
        GpioPin gpio = new GpioPin();
        model.addAttribute("gpio", gpio);

        gpio.setGpio(gpioPin);
//        int gpio = Integer.parseInt(gpioPin.get("gpio"));
//        outputForm.put("gpio", gpio);
        gpioRepository.save(gpio);

        return "redirect:/gpio_list";
    }
    /**
     * Method for checking sensors every 5 sec and if they are active - store data into DB
     */

    //  @GetMapping("/status")
    //@Scheduled(fixedDelay = 3600000 ) //every one hour


    /**
     * Index page
     *
     * @param model
     * @return all sensor data records with pagination
     */
//    @GetMapping({"/", "/list"})
//    public String getList(Model model) {
//
//        model.addAttribute("list", sensorDataService.getAll());
//
//        return "list";
//    }
    @GetMapping({"/", "/sensors_history_list"})
    public String getList(Model model,
                          @RequestParam(required = false, defaultValue = "0") int page,
                          @RequestParam(required = false, defaultValue = "5") int size
    ) {
        Page<SensorData> sensorDataPage = sensorDataRepository.
                findAll(PageRequest.of(page, size, Sort.Direction.DESC, "date"));
        model.addAttribute("list", sensorDataPage);
        model.addAttribute("numbers", IntStream.range(0, sensorDataPage.getTotalPages()).toArray());
        model.addAttribute("sortASC", Sort.Direction.ASC);
        model.addAttribute("sortDESC", Sort.Direction.DESC);
        model.addAttribute("totalRecords", sensorDataPage.getTotalElements());
        model.addAttribute("getNumber", sensorDataPage.getNumber());

        return "sensors_history_list";
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

        return "redirect:/sensors_list";
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

        return "update_sensor";
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
    @GetMapping("/sensors_list")
    public String getAllSensors(Model model) {
        model.addAttribute("sensors", sensorService.getAll());

        return "sensors_list";
    }

    @GetMapping("/gpio_list")
    public String getAllGpio(Model model) {
        model.addAttribute("gpio", gpioRepository.findAll());

        return "gpio_list";
    }

    /**
     * Method to show all users
     *
     * @param model
     * @return users list
     */
    @GetMapping("/users_list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", getUserService.getAll());

        return "users_list";
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

        return "redirect:/users_list";
    }

    @GetMapping("/delete_gpio{id}")
    public String deleteGpio(@RequestParam("id") int id,
                             Model model) {

        gpioRepository.deleteById(id);
        model.addAttribute("delete_gpio", gpioRepository.findAll());

        return "redirect:/gpio_list";
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


    public List<GpioPinDigitalInput> gpioPin() {
        List<Sensor> listOfActiveSensors = sensorService.getAll();
        List<GpioPinDigitalInput> gpioPins = new ArrayList<>();

        for (Sensor sensor : listOfActiveSensors) {
            int pinAddress = sensor.getGpio().getGpio();
            GpioPinDigitalInput digitalInput = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(pinAddress), PinPullResistance.PULL_DOWN);
            gpioPins.add(digitalInput);
        }
        gpio.shutdown();

        for (int i = 0; i <= sensorService.getAll().size(); i++) {
            gpio.unprovisionPin(gpioPin().get(i));
        }

        return gpioPins;

    }


    @GetMapping("/listofpins")
    public void setGpioPinList() throws InterruptedException {

        for (int i = 0; i <= sensorService.getAll().size(); i++) {
            System.out.println("State " + gpioPin().get(i).getState());
            if (gpioPin().get(i).isHigh()) {
                System.out.println("if hihgh: " + i);
                //tada is gpio saraso gauti gpio pino numeri

            }
        }


        // System.out.println("\n\ngpio digital name " + gpioPin().getName() + "\n\n");

        // System.out.println("\n\ngpio digital pin " + gpioPin().getPin() + "\n\n");
        //    System.out.println("\n\ngpio digital state " + gpioPin() + "\n\n");

        GpioPin pins[] = gpioRepository.findAll().toArray(new GpioPin[0]);
        Sensor gpioPin[] = sensorService.getAll().toArray(new Sensor[0]);

        for (Sensor sensor : gpioPin) {
            System.out.println("gpio in sensors list " + sensor.getGpio().getGpio());
        }
//        for (int i = 0; i < sensorService.getAll().size(); i++) {
//
//            System.out.println(i);
//        }


        for (GpioPin pin : pins) {
            System.out.println("List of gpio pins " + pin.getGpio());

        }

        for (Sensor listOfActiveSensors : sensorService.getAll()) {

            System.out.println(listOfActiveSensors.getGpio().getGpio());
        }


        Sensor sensor = new Sensor();

        int activePin = 0;


        Map<Integer, String> sensorMap = new HashMap<>();
        //find name by id

        sensorMap.put(activePin, sensorService.getByGpio(activePin).getSensorName());
        //  sensorMap.put(17, sensor1.getSensorName() );
        //System.out.println(sensorMap.keySet());

//        if(sensorMap.containsKey(activePin)){
//
//            sensorMap.values();
//
//
//        }

        if (activePin == 27) {

            sensor.setSensorName((sensorMap.get(activePin)));
            sensorDataService.insertSensorDataStatus("2022-12-15", "tvarktas", sensor.getSensorName(), 0);
            Thread.sleep(5000);
        }

        gpio.shutdown();

        for (int i = 0; i <= sensorService.getAll().size(); i++) {
            gpio.unprovisionPin(gpioPin().get(i));
        }


    }


}
