package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class MutuarioServicesImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();

    @Mock
    private MutuarioRepository mutuarioRepo;

    //private final MutuarioService mutuarioService = new MutuarioServicesImpl(mutuarioRepo, modelMapper);

    private MutuarioService mutuarioService() {
        return new MutuarioServicesImpl(mutuarioRepo, mapper);
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
        mutuarioDTO.setIdMutuario(mutuario().getIdMutuario());
        mutuarioDTO.setName(mutuario().getName());
        mutuarioDTO.setEmail(mutuario().getEmail());
        return mutuarioDTO;
    }

    @Before
    public void setupMutuarioTest() {

    }

    @Test
    public void testarSalvar() {
        // cenário
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(0);
        when(mutuarioRepo.save(Mockito.any())).thenReturn(mutuario);
        Mutuario mutuarioSalvo = mutuarioService().salvarMutuario(mutuarioDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(0), mutuarioSalvo.getIdMutuario()); // não retorna o mutuante salvo dto
    }

    @Test
    public void testarDeletarSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuario));
        mutuarioService().deleteMutuario(String.valueOf(mutuario.getIdMutuario()));

        // verificação
        verify(mutuarioRepo, times(1)).deleteById(String.valueOf(mutuario.getIdMutuario()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarDeletarFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        mutuarioService().deleteMutuario(String.valueOf(mutuario.getIdMutuario()));

    }

    @Test
    public void testarBuscarIdSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuario));
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().findById(String.valueOf(mutuario.getIdMutuario()));

        // verificação
        Assert.assertEquals("1", String.valueOf(mutuarioEncontradoDTO.getIdMutuario()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarIdFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().findById(String.valueOf(mutuario.getIdMutuario()));
    }

    @Test
    public void testarBuscarNomeSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findByName(Mockito.any())).thenReturn(Optional.of(mutuario));
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().procurarPorNome(mutuario.getName());

        // verificação
        Assert.assertEquals("Mattheus", mutuarioEncontradoDTO.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarNomeFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findByName(Mockito.any())).thenReturn(Optional.empty());
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().procurarPorNome(mutuario.getName());
    }

    @Test
    public void testarBuscarTodosSucesso() {
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        // cenário
        List<Mutuario> list = mutuarioService().listaMutuarios();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosFalha() {
        // cenário
        List<Mutuario> list = mutuarioService().listaMutuarios();
        when(mutuarioRepo.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    public void testarBuscarTodosDTOSucesso() {
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        // cenário
        List<MutuarioDTO> list = mutuarioService().listaMutuariosDTO();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosDTOFalha() {
        // cenário
        List<MutuarioDTO> list = mutuarioService().listaMutuariosDTO();
        when(mutuarioRepo.findAll()).thenReturn(Collections.emptyList());
    }
}
