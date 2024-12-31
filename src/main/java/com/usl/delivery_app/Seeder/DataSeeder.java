package com.usl.delivery_app.Seeder;

import com.usl.delivery_app.data.Usersdata.Role;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.repository.UsersRepository;
import com.usl.delivery_app.service.userService.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private UserServiceImpl userService;
    public UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usersRepository.findByEmail("admin@example.com") == null) {
            Users admin = new Users();
            admin.setFirstName("Admin");
            admin.setLastName("Super Admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("Admin1234"));
            admin.setRole(Role.ADMIN);
            admin.setEmailVerified(true);
            admin.setTokenExpirationTime(null);
            usersRepository.save(admin);

        }
    }
}
