package nl.novi.yannickhoebers.reversedrecipe.controller;

import nl.novi.yannickhoebers.reversedrecipe.model.Cook;
import nl.novi.yannickhoebers.reversedrecipe.repository.CookRepository;
import nl.novi.yannickhoebers.reversedrecipe.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CookController {

    @Autowired
    private CookRepository cookRepository;

    @GetMapping("/cooks")
    public List<Cook> getAllCooks() {
        return cookRepository.findAll();
    }

    @GetMapping("/cook/{id}")
    public Cook getCookByID(@PathVariable Long id) {
        Optional<Cook> optCook = cookRepository.findById(id);
        if(optCook.isPresent()) {
            return optCook.get();
        }else {
            throw new NotFoundException("cook not found with id " + id);
        }
    }

    @PostMapping("/cooks")
    public Cook createCook(@Valid @RequestBody Cook cook) {
        return cookRepository.save(cook);
    }

    @PutMapping("/cooks/{id}")
    public Cook updateCook(@PathVariable Long id,
                           @Valid @RequestBody Cook cookUpdated) {
        return cookRepository.findById(id)
                .map(cook -> {
                    cook.setName(cookUpdated.getName());
                    cook.setSurName(cookUpdated.getSurName());
                    cook.setAge(cookUpdated.getAge());
                    cook.setGender(cookUpdated.getGender());
                    return cookRepository.save(cook);
                }).orElseThrow(() -> new NotFoundException("Cook not found with id " + id));
    }
}