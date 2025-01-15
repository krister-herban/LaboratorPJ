package student.group.Lab9_SDJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Lab9SdjpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(Lab9SdjpaApplication.class);
	@Autowired
	private MasinaSpringDataJpaRepository masinaSpringDataJpaRepository;

	private void adaugareMasina() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Numar inmatriculare: ");
		String nrInmatriculare = scanner.nextLine();
		System.out.print("Marca: ");
		String marca = scanner.nextLine();
		System.out.print("Anul fabricatiei: ");
		int anulFabricatiei = scanner.nextInt();
		scanner.nextLine(); // Consumă newline
		System.out.print("Culoare: ");
		String culoare = scanner.nextLine();
		System.out.print("Numar de kilometri: ");
		int nrKm = scanner.nextInt();

		Masina masina = new Masina(nrInmatriculare, marca, anulFabricatiei, culoare, nrKm);
		masinaSpringDataJpaRepository.save(masina);
		System.out.println("Masina adaugata");
		logger.info("Masina cu numarul de inmatriculare {} adaugata", nrInmatriculare);
	}

	private void stergereNrInmatriculare() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduceti numarul de inmatriculare pentru stergere: ");
		String nrInmatriculare = scanner.nextLine();
		masinaSpringDataJpaRepository.deleteById(nrInmatriculare);
		System.out.println("Masina a fost stearsa");
		logger.info("Masina cu numarul de inmatriculare {} a fost stearsa.", nrInmatriculare);
	}

	private void cautareNrInmatriculare() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduceti numarul de inmatriculare pentru cautare: ");
		String nrInmatriculare = scanner.nextLine();
		List<Masina> masini = masinaSpringDataJpaRepository.findByNumarInmatriculare(nrInmatriculare);
		if (masini.isEmpty()) {
			System.out.println("Nicio masina gasita cu acest numar de inmatriculare.");
			logger.info("Nicio masina gasita cu acest numar de inmatriculare.");
		} else {
			masini.forEach(System.out::println);
			masini.forEach(masina -> logger.info("Masina gasita: {}", masina));
		}
	}

	private void toateMasinile() {
		List<Masina> masini = masinaSpringDataJpaRepository.findAll();
		System.out.println("\nToate masinile:");
		masini.forEach(System.out::println);
		masini.forEach(masina -> logger.info("Masina: {}", masina));
	}

	private void masiniMarcaTastatura() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduceti marca: ");
		String marca = scanner.nextLine();
		List<Masina> masini = masinaSpringDataJpaRepository.findByMarca(marca);
		System.out.println("\nMasini cu marca " + marca + ":");
		masini.forEach(System.out::println);
		logger.info("\nMasina cu marca {}:", marca);
		masini.forEach(masina -> logger.info("{}", masina));
	}

	private void masiniSub100000km() {
		List<Masina> masini = masinaSpringDataJpaRepository.findByNrKmLessThan(100000);
		System.out.println("\nMasini sub 100 000km:");
		masini.forEach(System.out::println);
		masini.forEach(masina -> logger.info(" {}", masina));
	}

	private void masiniMaiNoi5Ani() {
		int anCurent = java.time.Year.now().getValue();
		List<Masina> masini = masinaSpringDataJpaRepository.findByAnulFabricatieiGreaterThan(anCurent - 5);
		System.out.println("\nMasini mai noi de 5 ani:");
		masini.forEach(System.out::println);
		masini.forEach(masina -> logger.info("Masina mai noua de 5 ani: {}", masina));
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab9SdjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opt;
		do{
			System.out.println("\n1. adaugare");
			System.out.println("2. stergere dupa nrInmatriculare");
			System.out.println("3. cautare dupa nrInmatriculare");
			System.out.println("4. lista cu toate masinile din baza de date");
			System.out.println("5. nr de masini cu o anumita marca de la tastatura");
			System.out.println("6. masini sub 100 000km");
			System.out.println("7. lista cu masini mai noi de 5 ani");
			System.out.println("opt: ");
			opt=scanner.nextInt();
			switch (opt){
				case 1:
					//adaugare masina
					adaugareMasina();
					break;
				case 2:
					//stergere dupa nrInmatriculare
					stergereNrInmatriculare();
					break;
				case 3:
					//cautare dupa nrInmatriculare
					cautareNrInmatriculare();
					break;
				case 4:
					//toate masinile
					System.out.println("\nToate masinile: ");
					masinaSpringDataJpaRepository.findAll().forEach(System.out::println);
					masinaSpringDataJpaRepository.findAll().forEach(m->logger.info("{}\n", m));
					break;
				case 5:
					//masini cu o anumita marca de la tastatura
					masiniMarcaTastatura();
					break;
				case 6:
					//masini sub 100 000km
					masiniSub100000km();
					break;
				case 7:
					//masini mai noi de 5 ani
					masiniMaiNoi5Ani();
					break;
				case 0:
					return;
			}
		}while(true);
	}
}
