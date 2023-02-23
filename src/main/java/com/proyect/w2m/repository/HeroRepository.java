package com.proyect.w2m.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.w2m.model.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

	Hero findByNameContaining(String name);
}