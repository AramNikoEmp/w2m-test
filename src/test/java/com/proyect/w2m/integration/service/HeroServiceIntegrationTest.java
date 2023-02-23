package com.proyect.w2m.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.proyect.w2m.model.entity.Hero;
import com.proyect.w2m.repository.HeroRepository;

@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class HeroServiceIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private HeroRepository heroRepo;

	@Test
	public void whenFindByName_thenReturnEmployee() {

		Hero hero = new Hero();
		hero.setId(1);
		hero.setAge("87");
		hero.setName("Deadpool");
		hero.setLocation("Spain");
		entityManager.persist(hero);
		entityManager.flush();

		Hero found = heroRepo.findByNameContaining(hero.getName());

		assertThat(found.getId()).isEqualTo(hero.getId());
	}

}
