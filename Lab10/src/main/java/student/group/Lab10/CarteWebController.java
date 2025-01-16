package student.group.Lab10;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CarteWebController {

    @Autowired
    private CarteRepository carteRepository;

    @GetMapping("/lista-carti")
    public String getListaCarti(Model model) {
        model.addAttribute("message", "Lista de carti");
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }

    @PostMapping("/operatii")
    public String operatii(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titlu,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String adauga,
            @RequestParam(required = false) String modifica,
            @RequestParam(required = false) String sterge,
            @RequestParam(required = false) String filtreaza,
            Model model) {

        String mesaj = "";

        if (adauga != null) {
            if (!Objects.equals(isbn, "") && !Objects.equals(titlu, "") && !Objects.equals(autor, "")) {
                carteRepository.save(new Carte(isbn, titlu, autor));
                mesaj = "Adaugare realizata";
                model.addAttribute("lista", carteRepository.findAll());
            } else {
                mesaj = "Toate caracteristicile trbuie puse";
                model.addAttribute("lista", carteRepository.findAll());
            }
        } else if (modifica != null) {
            Optional<Carte> carteOpt = carteRepository.findById(isbn);
            if (carteOpt.isPresent() && !Objects.equals(titlu, "") && !Objects.equals(autor, "")) {
                Carte carte = carteOpt.get();
                carte.setTitlu(titlu);
                carte.setAutor(autor);
                carteRepository.save(carte);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost modificata";
                model.addAttribute("lista", carteRepository.findAll());
            } else {
                mesaj = "Nu e nicio cartr cu ISBN-ul introdus";
                model.addAttribute("lista", carteRepository.findAll());
            }
        } else if (sterge != null) {
            if (carteRepository.existsById(isbn)) {
                carteRepository.deleteById(isbn);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost stearsa";
                model.addAttribute("lista", carteRepository.findAll());
            } else {
                mesaj = "Nu e nicio cartr cu ISBN-ul introdus";
                model.addAttribute("lista", carteRepository.findAll());
            }
        } else if (filtreaza != null) {
            if (autor != null && !autor.isEmpty()) {
                model.addAttribute("lista", carteRepository.findByAutor(autor));
                mesaj = "Cartile urmatoare apartin autorului " + autor + ".";
            } else {
                model.addAttribute("lista", carteRepository.findAll());
                mesaj = "Toate cartile au fost afisate.";
            }
        }

        model.addAttribute("str", mesaj);
        return "carti";
    }

}
