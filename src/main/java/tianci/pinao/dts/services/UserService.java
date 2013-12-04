package tianci.pinao.dts.services;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;




import tianci.pinao.dts.models.User;
import tianci.pinao.dts.util.MD5Util;

@Component
public class UserService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	public List<User> check(User user) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM USER where status=1");
		if (user.getId() != null) {
			sql.append(" and id=" + user.getId() + "");
		}
		List<User> list = this.jdbcTemplate.query(sql.toString(),
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setCreated_at(rs.getString("created_at"));
						return user;
					}
				});
		return list;
	}

	// 增加
	public int add(User user) {
		String password =MD5Util.MD5( user.getPassword());
		String sql = "insert into User (name, password,status,created_at) values (?, ?,1,sysdate())";
		return this.jdbcTemplate
				.update(sql, user.getName(), password);
	}

	// 根据name查询
	public int login(User user,HttpServletRequest request) {
		String password =MD5Util.MD5( user.getPassword());
		String sql = "SELECT * FROM USER where status=1 and name='"
				+ user.getName() + "' and password = '" + password
				+ "'";
		List<User> list = this.jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setCreated_at(rs.getString("created_at"));
				return user;
			}
		});
		for (int i = 0; i <list.size(); i++) {
			String name=list.get(i).getName();
			if(name.equals(user.getName())){
				HttpSession session=request.getSession();
				session.setAttribute("sessionname", user.getName());
			}
		}
		int result;
		if (list.size() > 0) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

	public int delete(User user) {
		String sql = "update User set status=0 where id=" + user.getId() + "";
		return jdbcTemplate.update(sql);
	}

	public int updates(User user) {
		String sql = "update User set name='" + user.getName()
				+ "', password='" + user.getPassword() + "'where id="
				+ user.getId() + " and status=1";
		int res = jdbcTemplate.update(sql);
		return res;
	}

	// 批量删除
	public int deleteall(int a) {
		String sql = "update User set status=0 where id=" + a + "";
		int re = jdbcTemplate.update(sql);
		return re;
	}

	//根据id查询全部选项
	public List<User> findidall(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from User where status=1");
		if (id != 0) {
			sb.append(" and id=" + id + "");
		}
		List<User> list = this.jdbcTemplate.query(sb.toString(),
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setCreated_at(rs.getString("created_at"));
						return user;
					}
				});
		return list;
	}

}
