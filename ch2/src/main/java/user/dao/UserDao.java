package user.dao;

import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;

    public void setConnectionMaker(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Connection c = dataSource.getConnection();
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

    public User get(final String id) throws SQLException {
        // DB 연결을 위한 Connection을 가져온다.
        Connection c = dataSource.getConnection();
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
}
