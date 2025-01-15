package com.example.Lab111;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenimentRepository extends JpaRepository<Eveniment, Integer> {
    List<Eveniment> findByLocatie(String locatie);
    List<Eveniment> findByData(LocalDate date);

}
