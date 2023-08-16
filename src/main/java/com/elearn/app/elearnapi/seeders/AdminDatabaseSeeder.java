package com.elearn.app.elearnapi.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.utilities.PrintUtilities;

@Component
public class AdminDatabaseSeeder {
    @Autowired
    private UserService userService;

    public void seed() {
        PrintUtilities.println(String.format("### Seeding Admin User"));
        String name = "Admin", email = "Admin@admin.com", password = "123456789";
        User user = this.userService.findByEmail(email);
        if (user == null) {
            this.userService.createAdminUser(name, email, password);
            PrintUtilities.println(String.format("###### Admin User with email %s and password %s created successfully",
                    email, password));
        } else {
            PrintUtilities.println(
                    String.format("###### Skip create admin user with email %s because it's already created ", email));

        }
    }
}
