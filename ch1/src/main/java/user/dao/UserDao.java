package user.dao;

import user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public void add(final User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        // DB 연결을 위한 Connection을 가져온다.
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:53306/springbook", "spring", "book");
        // SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        // 만들어진 Statement를 실행한다.
        ps.executeUpdate();
        // 작업 중에 생성된 리소스는 작업을 마친 후 반드시 닫아준다.
        ps.close();
        c.close();
    }

    public User get(final String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        // DB 연결을 위한 Connection을 가져온다.
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:53306/springbook", "spring", "book");
        // SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);
        // (조회) SQL 쿼리의 실행 결과를 ResultSet으로 받아서 User 오브젝트에 옮겨준다.
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        // 작업 중에 생성된 리소스는 작업을 마친 후 반드시 닫아준다.
        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao();

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
