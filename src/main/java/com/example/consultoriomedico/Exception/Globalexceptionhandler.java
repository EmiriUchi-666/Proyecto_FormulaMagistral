package com.example.consultoriomedico.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de errores de validación (Bean Validation).
 *
 * Cuando un @RequestBody anotado con @Valid falla (por ejemplo, un
 * campo @NotBlank llega vacío), Spring lanza
 * MethodArgumentNotValidException ANTES de que el método del
 * controller se ejecute. Sin este manejador, esa excepción se
 * traduce en un 400 con un cuerpo genérico difícil de leer en
 * Postman. Aquí la transformamos en un JSON simple:
 *
 * {
 *   "mensaje": "Error de validación",
 *   "errores": {
 *       "razonSocial": "La razón social es obligatoria",
 *       "ruc": "El RUC debe tener 11 dígitos"
 *   }
 * }
 *
 * Se aplica a TODOS los @RestController del proyecto, no solo a los
 * del módulo de Inventario/Ventas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("mensaje", "Error de validación");
        cuerpo.put("errores", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpo);
    }

}