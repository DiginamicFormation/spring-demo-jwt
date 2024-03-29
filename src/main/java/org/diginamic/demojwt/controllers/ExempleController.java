package org.diginamic.demojwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exemple de contrôleur avec une sécurisation des méthodes via
 * l'annotation @Secured contenant les profils autorisés
 * 
 * @author RichardBONNAMY
 *
 */
@RestController
@RequestMapping("/exemples")
public class ExempleController {

	/** Cette méthode n'est accessible qu'au profil USER
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/user")
	@Secured("USER")
	public ResponseEntity<String> getData1() {
		return ResponseEntity.ok("Cette URL n'est accessible qu'aux profils USER");
	}

	/** Cette méthode n'est accessible qu'au profil ADMIN
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/admin")
	@Secured("ADMIN")
	public ResponseEntity<String> getData2() {
		return ResponseEntity.ok("Cette URL n'est accessible qu'aux profils ADMIN");
	}

	/** Cette méthode n'est accessible qu'aux profils ADMIN et USER
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/all")
	@Secured({ "ADMIN", "USER" })
	public ResponseEntity<String> getData3() {
		return ResponseEntity.ok("Cette URL n'est accessible aux profils ADMIN et USER");
	}
}
