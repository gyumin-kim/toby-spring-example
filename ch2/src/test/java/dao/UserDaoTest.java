package dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    @Test
    void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertEquals(0, dao.getCount());

        User user = new User();
        user.setId("gyumin-kim");
        user.setName("김규민");
        user.setPassword("1234");

        dao.add(user);
        assertEquals(1, dao.getCount());

        User user2 = dao.get(user.getId());

        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getPassword(), user2.getPassword());
    }
}
