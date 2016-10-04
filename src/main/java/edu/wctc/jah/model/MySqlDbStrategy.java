package edu.wctc.jah.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
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
    public final Map<String,Object> findRecordByKey(String table, String field, Object key) throws SQLException {
        PreparedStatement pstmt = null;
	ResultSet rs = null;
        Object ob = null;
        Map<String,Object> record = new LinkedHashMap<>();
        
        String sql = "SELECT * FROM " + table + " WHERE " + field + " = ?";
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setObject(1, key);
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
    
    public PreparedStatement buildDeleteStatement(String table, String field, Object value) throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE " + field + " =  ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setObject(1, value);
        return ps;
    }
    
    public PreparedStatement buildInsertStatement(String table, List<String> colNames, List<Object> colValues) throws SQLException {
        String sql = "INSERT INTO " + table + " ";
        StringJoiner sjCols = new StringJoiner(", ", "(", ")");
        for (String cols : colNames) {
            sjCols.add(cols);
        }
        sql += sjCols.toString();
        sql += " VALUES ";
        StringJoiner sjValues = new StringJoiner(", ", "(", ")");
        for (Object o : colValues) {
            sjValues.add("?");
        }
        sql += sjValues.toString();
        //sql += ";";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps;
    }
    
    @Override
    public final void deleteRecordByKey(String table, String field, Object value) throws SQLException {
        PreparedStatement ps = buildDeleteStatement(table, field, value);
        ps.executeUpdate();
    }
    
    @Override
    public final void insertRecord(String table, List<String> colNames, List<Object> colValues) throws SQLException {
        int i;
        PreparedStatement ps = buildInsertStatement(table, colNames, colValues);
        
        for (i = 0; i < colValues.size(); i++) {
            ps.setObject(i+1, colValues.get(i));
        }
        
        ps.executeUpdate();
    }
    
    public PreparedStatement buildUpdateStatement(String table, String primaryKey, List<String> colNames) throws SQLException {
        PreparedStatement ps = null;
        
        String sql = "UPDATE " + table + " SET ";
        StringJoiner sjCols = new StringJoiner(", ");
        for (int i = 0; i < colNames.size(); i++) {
            sjCols.add(colNames.get(i) + " = ?");
        }
        sql += sjCols.toString();
        sql += " WHERE " + primaryKey + " = ?";
        return conn.prepareStatement(sql);
    }
    
    @Override
    public final void updateRecord(String table, String primaryKey, Object pkValue, List<String> colnames, List<Object> colVals) throws SQLException {
        PreparedStatement ps = buildUpdateStatement(table, primaryKey, colnames);
        int i;
        
        for (i = 0; i < colVals.size(); i++) {
            ps.setObject(i+1, colVals.get(i));
        }
        
        ps.setObject(i+1, pkValue);
        
        ps.executeUpdate();
        
    }
   
    
    public static void main(String[] args) throws Exception {
        MySqlDbStrategy db = new MySqlDbStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        //List<Map<String, Object>> records = db.findAllRecords("author", 10);
        //Map<String, Object> a = db.findRecordByKey("author", "author_id", "1");
        List<String> colnames = new ArrayList<>();
        List<Object> colvals = new ArrayList<>();
        colnames.add("author_name");
        colnames.add("date_added");
        colvals.add("Joel Hedding");
        colvals.add(new Date());
        db.updateRecord("author", "author_id", 1, colnames, colvals);
        //System.out.println("Complete");
        //System.out.println(records);
        db.closeConnection();

    }

//  DELETE FROM authors WHERE author_id = 2;
//  DELETE FROM (TABLE) WHERE (PRIMARY KEY) = (VAR);
//  Returns integer;
    
}