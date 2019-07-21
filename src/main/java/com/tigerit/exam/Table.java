package com.tigerit.exam;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	
	private String tableName;
	
	private ArrayList<String> columnNames;
	
	private Integer columnCount;
	
	private Integer rowCount;
	
	private Integer arr[][];
	
	

	public Table(String tableName, Integer columnCount, Integer rowCount, ArrayList<String> columnNames,
			Integer[][] arr) {
		super();
		this.tableName = tableName;
		this.columnNames = columnNames;
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		this.arr = arr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public Integer getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Integer columnCount) {
		this.columnCount = columnCount;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer[][] getArr() {
		return arr;
	}

	public void setArr(Integer[][] arr) {
		this.arr = arr;
	}

	@Override
	public String toString() {
		String str="";
		for(int i=0;i<rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				str=str+" "+String.valueOf(arr[i][j]);
			}
		}
		return "Table [tableName=" + tableName + ", columnNames=" + columnNames + ", columnCount=" + columnCount
				+ ", rowCount=" + rowCount + ", arr=" + str + "]";
	}
	
	
	
	


	

}
