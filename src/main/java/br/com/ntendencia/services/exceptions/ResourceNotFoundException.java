package br.com.ntendencia.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id){
        super("Recurso não encontrado.Para o Id "+id);
    }
}
