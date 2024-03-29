package org.diginamic.demojwt.controllers;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exemple utilisant Spring Hateoas et qui permet de lister les routes
 * disponibles dans l'application.
 * 
 * @author RichardBONNAMY
 *
 */
@RestController
@RequestMapping("/root")
public class RootEntryPointController {

	@GetMapping
	@Secured({ "ADMIN", "USER" })
	public RepresentationModel<?> getApiRoot() {
		RepresentationModel<?> rootModel = new RepresentationModel<>();

		// Ajouter un lien vers la liste des livres
		rootModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExempleController.class).getData1())
				.withRel("Data 1"));
		rootModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExempleController.class).getData2())
				.withRel("Data 2"));

		// Ajouter d'autres liens vers différentes parties de l'API selon le besoin
		rootModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VilleController.class).findAll())
				.withRel("villes"));
		rootModel.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VilleController.class).findByCodeDept("{codeDept}"))
						.withRel("villes par code dept"));

		// Vous pouvez continuer à ajouter d'autres liens ici

		return rootModel;
	}
}