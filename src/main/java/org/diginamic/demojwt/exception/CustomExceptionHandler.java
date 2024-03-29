package org.diginamic.demojwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Intercepte les erreurs de type {@link FunctionalException} et
 * {@link UnknownEntityException} afin de générer une réponse HTTP adaptée à
 * chaque exception.
 * 
 * @author RichardBONNAMY
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {

	/**
	 * Dans le cas d'une exception métier on retourne un code HTTP 422
	 * (unprocessable entity: entité non traitable car données manquantes par ex).
	 * La réponse HTTP contient également le message d'erreur.
	 * 
	 * @param ex exception métier
	 * @return ResponseEntity
	 */
	@ExceptionHandler({ FunctionalException.class })
	protected ResponseEntity<String> traiterErreurs(FunctionalException ex) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}

	/**
	 * Dans le cas d'une ressource non trouvée (ville dont l'id est non trouvé) on
	 * retourne un code HTTP 404 (unprocessable entity: entité non traitable car
	 * données manquantes par ex).<br>
	 * La réponse HTTP contient également le message d'erreur.
	 * 
	 * @param ex exception métier
	 * @return ResponseEntity
	 */
	@ExceptionHandler({ UnknownEntityException.class })
	protected ResponseEntity<String> traiterErreurs(UnknownEntityException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
