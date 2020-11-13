package br.com.ntendencia.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper;
        mapper = new ModelMapper();

//        PropertyMap<Usuario, UsuarioDTO> usuariotoUsuarioDTOPropertyMap = new PropertyMap<Usuario, UsuarioDTO>() {
//            protected void configure() {
//                map().setIdUser(source.getId());
//            }
//        };
//        mapper.addMappings(usuariotoUsuarioDTOPropertyMap);
// rever caso necessitar
        return mapper;
    }
}
