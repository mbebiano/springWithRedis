package br.com.ntendencia.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;



import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;
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
		
		
		contratoEmprestimoRepo.deleteAll();
		itemEmprestadoRepo.deleteAll();
		mutuarioRepo.deleteAll();
		mutuanteRepo.deleteAll();
		
		
		Mutuante jimmy = new Mutuante(null, "Jimmy Flauteado", "jimmy@flauteadomagico.com");
		mutuanteRepo.save(jimmy);
		Mutuante alex = new Mutuante(null, "Alex Flauteado", "alex@flauteadomagico.com");
		mutuanteRepo.save(alex);
		Mutuario maria = new Mutuario(null, "Maria", "maria@teste.com", "maria8");
		mutuarioRepo.save(maria);
		Mutuario bob = new Mutuario(null, "Bob", "bob@teste.com", "bobo8");
		mutuarioRepo.save(bob);
		
		
		MutuarioDTO mariaDTO = new MutuarioDTO(maria);
		MutuarioDTO bobDTO = new MutuarioDTO(bob);
		
		ItemEmprestado livro = new ItemEmprestado(null, "Harry Potter");
		itemEmprestadoRepo.save(livro);
		ItemEmprestado travanao = new ItemEmprestado(null, "Modelo compacto");
		itemEmprestadoRepo.save(travanao);
		ItemEmprestado mouse = new ItemEmprestado(null, "Mouse");
		itemEmprestadoRepo.save(mouse);
		
		jimmy.getItemParaEmprestar().add(mouse);
		ContratoEmprestimo contrato1 = new ContratoEmprestimo(null, LocalDate.parse("2020-09-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")),  LocalDate.parse("2020-10-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")), alex, mariaDTO);
		ContratoEmprestimo contrato2 = new ContratoEmprestimo(null, LocalDate.parse("2020-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")),  LocalDate.parse("2020-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")), jimmy, bobDTO);
		
		contrato1.setItemEmprestado2(livro);
		contrato1.setItemEmprestado2(travanao);
		contrato2.getItemEmprestado().addAll(jimmy.getItemParaEmprestar());
		
		maria.getItemsEmprestados().addAll(contrato1.getItemEmprestado());
		bob.getItemsEmprestados().addAll(contrato2.getItemEmprestado());
		mutuarioRepo.save(maria);
		mutuarioRepo.save(bob);
		
		mutuanteRepo.save(jimmy);
		
		contratoEmprestimoRepo.save(contrato1);
		contratoEmprestimoRepo.save(contrato2);
		
		System.out.println("Salvo");
		
	}
	
}
