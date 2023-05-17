package org.hub.sensors.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
// Vartotojų autentifikacijai ir autorizacijai prisijungiant prie sistemos
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    // kriptografijos algoritmas (maišos funkcijos) slaptažodžių kodavimui
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/sensors").hasAuthority("admin") // tik admin gali matyti visas atliktas skaičiavimo operacijas
                     .antMatchers("/users").hasAuthority("admin") // tik admin gali matyti visas atliktas skaičiavimo operacijas
                .antMatchers("/registration") // leidžiame registruotis neprisijungusiems vartotojams
                //.antMatchers("/resources/**", "/skaiciai").hasRole("admin") // tik admin gali matyti visas atliktas skaičiavimo operacijas
                //.antMatchers("/resources/**", "/registruoti") // leidžiame registruotis neprisijungusiems vartotojams
                .permitAll()
                .anyRequest().authenticated() // kiti puslapiai pasiekiami tik prisijungusiems (autorizuotiems) vartotojams
                .and()
            .formLogin() // neprisijungusiam vartotojui leidžiame prieiti prie prisijungimo puslapio
                .loginPage("/login")
                .permitAll()
                .and()
            .logout() // leidžiame visiems atsijungti
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    // Autentifikacijos vadybininkas reikalingas vartotojų autentifikacijai
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    // Konfigūracija slaptažodžių kodavimui
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}