package com.learn.spring.spring_la_mia_pizzeria_relazioni.Eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Intercetta l'errore di conversione del tipo (es. testo invece di numero)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Forza lo status HTTP a 404
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {

        // Ritorna il percorso del tuo template HTML
        model.addAttribute("messageError", "inserire un numero");
        return "error/404";
    }

    // CASO 2: L'ID è un numero ma non esiste nel DB (es. /pizzas/999)
    @ExceptionHandler(java.util.NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(Model model) {
        model.addAttribute("messageError", "Non è stato trovato nessuna pizza con questo id");
        return "error/404";
    }

    @ExceptionHandler(Exception.class) // Cattura TUTTO il resto
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Errore 500
    public String handleGenericException(Exception ex, Model model) {
        // Logga l'errore nella console per te sviluppatore
        System.out.println("Errore imprevisto: " + ex.getMessage());

        model.addAttribute("messageError", "Si è verificato un errore interno. Riprova più tardi.");
        return "error/500"; // Mostra una pagina templates/error/500.html
    }
}
