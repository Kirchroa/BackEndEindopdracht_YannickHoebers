package nl.novi.yannickhoebers.reversedrecipe.controller;

import nl.novi.yannickhoebers.reversedrecipe.model.Cook;
import nl.novi.yannickhoebers.reversedrecipe.repository.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api")
public class CookController {

    @Autowired
    private CookRepository cookRepository;

    @GetMapping("/cooks")
    public List<Cook> getAllCooks() {
        return cookRepository.findAll();
    }
}