package se.lexicon.todo_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.lexicon.todo_app.entity.*;
import se.lexicon.todo_app.repository.AttachmentRepository;
import se.lexicon.todo_app.repository.PersonRepository;
import se.lexicon.todo_app.repository.TodoRepository;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner run(PersonRepository personRepo, TodoRepository todoRepo, AttachmentRepository attachmentRepo) {
        return args -> {
            if (personRepo.count() == 0 && todoRepo.count() == 0 && attachmentRepo.count() == 0) {
                // 👨‍💻 Create Developers
                System.out.println("Creating initial users...");

                Person adminPerson = new Person("Admin", "admin@test.se");
                User admin = new User("admin", passwordEncoder.encode("password"));
                admin.addRole(Role.USER);
                admin.addRole(Role.ADMIN);
                adminPerson.setUser(admin);
                Person savedAdmin = personRepo.save(adminPerson);
                System.out.println("Admin user created with username: " + savedAdmin.getUser().getUsername());

                Person person1 = new Person("Mehrdad Javan", "mehrdad@test.se");
                User user1 = new User("user1", passwordEncoder.encode("password"));
                user1.addRole(Role.USER);
                person1.setUser(user1);
                Person savedUser1 = personRepo.save(person1);
                System.out.println("User1 created with username: " + savedUser1.getUser().getUsername());

                Person person2 = new Person("Simon Elbrink", "simon@test.se");
                User user2 = new User("user2", passwordEncoder.encode("password"));
                user2.addRole(Role.USER);
                person2.setUser(user2);
                Person savedUser2 = personRepo.save(person2);
                System.out.println("User2 created with username: " + savedUser2.getUser().getUsername());

                Person person3 = new Person("Mattias Hellman", "mattias@test.se");
                User user3 = new User("user3", passwordEncoder.encode("password"));
                user3.addRole(Role.USER);
                user3.addRole(Role.ADMIN);
                person3.setUser(user3);
                Person savedUser3 = personRepo.save(person3);
                System.out.println("User3 created with username: " + savedUser3.getUser().getUsername());

                // ✅ Create Todos
                System.out.println("Creating initial todos...");

                Todo todo1 = new Todo("Complete Project Documentation",
                        "Write comprehensive documentation for the new features",
                        false, LocalDateTime.now().minusDays(8),
                        savedUser1);
                Todo savedTodo1 = todoRepo.save(todo1);
                System.out.println("savedTodo1 created with title: " + savedTodo1.getTitle());

                Todo todo2 = new Todo("Review Code Changes",
                        "Review and approve pending pull requests",
                        true, LocalDateTime.now().plusDays(3),
                        savedUser2);
                Todo savedTodo2 = todoRepo.save(todo2);
                System.out.println("savedTodo2 created with title: " + savedTodo2.getTitle());

                Todo todo3 = new Todo("Deploy Application Updates",
                        "Deploy the latest version to production",
                        false, LocalDateTime.now().plusDays(2));
                Todo savedTodo3 = todoRepo.save(todo3);
                System.out.println("savedTodo3 created with title: " + savedTodo3.getTitle());

                Todo todo4 = new Todo("Post-deployment Support",
                        " Fix bugs and plan feature enhancements",
                        false);
                todo4.setPerson(savedUser3);
                Todo savedTodo4 = todoRepo.save(todo4);
                System.out.println("savedTodo3 created with title: " + savedTodo4.getTitle());
            }
        };
    }
}