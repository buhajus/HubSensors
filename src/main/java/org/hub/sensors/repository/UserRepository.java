package com.spring.printer.repository;

import com.spring.printer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    O kas, jeigu nenorime rūpintis paprastomis užklausomis, juk jos visada vienodos.
    Mes nenorime išradinėti dviračio.
    Spring Data JPA repository leidžia:
        - Atsisakyti paprastų užklausų;
        - lengviau rašyti vidutinio sudėtingumo užklausas;
        - Visiškai nekurti repository klasės.
    CRUD įgyvendinantis DAO su Spring Data atrodo taip (žr. 28 kodo eilutę)
        - Nereikia jokios implementacijos!
        - Jei kitoje klasėje pasiimsime su Autowired, galėsime kviesti įvairius metodus, pvz.:
                - userRepository.save(user); Create/ Update
                - userRepository.findAll(); Read
                - userRepository.delete(user); Delete
                - ir kt.
    Galima susikurti bet kokius metodus, sujungiant operacijos pavadinimą (pvz delete),
        tuomet tai, ką norime rasti, pvz User, žodį by bei reikalingus laukus.
    Galimos operacijos:
        find, read, query, count, get, delete, exists
    Neįrašius ieškomojo, bus pasirinkta iš interfeiso.
    Galima patikslinti, ko tiksliau ieškome:
        findFirst.., findTop.., findDistinct.., findUser.., findFirstUser.., findDistinctUser..
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
