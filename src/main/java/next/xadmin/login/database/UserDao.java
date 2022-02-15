package next.xadmin.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.java.usermanagment.User;

public class UserDao {
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "JulianCal#1";
	
	private static final String INSERT_USERS_SQL = "INSERT INFO users" + " (name, email, country) VALUES "
			+ " (?, ?, ?);";
	
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =)";
    private static final String SELECT_ALL_USERS = "select ' from users";
    private static final String DELETE_USERS_SQL = "delete from where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    protected Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return connection;
    }
    
    public void insertUser(User user) throws SQLException {
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
    		preparedStatement.setString(1, user.getName());
    		preparedStatement.setString(2, user.getEmail());
    		preparedStatement.setString(3, user.getCountry());
    		preparedStatement.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public boolean UpdateUser(User user) throws SQLException {
    	boolean rowUpdated;
    	try (Connection connection = getConnection();
    			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
    		statement.setString(1, user.getName());
    		statement.setString(2, user.getEmail());
    		statement.setString(3, user.getCountry());
    		statement.setInt(4, user.getId());
    		
    		rowUpdated = statement.executeUpdate() > 0;
    	}
    	return rowUpdated;
    	}
public User selectUser(int id) {
	User user = null;
	try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
		preparedStatement.setInt(1, id);
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
	while (rs.next()) {
		String name = rs.getString("name");
		String email = rs.getString("email");
		String country = rs.getString("country");
		user = new User(id, name, email, country);
	}
} catch (SQLException e) {
	e.printStackTrace();
}
  return user; 
}
public List<User> selectAllUsers(int id) {
	List<User> users = new ArrayList<>();
	try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
		preparedStatement.setInt(1, id);
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
	while (rs.next()) {
		String name = rs.getString("name");
		String email = rs.getString("email");
		String country = rs.getString("country");
		users.add(new User(id, name, email, country));
	}
} catch (SQLException e) {
	e.printStackTrace();
}
  return users; 
}
  public boolean deleteUser(int id)	throws SQLException {
	  boolean rowDeleted;
	  try (Connection connection = getConnection();
			  PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
		  statement.setInt(1, id);
		  rowDeleted = statement.executeUpdate() > 0;
	  }
	  return rowDeleted;
}

public List<User> selectAllUsers() {
	// TODO Auto-generated method stub
	return null;
}

	
}


  

