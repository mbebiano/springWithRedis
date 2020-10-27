package br.com.ntendencia.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.ntendencia.domain.*;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ntendencia.dto.MutuanteDTO;
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
		System.out.println("Deletados");

		//Instaciação Users
		Mutuante john = new Mutuante("2", "John Ferr", "johny@gmail.com", 2);
		mutuanteRepo.save(john);
		Mutuante alex = new Mutuante("3", "Alex Gray", "alex@gmailo.com", 3);
		mutuanteRepo.save(alex);
		Mutuario maria = new Mutuario("0", "Maria", "maria@teste.com", "maria8", 0);
		mutuarioRepo.save(maria);
		Mutuario bob = new Mutuario("1", "Bob", "bob@teste.com", "bobo8", 1);
		mutuarioRepo.save(bob);

		//Instaciação mocada de DTOS
		MutuarioDTO mariaDTO = new MutuarioDTO(maria);
		MutuarioDTO bobDTO = new MutuarioDTO(bob);
		MutuanteDTO alexDTO = new MutuanteDTO(alex);
		MutuanteDTO johnDTO = new MutuanteDTO(john);

		// Instaciação mocada de Items emprestados, setando os Mutuantes
		ItemEmprestado livro = new ItemEmprestado(null, "Harry Potter");
		livro.setMutuanteDTO(johnDTO);
		livro.setMutuarioDTO(mariaDTO);
		itemEmprestadoRepo.save(livro);
		ItemEmprestado carro = new ItemEmprestado(null, "Hatch Modelo compacto");
		carro.setMutuanteDTO(johnDTO);
		itemEmprestadoRepo.save(carro);
		ItemEmprestado mouse = new ItemEmprestado(null, "Mouse");
		mouse.setMutuanteDTO(alexDTO);
		itemEmprestadoRepo.save(mouse);

		//Instaciação mocada de DTO ItemEmprestado
		ItemEmprestadoDTO livroDTO = new ItemEmprestadoDTO(livro);
		john.getItemEmprestadoDTO().add(livroDTO);
		ItemEmprestadoDTO carroDTO = new ItemEmprestadoDTO(carro);
		john.getItemEmprestadoDTO().add(carroDTO);
		ItemEmprestadoDTO mouseDTO = new ItemEmprestadoDTO(mouse);
		alex.getItemEmprestadoDTO().add(mouseDTO);

		//Instaciação mocada de DTO ItemEmprestado Mutuarios
		maria.getItemsEmprestadosDTO().add(livroDTO);

		//Instaciação mocada de ContratoEmprestimos emprestados, setando os Mutuantes, Mutuarios, Item Emprestado e datas

		ContratoEmprestimo contrato1 = new ContratoEmprestimo(null, LocalDate.parse("2020-09-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")),  LocalDate.parse("2020-10-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		contrato1.getItemEmprestadoDTO().add(livroDTO);
		contrato1.setMutuanteDTO(johnDTO);
		contrato1.setMutuarioDTO(livro.getMutuarioDTO());

		//Save mutuarios
		contratoEmprestimoRepo.save(contrato1);
		mutuarioRepo.save(maria);
		mutuarioRepo.save(bob);
		mutuanteRepo.save(john);
		mutuanteRepo.save(alex);
		itemEmprestadoRepo.save(livro);

		System.out.println("Salvo");
	}
}
