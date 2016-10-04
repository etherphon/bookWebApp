/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author etherdesign
 */
public interface dbStrategy {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String passWord) throws ClassNotFoundException, SQLException;
    
    Map<String,Object> findRecordByKey(String table, String field, Object key) throws SQLException;
    
    void deleteRecordByKey(String table, String field, Object value) throws SQLException;
    
    void insertRecord(String table, List<String> colNames, List<Object> colValues) throws SQLException;
    
    void updateRecord(String table, String primaryKey, Object pkValue, List<String> colnames, List<Object> colVals) throws SQLException;
}
