package tesis.playon.web.dao.impl.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * 
 * @author gmorales
 * 
 */
public class DBPasswordEncrypterBean extends JdbcDaoSupport {

    private String selectQuery = null;

    private String updateQuery = null;

    private PasswordEncoder passwordEncoder = null;

    public void encryptDBPassword() {
	getJdbcTemplate().query(getSelectQuery(), new RowCallbackHandler() {
	    @Override
	    public void processRow(ResultSet rs) throws SQLException {
		final String username = rs.getString("usuario");
		final String encryptedPassword = passwordEncoder.encodePassword(rs.getString("password"), null);
		getJdbcTemplate().update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement preparedStatement = con.prepareStatement(getUpdateQuery());
			preparedStatement.setString(1, encryptedPassword);
			preparedStatement.setString(2, username);
			return preparedStatement;
		    }
		});
	    }
	});
    }

    public PasswordEncoder getPasswordEncoder() {
	return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }

    public String getSelectQuery() {
	return selectQuery;
    }

    public void setSelectQuery(String selectQuery) {
	this.selectQuery = selectQuery;
    }

    public String getUpdateQuery() {
	return updateQuery;
    }

    public void setUpdateQuery(String updateQuery) {
	this.updateQuery = updateQuery;
    }

}