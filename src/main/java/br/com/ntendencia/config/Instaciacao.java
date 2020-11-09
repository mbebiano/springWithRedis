package br.com.ntendencia.config;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.repositories.MutuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

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

		//Instaciação Mutuante
		Mutuante john = new Mutuante("1", "john@gmail.com","john", 1);
		Mutuante john2 = new Mutuante("2", "john@gmail.com","john", 2);
		Mutuante jose = new Mutuante("3", "jose@gmail.com","jose", 3);
		mutuanteRepo.saveAll(Arrays.asList(john, john2, jose));

		//Instaciação Mutuario

		Mutuario maria = new Mutuario("1", "Maria", "maria@gmail.com", "maria8", 1);
		Mutuario bob = new Mutuario("2", "Bob", "bob@gmail.com", "bob8", 2);
		mutuarioRepo.saveAll(Arrays.asList(maria,bob));

		//Instaciacão Contrato Emprestimo

		ContratoEmprestimo contrato1 = new ContratoEmprestimo("1","1", 1);
		contratoEmprestimoRepo.save(contrato1);

		//Instaciacão Item Emprestado

		ItemEmprestado livro = new ItemEmprestado("Livro","1",7);
		livro.setId("1");
		livro.setIdItemEmprestado(1);
		livro.seteStatus(EStatus.EMPRESTADO);
		livro.setDataEmprestimo(LocalDate.of(2020, 11, 7));
		ItemEmprestado notebook = new ItemEmprestado("Notebook","3",4);
		notebook.seteStatus(EStatus.DISPONIVEL);
		notebook.setId("2");
		notebook.setIdItemEmprestado(2);

		contrato1.getItensEmprestados().add(livro);
		contrato1.setIdMutuario(maria.getIdUsuario());

		itemEmprestadoRepo.saveAll(Arrays.asList(livro, notebook));
		contratoEmprestimoRepo.save(contrato1);
//		//Instaciação Users
//		Mutuante john = new Mutuante("2", "John Ferr", "johny@gmail.com", 2);
//		mutuanteRepo.save(john);
//		Mutuante alex = new Mutuante("3", "Alex Gray", "alex@gmailo.com", 3);
//		mutuanteRepo.save(alex);
//		Mutuario maria = new Mutuario("0", "Maria", "maria@teste.com", "maria8", 0);
//		mutuarioRepo.save(maria);
//		Mutuario bob = new Mutuario("1", "Bob", "bob@teste.com", "bobo8", 1);
//		mutuarioRepo.save(bob);
//
//		//Instaciação mocada de DTOS
//		MutuarioDTO mariaDTO = new MutuarioDTO(maria);
//		MutuarioDTO bobDTO = new MutuarioDTO(bob);
//		MutuanteDTO alexDTO = new MutuanteDTO(alex);
//		MutuanteDTO johnDTO = new MutuanteDTO(john);
//
//		// Instaciação mocada de Items emprestados, setando os Mutuantes
//		ItemEmprestado livro = new ItemEmprestado(null, "Harry Potter");
//		livro.setMutuanteDTO(johnDTO);
//		livro.setMutuarioDTO(mariaDTO);
//		itemEmprestadoRepo.save(livro);
//		ItemEmprestado carro = new ItemEmprestado(null, "Hatch Modelo compacto");
//		carro.setMutuanteDTO(johnDTO);
//		itemEmprestadoRepo.save(carro);
//		ItemEmprestado mouse = new ItemEmprestado(null, "Mouse");
//		mouse.setMutuanteDTO(alexDTO);
//		itemEmprestadoRepo.save(mouse);
//
//		//Instaciação mocada de DTO ItemEmprestado
//		ItemEmprestadoDTO livroDTO = new ItemEmprestadoDTO(livro);
//		john.getItemEmprestadoDTO().add(livroDTO);
//		ItemEmprestadoDTO carroDTO = new ItemEmprestadoDTO(carro);
//		john.getItemEmprestadoDTO().add(carroDTO);
//		ItemEmprestadoDTO mouseDTO = new ItemEmprestadoDTO(mouse);
//		alex.getItemEmprestadoDTO().add(mouseDTO);
//
//		//Instaciação mocada de DTO ItemEmprestado Mutuarios
//		maria.getItemsEmprestadosDTO().add(livroDTO);
//
//		//Instaciação mocada de ContratoEmprestimos emprestados, setando os Mutuantes, Mutuarios, Item Emprestado e datas
//
//		ContratoEmprestimo contrato1 = new ContratoEmprestimo(null, LocalDate.parse("2020-09-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")),  LocalDate.parse("2020-10-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//		contrato1.getItemEmprestadoDTO().add(livroDTO);
//		contrato1.setMutuanteDTO(johnDTO);
//		contrato1.setMutuarioDTO(livro.getMutuarioDTO());
//
//		//Save mutuarios
//		contratoEmprestimoRepo.save(contrato1);
//		mutuarioRepo.save(maria);
//		mutuarioRepo.save(bob);
//		mutuanteRepo.save(john);
//		mutuanteRepo.save(alex);
//		itemEmprestadoRepo.save(livro);

		System.out.println("Salvo");
	}
}
