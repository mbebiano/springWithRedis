package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.services.MutuanteService;
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
public class MutuanteServicesImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();

    @Mock
    private MutuanteRepository mutuanteRepo;


    private ModelMapper modelMapper = new ModelMapper();

    //private final MutuarioService mutuarioService = new MutuarioServicesImpl(mutuarioRepo, modelMapper);

    private MutuanteService mutuanteService() {
        return new MutuanteServicesImpl(mutuanteRepo, modelMapper);
    }

    protected Mutuante mutuante() {
        Mutuante mutuante = new Mutuante();
        mutuante.setIdMutuante(1);
        mutuante.setIdUsuario("1");
        mutuante.setName("Mattheus");
        mutuante.setEmail("mattheusbebiano@gmail.com");
        return mutuante;
    }

    protected MutuanteDTO mutuanteDTO() {
        MutuanteDTO mutuanteDTO = new MutuanteDTO();
        mutuanteDTO.setIdMutuante(mutuante().getIdMutuante());
        mutuanteDTO.setId(mutuante().getIdUsuario());
        mutuanteDTO.setName(mutuante().getName());
        mutuanteDTO.setEmail(mutuante().getEmail());
        return mutuanteDTO;
    }

    @Before
    public void setupMutuarioTest() {

    }

    @Test
    public void testarSalvar() {
        // cenário
        Mutuante mutuanteMock = Mockito.mock(Mutuante.class);
        //Mock retorno do find all
        when(mutuanteRepo.findAll()).thenReturn(Collections.singletonList(mutuanteMock));
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.save(Mockito.any())).thenReturn(mutuante);
        Mutuante mutuanteSalvo = mutuanteService().salvarMutuante(mutuanteDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(1), mutuanteSalvo.getIdMutuante());
    }

    @Test
    public void testarDeletarSucesso() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuante));
        mutuanteService().deleteMutuante(String.valueOf(mutuante.getIdMutuante()));

        // verificação
        verify(mutuanteRepo, times(1)).deleteById(String.valueOf(mutuante.getIdMutuante()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarDeletarFalha() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        mutuanteService().deleteMutuante(String.valueOf(mutuante.getIdMutuante()));

    }

    @Test
    public void testarBuscarIdSucesso() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuante));
        MutuanteDTO mutuanteEncontradoDTO = mutuanteService().procurarPorId(String.valueOf(mutuante.getIdMutuante()));

        // verificação
        Assert.assertEquals("1", String.valueOf(mutuanteEncontradoDTO.getIdMutuante()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarIdFalha() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        MutuanteDTO mutuanteEncontradoDTO = mutuanteService().procurarPorId(String.valueOf(mutuante.getIdMutuante()));
    }

    @Test
    public void testarBuscarNomeSucesso() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findByName(Mockito.any())).thenReturn(Optional.of(mutuante));
        MutuanteDTO mutuanteEncontradoDTO = mutuanteService().procurarPorNome(mutuante.getName());

        // verificação
        Assert.assertEquals("Mattheus", mutuanteEncontradoDTO.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarNomeFalha() {
        // cenário
        MutuanteDTO mutuanteDTO = mutuanteDTO();
        Mutuante mutuante = mapper.map(mutuanteDTO, Mutuante.class);
        when(mutuanteRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        MutuanteDTO mutuanteEncontradoDTO = mutuanteService().procurarPorNome(mutuante.getName());
    }

    @Test
    public void testarBuscarTodosSucesso() {
        Mutuante mutuanteMock = Mockito.mock(Mutuante.class);
        //Mock retorno do find all
        when(mutuanteRepo.findAll()).thenReturn(Collections.singletonList(mutuanteMock));
        // cenário
        List<Mutuante> list = mutuanteService().listaMutuantes();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosFalha() {
        // cenário
        List<Mutuante> list = mutuanteService().listaMutuantes();
        when(mutuanteRepo.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    public void testarBuscarTodosDTOSucesso() {
        Mutuante mutuanteMock = Mockito.mock(Mutuante.class);
        //Mock retorno do find all
        when(mutuanteRepo.findAll()).thenReturn(Collections.singletonList(mutuanteMock));
        // cenário
        List<MutuanteDTO> list = mutuanteService().listaMutuantesDTO();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosDTOFalha() {
        // cenário
        List<MutuanteDTO> list = mutuanteService().listaMutuantesDTO();
        when(mutuanteRepo.findAll()).thenReturn(Collections.emptyList());
    }
}
