package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.ItemEmprestadoService;
import br.com.ntendencia.services.MutuanteService;
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
public class ContratoEmprestimoServicesImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();

    @Mock
    private ContratoEmprestimoRepository contratoRepo;
    @Mock
    private ItemEmprestadoService itemService;
    @Mock
    private MutuarioService mutuarioService;

    private ContratoEmprestimoService contratoEmprestimoService() {
        return new ContratoEmprestimoServicesImpl(contratoRepo, itemService, mutuarioService, mapper);
    }

    protected ContratoEmprestimo contratoEmprestimo() {
        ContratoEmprestimo contratoEmprestimo = new ContratoEmprestimo();
        contratoEmprestimo.setIdMutuario("1");
        return contratoEmprestimo;
    }

    protected ContratoEmprestimoDTO contratoEmprestimoDTO() {
        ContratoEmprestimoDTO contratoEmprestimoDTO = new ContratoEmprestimoDTO();
        contratoEmprestimoDTO.setId(contratoEmprestimo().getId());
        contratoEmprestimoDTO.setIdMutuario(contratoEmprestimo().getIdMutuario());
        return contratoEmprestimoDTO;
    }

    @Before
    public void setupMutuarioTest() {
    }

    @Test
    public void testarSalvar() {
        // cenário
        ContratoEmprestimo contratoMock = Mockito.mock(ContratoEmprestimo.class);
        ItemEmprestado itemEmprestadoMock = Mockito.mock(ItemEmprestado.class);
        MutuarioDTO mutuarioDTO = Mockito.mock(MutuarioDTO.class);
        //Mock retorno do find all
        itemEmprestadoMock.seteStatus(EStatus.DISPONIVEL);
        itemEmprestadoMock.setId("1");
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoMock));
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.getListaIdsItens().add("1");
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById("1")).thenReturn(mutuarioDTO);
        when(itemService.procurarItemEmprestado("1")).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo  = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(1), contratoSalvo.getIdContrato());
    }

    /*
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

     */
}
