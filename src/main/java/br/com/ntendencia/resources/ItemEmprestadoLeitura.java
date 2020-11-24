package br.com.ntendencia.resources;


import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.services.ItemEmprestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-emprestado")
public class ItemEmprestadoLeitura {

    @Autowired
    private ItemEmprestadoService itemEmprestadoService;

    @GetMapping("/listar-todos")
    public List<ItemEmprestadoDTO> listarItensDTO(@RequestParam(required = false) boolean atrasado,
                                                  @RequestParam(required = false) boolean disponiveis) {
        return itemEmprestadoService.listarItensDTO(atrasado, disponiveis);
    }

    @GetMapping("/procurar-por-id/{id}")
    public ItemEmprestadoDTO buscarPorId(@PathVariable String id) {
        return itemEmprestadoService.procurarItemEmprestadoDTO(id);
    }
}