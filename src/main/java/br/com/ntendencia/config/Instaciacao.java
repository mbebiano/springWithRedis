package br.com.ntendencia.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Calendar data = new GregorianCalendar(2020, 9, 20);
		
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
		maria.setItemsEmprestados(Arrays.asList(livro, mouse));
		bob.setItemsEmprestados(Arrays.asList(travanao));
		ContratoEmprestimo contrato1 = new ContratoEmprestimo(null, sdf.format(data.getTime()), sdf.parse("08/10/2020"), jimmy, mariaDTO);
		ContratoEmprestimo contrato2 = new ContratoEmprestimo(null, sdf.parse("06/10/2020"), sdf.parse("10/10/2020"), alex, bobDTO);
		
		contrato1.setItemEmprestado2(livro);
		contrato1.setItemEmprestado2(mouse);
		contrato2.setItemEmprestado2(travanao);
		
		contratoEmprestimoRepo.save(contrato1);
		contratoEmprestimoRepo.save(contrato2);
		
		System.out.println("Salvo");
		System.out.println(sdf.format(new Date()));
		
	}
	
}
