package com.clean_spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class CleanSpringApplicationTest {

    @Test
    void run() {
        MockedStatic<SpringApplication> mocked =  Mockito.mockStatic(SpringApplication.class);

        CleanSpringApplication.main(new String[0]);

        mocked.verify(() -> {
            SpringApplication.run(CleanSpringApplication.class, new String[0]);
        });
    }
}