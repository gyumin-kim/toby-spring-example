package user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("gyumin-kim");
        user.setName("김규민");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}