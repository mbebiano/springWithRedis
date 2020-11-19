package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class MutuarioServicesImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();

    @Mock
    private MutuarioRepository mutuarioRepo;


    private ModelMapper modelMapper = new ModelMapper();

    //private final MutuarioService mutuarioService = new MutuarioServicesImpl(mutuarioRepo, modelMapper);

    private MutuarioService mutuarioService() {
        return new MutuarioServicesImpl(mutuarioRepo, modelMapper);
    }

    protected Mutuario mutuario() {
        Mutuario mutuario = new Mutuario();
        mutuario.setIdUsuario("1");
        mutuario.setIdMutuario(1);
        mutuario.setName("Mattheus");
        mutuario.setEmail("mattheusbebiano@gmail.com");
        mutuario.setSenha("Teste");
        return mutuario;
    }

    protected MutuarioDTO mutuarioDTO() {
        MutuarioDTO mutuarioDTO = new MutuarioDTO();
        mutuarioDTO.setName(mutuario().getName());
        mutuarioDTO.setEmail(mutuario().getEmail());
        return mutuarioDTO;
    }

    @Before
    public void setupMutuarioTest() {
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);

        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
    }

    @Test
    public void testarSalvarSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.save(Mockito.any())).thenReturn(mutuario);
        Mutuario mutuarioSalvo = mutuarioService().salvarMutuario(mutuarioDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(1), mutuarioSalvo.getIdMutuario()); // não retorna o mutuario salvo dto
    }


//    @Test
//    public void testarBuscarSucesso() {
//        try {
//            Mockito.when(mutuarioRepo.findById("1"))
//                    .thenReturn(Optional.of(mutuario()));
//            MutuarioDTO mutuarioDTO = mutuarioService().findById(any(String.class));
//            mutuarioDTO.getName();
//            Assert.assertEquals("Mattheus", mutuarioDTO.getName());
//        } catch (Exception ex) {
//            Assert.fail();
//        }
//    }


}
