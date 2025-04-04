package es.acompany.hexagonal.architecture.price.api.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
    private String message;
    private List<String> details;
}
