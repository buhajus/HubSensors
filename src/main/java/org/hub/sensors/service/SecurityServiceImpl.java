package org.hub.sensors.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

// SecurityService reikalingas esamo vartotojo prisijungimui ir automatiniam prisijungimui vartotojo po registracijos
@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    // Skirtas pateikti terminale įrašus programuotojui apie sėkmingai įvykdytus žingsnius
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    // Naudojamas vartotojo vardo atvaizdavimui menu.jsp formoje, t.y. kur rašoma 'Atsijungti, Algis'
    // ${pageContext.request.userPrincipal.name != null}">
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername() + ((UserDetails) userDetails).getAuthorities();
        }

        return null;
    }

    @Override
    // Naudojamas vartotojo prisijungimui prie sistemos
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //Sukuriamas požymis, skirtas prisijungimo vardo ir slaptažodžio atpažinimui- ar asmuo yra tas, kuo dedasi esant
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        // Požymio atpažinimas
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Jeigu vartotojas sėkmingai atpažintas pagal sukurtą požymį
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Automatinis prisijungimas %s sėkmingas!", username));
        }
    }
}
