package org.diginamic.demojwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application de DEMO utilisant : <br>
 * <ul>
 * <li>Une base de données H2 en mémoire pour le stockage des tables</li>
 * <li>Spring Security avec la génération d'un token JWT si l'utilisateur est
 * authentifié</li>
 * <li>Spring Hateoas pour faire la démonstration d'une API vraiment REST</li>
 * <li>Open API (ex swagger) pour la documentation de l'API accessible ici:
 * http://localhost:8080/swagger-ui/index.html</li>
 * <li>L'accès à la documentation de l'API est dans cet exemple non
 * sécurisé.</li>
 * </ul>
 * 
 * @author RichardBONNAMY
 *
 */
@SpringBootApplication
public class DemoJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJwtApplication.class, args);
	}

}
