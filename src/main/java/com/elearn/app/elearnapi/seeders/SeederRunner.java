package com.elearn.app.elearnapi.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SeederRunner implements ApplicationRunner {

    @Autowired
    private AdminDatabaseSeeder adminDatabaseSeeder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        adminDatabaseSeeder.seed();
    }
}
