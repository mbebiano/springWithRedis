package br.com.ntendencia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.repositories.MutuarioRepository;

@Configuration
public class Instaciacao implements CommandLineRunner {
	
	@Autowired
	private MutuanteRepository mutuanteRepo;
	
	@Autowired
	private MutuarioRepository mutuarioRepo;
	
	@Autowired
	private ItemEmprestadoRepository itemEmprestadoRepo;
	
	@Autowired
	private ContratoEmprestimoRepository contratoEmprestimoRepo;
	

	@Override
	public void run(String... args) throws Exception {
		Mutuante jimmy = new Mutuante(null, "Jimmy Flauteado", "jimmy@flauteadomagico.com");
		mutuanteRepo.save(jimmy);
		Mutuario maria = new Mutuario(null, "Maria", "maria@teste.com", "maria8");
		mutuarioRepo.save(maria);
		
		ItemEmprestado livro = new ItemEmprestado(null, "Harry Potter", jimmy, maria);
		//ContratoEmprestimo contrato1 = new ContratoEmprestimo(null, dataEmprestimo, dataDevolucao, mutuante, mutuario);
		itemEmprestadoRepo.save(livro);
		
		System.out.println("Salvo");
		
	}
	
}
