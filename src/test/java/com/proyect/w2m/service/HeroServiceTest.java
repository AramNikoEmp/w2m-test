package com.proyect.w2m.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.proyect.w2m.exception.NotFoundException;
import com.proyect.w2m.model.entity.Hero;
import com.proyect.w2m.repository.HeroRepository;
import com.proyect.w2m.service.impl.HeroServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
class HeroServiceTest {

	@InjectMocks
	private HeroServiceImpl heroServ;
	
	@Mock
	private HeroRepository heroRepo;
	
	private Hero hero;
	
	@BeforeEach
    public void init() {
		final Integer ID_USER = 1;
		final String NAME = "PRB";
		final String AGE = "PRB";
		final String LOCATION = "COZY";
		hero = Hero.builder().name(NAME).id(ID_USER).age(AGE).location(LOCATION).build();
    }
	
	@Test
	public void shouldReturnHeroWhenRetrieveHero() {		
		
		/* When */
		when(heroRepo.findById(anyInt())).thenReturn(Optional.of(hero));

		/* Calling service */
		Hero out = heroServ.getHeroById(anyInt());
		
		/* Verify */
		assertEquals(hero, out);
		verify(heroRepo, times(1)).findById(anyInt());
		
	}
	
	@Test
	public void shouldThrowExceptionWhenRetrieveNoHero() {		
		
		Assertions.assertThrows(NotFoundException.class, () -> {
		
		/* When */
		when(heroRepo.findById(anyInt())).thenReturn(Optional.empty());
	
		/* Calling service */
		heroServ.getHeroById(anyInt());
		});
		
	}
}
