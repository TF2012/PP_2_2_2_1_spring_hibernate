package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Lada", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Renault", 2)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Suzuki", 10)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        List<User> usersByCar = userService.getUserByCar("Ferrari", 12);
        for (User user : usersByCar) {
            System.out.println(user);
        }

        context.close();
    }
}
