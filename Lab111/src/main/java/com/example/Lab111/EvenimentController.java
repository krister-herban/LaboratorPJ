package com.example.Lab111;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/jpa/evenimente")
public class EvenimentController {

    @Autowired
    private EvenimentRepository evenimentRepository;

    @GetMapping
    public List<Eveniment> getEveniments() {
        return (List<Eveniment>) evenimentRepository.findAll();
    }

    @GetMapping("locatie/{denumire_locatie}")
    public List<Eveniment> getEvenimentsByLocatie(@PathVariable String denumire_locatie) {
        return (List<Eveniment>) evenimentRepository.findByLocatie(denumire_locatie);
    }

    @GetMapping("data/{data_eveniment}")
    public List<Eveniment> getEvenimentsByData(@PathVariable String data_eveniment) {
        return (List<Eveniment>) evenimentRepository.findByData(LocalDate.parse(data_eveniment));
    }

    @PostMapping
    public ResponseEntity<Eveniment> cretateEveniment(@RequestBody Eveniment eveniment) {
        Eveniment evenimentSaved = evenimentRepository.save(eveniment);
        return ResponseEntity.ok(evenimentSaved);
    }

    @PutMapping("/{valoare_id}")
    public ResponseEntity<Eveniment> updateEveniment(@PathVariable Integer valoare_id, @RequestBody Eveniment eveniment1){
        Eveniment eveniment=evenimentRepository.findById(valoare_id).orElseThrow(()->new RuntimeException("Nu s-a gasit evenimentul"));
        eveniment.setDenumire(eveniment1.getDenumire());
        eveniment.setLocatie(eveniment1.getLocatie());
        eveniment.setData(eveniment1.getData());
        eveniment.setTime(eveniment1.getTime());
        eveniment.setPret_bile(eveniment1.getPret_bile());
        Eveniment updatedEveniment=evenimentRepository.save(eveniment);
        return ResponseEntity.ok(updatedEveniment);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteEveniment(@PathVariable Integer id) {
        evenimentRepository.deleteById(id);
        return ResponseEntity.ok("Eveniment sters");
    }

}
