package com.petadoption.pet_adoption_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.Mockito;
import com.petadoption.pet_adoption_api.services.PetService;
import com.petadoption.pet_adoption_api.repositories.PetRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PetService.class, PetAdoptionApiApplicationTests.TestConfig.class})
class PetAdoptionApiApplicationTests {

	@Autowired
	private PetService petService;

	@Configuration
	static class TestConfig {
		@Bean
		PetRepository petRepository() {
			return Mockito.mock(PetRepository.class);
		}
	}

	@Test
	void contextLoads() {
		assertNotNull(petService);
	}

}
