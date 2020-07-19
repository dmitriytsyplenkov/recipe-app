package com.dts.recipeapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-default.properties")
class RecipeAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
