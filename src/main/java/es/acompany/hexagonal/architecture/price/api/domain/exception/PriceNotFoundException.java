package es.acompany.hexagonal.architecture.price.api.domain.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message){
        super(message);
    }
    
}
