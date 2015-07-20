package io.ddf.jdbc;


import io.ddf.DDF;
import io.ddf.DDFManager;
import io.ddf.jdbc.JDBCDDFManager.TableSchema;
import io.ddf.jdbc.JDBCDDFManager.ColumnSchema;
import io.ddf.content.Schema;
import io.ddf.exception.DDFException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by freeman on 7/17/15.
 */
public class JDBCDDF extends DDF {

  public JDBCDDF(DDFManager manager, String namespace, String name, Schema schema) throws DDFException {
    throw new DDFException("Unsupported constructor");
  }

  public JDBCDDF(DDFManager manager) throws DDFException {
    throw new DDFException("Unsupported constructor");
  }

  public JDBCDDF() throws DDFException {
    throw new DDFException("Unsupported constructor");
  }

  /**
   *  Create a DDF from a table
   *
   * @param manager
   * @param namespace
   * @param name: DDF name
   * @param tableName: JDBC table name
   */
  public JDBCDDF(JDBCDDFManager manager, String namespace, String name, String tableName)
      throws DDFException, SQLException {
    //build DDF schema from table schema
    TableSchema tableSchema = manager.getTableSchema(tableName);
    Schema ddfSchema = buildDDFSchema(tableSchema);
  }

  private Schema buildDDFSchema(TableSchema tableSchema) throws DDFException {
    List<Schema.Column> cols = new ArrayList<>();
    Iterator<ColumnSchema> schemaIter = tableSchema.iterator();

    while(schemaIter.hasNext()){
      ColumnSchema jdbcColSchema = schemaIter.next();
      Schema.ColumnType colType = jdbcColSchema.getDDFType(); //TODO: verify if throwing exception makes sense
      String colName = jdbcColSchema.getName();
      cols.add(new Schema.Column(colName, colType));
    }
    return new Schema(null, cols);
  }
}