package br.com.ntendencia.resources;

import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/mutuario")
public class MutuarioEscrita {

    @Autowired
    private MutuarioServicesImpl mutuarioService;

    @PostMapping("/save")
    public String createUser(@RequestBody @Valid MutuarioDTO mutuarioDTO) {
        mutuarioService.salvarMutuario(mutuarioDTO);
        return "Usuário Salvo";
    }
	
    @DeleteMapping("/delete/{id}")
    public String deleteMutuante(@PathVariable String id) {
        mutuarioService.deleteMutuario(id);
        return "Usuario Deletado";
    }

}
