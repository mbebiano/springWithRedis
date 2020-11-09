package br.com.ntendencia.resources;


import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.services.impl.ItemEmprestadoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoLeitura {

    @Autowired
    private ItemEmprestadoServicesImpl itemEmprestadoService;

    @GetMapping("/listarItens")
    public List<ItemEmprestadoDTO> listarItensDTO(@RequestParam(required = false) boolean atrasado,
                                            @RequestParam(required = false) boolean disponiveis) {
        return itemEmprestadoService.listarItensDTO(atrasado, disponiveis);
    }

    @GetMapping("/buscarPorId/{id}")
    public ItemEmprestadoDTO buscarPorId(@PathVariable String id) {
        return itemEmprestadoService.procurarItemEmprestadoDTO(id);
    }
}
