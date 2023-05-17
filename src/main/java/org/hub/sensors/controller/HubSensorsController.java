package org.hub.sensors.controller;

import org.hub.sensors.model.Sensor;
import org.hub.sensors.model.User;
import org.hub.sensors.service.SecurityService;
import org.hub.sensors.service.SensorService;
import org.hub.sensors.service.UserService;
import org.hub.sensors.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // autowire- naudojamas automatinei priklausomybių injekcijai
    // Kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("SensorServiceImpl")
    public SensorService sensorService;

    /**
     * Returns all list from DB
     *
     * @param model
     * @return
     */
    @GetMapping({"/", "/list"})
    public String getList(Model model) {
        model.addAttribute("list", sensorService.getAll());
        return "list";
    }

    @GetMapping("/show{id}")
    public String getByid(@RequestParam("id") int id, Model model) {
        model.addAttribute("sensor", sensorService.getById(id));
        return "sensor";
    }
    @GetMapping("/delete{id}")
    public String delete (@RequestParam("id") int id, Model model){
        sensorService.delete(id);
        model.addAttribute("list", sensorService.getAll());
        return "list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ("sensor")Sensor sensor){
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


}
