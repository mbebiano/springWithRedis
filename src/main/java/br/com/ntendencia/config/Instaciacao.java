package br.com.ntendencia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.repositories.MutuanteRepository;

@Configuration
public class Instaciacao implements CommandLineRunner {
	
	@Autowired
	private MutuanteRepository mutuanteRepo;
		
	

	@Override
	public void run(String... args) throws Exception {
		Mutuante jimmy = new Mutuante(null, "Jimmy Flauteado", "jimmy@flauteadomagico.com");
		mutuanteRepo.save(jimmy);
		System.out.println("Salvo");
		
	}
	

	
	
}
