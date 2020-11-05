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
    public List<ItemEmprestado> listarItens(HttpServletRequest request) {
        if (request.getServletPath().startsWith("/itemEmprestado/listarItensAtrasados")) {
            return itemEmprestadoService.listarItensEmAtraso();
        }
        return itemEmprestadoService.listarItensEmprestados();
    }

    @GetMapping("/buscarPorId/{id}")
    public Optional<ItemEmprestado> buscarPorId(@PathVariable String id) {
        return itemEmprestadoService.procurarItemEmprestado(id);
    }

}
