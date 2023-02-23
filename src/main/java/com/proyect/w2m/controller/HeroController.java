package com.proyect.w2m.controller;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.w2m.model.entity.Hero;
import com.proyect.w2m.service.impl.HeroServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/heroes")	
public class HeroController {

	private final HeroServiceImpl heroServ;
	
	public HeroController(HeroServiceImpl heroServiceImpl) {
		this.heroServ = heroServiceImpl;
	}
	
	@GetMapping
	@ApiOperation(value = "Get hero", notes = "Returns all heroes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exists one hero at least"),
            @ApiResponse(code = 204, message = "Doesn't exist any hero")
    })
	public ResponseEntity<List<Hero>> retrieveAllHeroes(){
		List<Hero> heroes = heroServ.getAllHeroes();
		
		ResponseEntity<List<Hero>> response = null;
		if(isEmpty(heroes)) {
			response = ResponseEntity.noContent().build();
		} else {
			response = ResponseEntity.ok().body(heroes);
		}
	
		return response;	
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get one hero", notes = "Returns one hero")
    @ApiResponses({
    	@ApiResponse(code = 200, message = "Exists this hero"),
    	@ApiResponse(code = 404, message = "Doesn't exist this hero")
    })
	public ResponseEntity<Hero> retrieveHero(@PathVariable Integer id) {
		Hero response = heroServ.getHeroById(id);
		return ResponseEntity.ok().body(response);	
	}
	
	@PostMapping
	@ApiOperation(value = "Create hero", notes = "Create a hero")
    @ApiResponses({
    	@ApiResponse(code = 201, message = "Successful creation of a hero"),
    	@ApiResponse(code = 409, message = "Conflict in hero creation")
    })
	public ResponseEntity<Hero> createHero(@Valid @RequestBody Hero hero) {
		Hero created = heroServ.saveHero(hero);
		return ResponseEntity.ok().body(created);

	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Modify hero", notes = "Modify a hero")
    @ApiResponses({
    	@ApiResponse(code = 200, message = "Successful modification of the hero"),
    	@ApiResponse(code = 404, message = "Doesn't exist this hero")
    })
	public ResponseEntity<Hero> modifyHero(@PathVariable Integer id, @Valid @RequestBody Hero hero) {
		hero.setId(id);
		Hero modified = heroServ.modifyHero(hero);
		
		return ResponseEntity.ok().body(modified);	
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete hero", notes = "Delete a hero")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successful delete of a hero"),
            @ApiResponse(code = 404, message = "Doesn't exist this hero")
    })
	public ResponseEntity<Void> deleteHero(@PathVariable Integer id){
		heroServ.deleteHeroById(id);
		
		return  ResponseEntity.noContent().build();
	}
}
