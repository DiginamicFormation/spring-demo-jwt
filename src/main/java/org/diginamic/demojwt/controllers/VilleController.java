package org.diginamic.demojwt.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.diginamic.demojwt.beans.Ville;
import org.diginamic.demojwt.dtos.VilleDto;
import org.diginamic.demojwt.exception.UnknownEntityException;
import org.diginamic.demojwt.repos.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exemple classique de contrôleur avec différents types de méthodes.<br>
 * On voit notamment l'utilisation de Spring Hateoas pour générer de vraies
 * réponses RESTful.
 * 
 * @author RichardBONNAMY
 *
 */
@RestController
@RequestMapping("/villes")
public class VilleController {

	/** villeRepository */
	@Autowired
	private VilleRepository villeRepository;

	/**
	 * Retourne toutes les villes
	 * 
	 * @return
	 */
	@GetMapping("/all")
	@Secured("ADMIN")
	public Iterable<Ville> findAll() {
		return villeRepository.findAll();
	}

	/**
	 * Retourne une ville de manière pleinement REST avec l'aide de Spring Hateoas.
	 * 
	 * @param id identifiant de la ville
	 * @return {@link EntityModel}
	 * @throws UnknownEntityException si la ville n'existe pas.
	 */
	@GetMapping("/{id}")
	@Secured("ADMIN")
	public EntityModel<VilleDto> findById(@PathVariable Integer id) throws UnknownEntityException {
		Optional<Ville> opt = villeRepository.findById(id);
		if (opt.isPresent()) {
			VilleDto dto = new VilleDto(opt.get());
			return EntityModel.of(dto, linkTo(methodOn(VilleController.class).findById(id)).withSelfRel(),
					linkTo(methodOn(VilleController.class).findAll()).withRel("villes"));
		}
		throw new UnknownEntityException("La ville d'identifiant " + id + " n'existe pas");
	}

	/**
	 * Point d'entrée pour une requête paginée sur les villes. Cette méthode
	 * exploite une possibilité de pagnation offerte par JpaRepository.<br>
	 * L'intérêt est que c'est le client qui fixe le numéro de page et le nb de
	 * villes retournées.
	 * 
	 * @param page numéro de page
	 * @param size nombre d'items dans a page
	 * @return {@link Page}
	 */
	@GetMapping("/page")
	@Secured("ADMIN")
	public Page<VilleDto> getVilles(@RequestParam int page, @RequestParam int size) {
		Pageable pagination = PageRequest.of(page, size);
		return villeRepository.findAll(pagination).map(VilleDto::new);
	}

	/**
	 * Retourne un ensemble itérable de villes pour un code département donné
	 * 
	 * @param codeDept code département
	 * @return {@link Iterable}
	 */
	@GetMapping("/all/{codeDept}")
	@Secured("ADMIN")
	public Iterable<Ville> findByCodeDept(@PathVariable String codeDept) {
		return villeRepository.findTop10ByDepartementCodeOrderByPopulationDesc(codeDept);
	}
}
