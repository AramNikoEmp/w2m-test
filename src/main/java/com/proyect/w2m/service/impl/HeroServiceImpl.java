package com.proyect.w2m.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyect.w2m.exception.NotFoundException;
import com.proyect.w2m.model.entity.Hero;
import com.proyect.w2m.repository.HeroRepository;
import com.proyect.w2m.service.IHeroService;
import com.proyect.w2m.utils.constants.Constants;


@Service
@CacheConfig(cacheNames={"heroes"})
@Transactional
public class HeroServiceImpl implements IHeroService {

	@Autowired
	HeroRepository heroRepo;
	
	@Cacheable("heroes")
	@Override
	public List<Hero> getAllHeroes() {
		return heroRepo.findAll().stream().collect(Collectors.toList());
	}

	@Cacheable(key = "#id")
	@Override
	public Hero getHeroById(Integer id) {
		return heroRepo.findById(id).orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_HERO));
	}
	
	@Cacheable(key = "#name")
	@Override
	public Hero getHeroByNameContaining(String name) {
		return heroRepo.findByNameContaining(name);
	}

	@CachePut(key = "#hero.id")
	@Override
	public Hero saveHero(Hero hero) {
		return heroRepo.save(hero);
		
	}

	@CacheEvict(key="#id") 
	@Override
	public void deleteHeroById(Integer id) {
		getHeroById(id);
		
		heroRepo.deleteById(id);
	}
	
	@CachePut(key = "#hero.id")
	@Override
	public Hero modifyHero(Hero hero) {
		Hero heroDB = getHeroById(hero.getId());
		if(Objects.nonNull(heroDB)) {
			return heroRepo.save(hero);
		} else {
			throw new NotFoundException(Constants.NOT_FOUND_HERO);
		}		
	}



	
	
}
