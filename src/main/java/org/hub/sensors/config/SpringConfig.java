package org.hub.sensors.config;

import org.hub.sensors.model.*;
import org.hub.sensors.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// @Configuration - žymi konfigūracijos komponentą
// viduje leidžia kurti bean per metodus su @Bean
@Configuration
public class SpringConfig {
    // Bean- tai objektai, kurie sudaro Spring aplikacijos pagrindą.
    // Paprastai tai Java klasė, realizuojanti tam tikrą interfeisą ir JavaBean specifikaciją.
    // Bean atitinka Singleton šabloną - programinės įrangos projektavimo schema,
    // kuri riboja klasės įvykdymą vienu „vieninteliu“ egzemplioriumi.
    // Tai naudinga, kai reikia tiksliai vieno objekto, norint koordinuoti veiksmus visoje sistemoje.
    @Bean
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'

    @Qualifier("SensorDataDAOImpl")
    public SensorDataDAO getSensorDataDAO() {
        return new SensorDataDAOImpl();
    }

    @Bean
    @Qualifier("SensorDataServiceImpl")
    public SensorDataService getSensorServiceData() {
        return new SensorDataServiceImpl();
    }

    @Bean
    @Qualifier("GpioPinDAOImpl")
    public GpioPinDAO getGpioPinDAO(){
        return new GpioPinDAOImpl();
    }

    @Primary
    @Bean
    @Qualifier("GpioPinServiceImpl")
    public GpioPinService getGpioPinService(){
        return new GpioPinServiceImpl();
    }

    @Bean
    @Qualifier("SensorDAOImpl")
    public SensorDAO getSensorDAO() {
        return new SensorDAOImpl();

    }

    @Bean
    @Primary
    @Qualifier("SensorServiceImpl")
    public SensorService getSensorService() {
        return new SensorServiceImpl();
    }

    @Bean
    @Qualifier("UserDAOImpl")
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Bean
    @Qualifier("SlaveDAOImpl")
    public SlaveDAO getSlaveDAO() {
        return new SlaveDAOImpl();
    }

//    @Bean
//    @Qualifier("RS485ServiceImpl")
//    public RS485Service getRS485DAOService() {
//        return new RS485ServiceImpl();
//    }

    @Bean
    @Primary
    @Qualifier("UserServiceImpl")
    public UserService getUserService() {
        return new UserServiceImpl();
    }
}
