package com.proyect.w2m.service;

import java.util.List;

import com.proyect.w2m.model.entity.Hero;

public interface IHeroService {
	
	/**
	 * Find all heroes
	 */
	List<Hero> getAllHeroes();
	
	/**
	 * Find hero by id
	 */
	Hero getHeroById(Integer id);
	
	/**
	 * Find hero by name (containing)
	 */
	Hero getHeroByNameContaining(String name);
	
	/**
	 * Save a hero
	 * @return 
	 */
	Hero saveHero(Hero hero);
	
	/**
	 * Delete hero by id
	 */
	void deleteHeroById(Integer id);

	/**
	 * Modify hero
	 */
	Hero modifyHero(Hero hero);
}
