package es.acompany.hexagonal.architecture.price.api.domain.exception;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}