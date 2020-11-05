package br.com.ntendencia.resources;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.services.impl.ItemEmprestadoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoLeitura {

    @Autowired
    private ItemEmprestadoServicesImpl itemEmprestadoService;

    @GetMapping("/listarItens")
    public List<ItemEmprestado> listarItens(@RequestParam boolean atrasado) {
        return itemEmprestadoService.listarItens(atrasado);
    }

    @GetMapping("/buscarPorId/{id}")
    public Optional<ItemEmprestado> buscarPorId(@PathVariable String id) {
        return itemEmprestadoService.procurarItemEmprestado(id);
    }

}
