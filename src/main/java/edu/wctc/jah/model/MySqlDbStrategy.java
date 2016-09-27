package edu.wctc.jah.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhedding
 */
public class MySqlDbStrategy implements dbStrategy {
    
    private Connection conn;
    
    @Override
    public final void openConnection(String driverClass, String url,
            String userName, String passWord) throws ClassNotFoundException, SQLException {
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, passWord);  
    }
    
    @Override
    public final void closeConnection() throws SQLException {
        conn.close();
    }
    
    @Override
    public final List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        List<Map<String, Object>> records = new ArrayList<>();
        int colCount = rsmd.getColumnCount();
        
        while(rs.next()) {
            Map<String,Object> record = new LinkedHashMap<>();
            for (int i = 1; i < colCount+1; i++) {
                String colName = rsmd.getColumnName(i);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }
    
    @Override
    public final Map<String,Object> findRecordByKey(String table, String field, String key) throws SQLException {
        PreparedStatement pstmt = null;
	ResultSet rs = null;
        Object ob = null;
        Map<String,Object> record = new LinkedHashMap<>();
        
        String sql = "SELECT * FROM " + table + " WHERE " + field + " = ?";
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, Integer.parseInt(key));
	rs = pstmt.executeQuery();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        while(rs.next()) {
            for (int i = 1; i < colCount+1; i++) {
                String colName = rsmd.getColumnName(i);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
        }
        return record;
        
    }
    
    public static void main(String[] args) throws Exception {
        MySqlDbStrategy db = new MySqlDbStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        //List<Map<String, Object>> records = db.findAllRecords("author", 10);
        Map<String, Object> a = db.findRecordByKey("author", "author_id", "1");
        
        System.out.println(a);
        //System.out.println(records);
        db.closeConnection();

    }

//  DELETE FROM authors WHERE author_id = 2;
//  DELETE FROM (TABLE) WHERE (PRIMARY KEY) = (VAR);
//  Returns integer;
    
}