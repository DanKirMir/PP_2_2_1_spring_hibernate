package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");

      user1.setUserCar(new Car("Volvo", 40));
      user2.setUserCar(new Car("Kia",6));

      System.out.println("Добавление пользователей в таблицу\n");
      userService.add(user1);
      userService.add(user2);
      System.out.println("Вывод всех пользователей таблицы \"users\"\n");
      userService.listUsers().forEach(System.out::println);

      System.out.println("Вывод всех пользователей таблицы \"users\" по условию наличия переданного автомобиля\n");
      System.out.println(userService.getDrivers(123, "Urus"));



//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();


      context.close();
   }
}
