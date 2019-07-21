package com.tigerit.exam;


import static com.tigerit.exam.IO.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
        // your application entry point

        // sample input process
        //String string = readLine();

        //Integer integer = readLineAsInteger();
        

        // sample output process
        //printLine(string);
        //printLine(integer);
    	String tbName;
    	Integer column;
    	Integer row;
    	Integer testCase = readLineAsInteger();
    	//table information input
    	for(Integer tc=1;tc<=testCase;tc++)
    	{
    		printLine("Test: "+tc);
    		Integer tableCount = readLineAsInteger();
    		ArrayList<Table> tables= new ArrayList<Table>();
    		for(Integer tb=1;tb<=tableCount;tb++)
    		{
    			tbName= readLine();
    			String clmAndrow= readLine();
    			String cAndr[]= clmAndrow.split(" ");
    			
    			column= Integer.valueOf(cAndr[0]);
    			row= Integer.valueOf(cAndr[1]);
    			
    			ArrayList<String> clmNames = new ArrayList<String>();
    			
    			String temp; 
    			String dummy[];
    			temp=readLine(); //all column names together
    			dummy=temp.split(" ");
    			for(Integer i=0;i<dummy.length;i++)
    			{
    				clmNames.add(dummy[i]); //column names
    			}
    			
    			Integer temp_arr[][]=new Integer[row][column];
    			String oneLine;
    			String input_together[];
    			for(Integer i=0;i<row;i++)
    			{
    				oneLine=readLine();
    				input_together=oneLine.split(" ");
    				
    				for(Integer j=0;j<column;j++)
    				{
    					temp_arr[i][j]= Integer.valueOf(input_together[j]);
    				}
    			}
    			
    			Table myTable= new Table(tbName,column,row,clmNames,temp_arr);
    			tables.add(myTable);
    			//printLine(myTable);
    			
    			
    			
    		}  //table input done
    		
    		
			Integer query_count=readLineAsInteger();
			
			//this is where the main thing happens
			String one,two,three,four,blank_line;
			String first_table,second_table,first_column,second_column;//the names
			String helper1[],helper2[],helper3[],helper4[]; //splitted results
			String aa[],bb[];
			for(Integer i=1;i<=query_count;i++)
			{
				//printLine("query :"+i);
				one=readLine();
				two=readLine();
				three=readLine();
				four=readLine();
				
				helper2=two.split(" ");
				helper3=three.split(" ");
				
				first_table=helper2[1]; //name
				second_table=helper3[1]; //name
				
				//printLine("tables: "+first_table+" and "+second_table); // for debug
				
				helper4=four.split(" ");				
				aa=helper4[1].split("\\.");
				bb=helper4[3].split("\\.");
		
				first_column=aa[1];
				second_column=bb[1];
				
				//printLine("columns: "+first_column+" and "+second_column); // for debug 
				Table first=null,second=null;
				for(Integer itr=0;itr<tables.size();itr++)
				{
					//printLine(tables.get(itr).getTableName());
					if(tables.get(itr).getTableName().equals(first_table))
					{
						first=tables.get(itr);
					}
					
					if(tables.get(itr).getTableName().equals(second_table))
					{
						second=tables.get(itr);
					}
					
				}
				
			//	printLine(first);
			//	printLine(second);
				ArrayList<String> clmnames1= first.getColumnNames();
				ArrayList<String> clmnames2= second.getColumnNames();
				
				Integer index1=-1,index2=-1;
				for(Integer itr=0;itr<clmnames1.size();itr++)
				{	
					if(clmnames1.get(itr).equals(first_column))
					{
						index1=itr; // column of the first table to compare
					}
					
				}
				
				for(Integer itr=0;itr<clmnames2.size();itr++)
				{	
					if(clmnames2.get(itr).equals(second_column))
					{
						index2=itr; // column of the first table to compare
					}
					
				}
				
				//printLine(index1+" compared with "+index2); //for debug
				
				
				//till this point the table and the column is selected successfully 
				String query_type[]=one.split(" ");
				
				if(query_type[1].equals("*"))
				{
					Integer arr1[][]=first.getArr();
					Integer arr2[][]=second.getArr();
					ArrayList<String> clm_nm1=first.getColumnNames();
					ArrayList<String> clm_nm2=second.getColumnNames();
					String header="";
					for(Integer ee=0;ee<first.getColumnCount();ee++)
					{
						header=header+clm_nm1.get(ee)+" ";
					}
					for(Integer ee=0;ee<second.getColumnCount();ee++)
					{
						header=header+clm_nm2.get(ee)+" ";
					}
					printLine(header);
					
					for(Integer ff=0;ff<first.getRowCount();ff++)
					{
						for(Integer ss=0;ss<second.getRowCount();ss++)
						{
							if(arr1[ff][index1]==arr2[ss][index2]) //important line
							{
								String res="";
								for(Integer tt=0;tt<first.getColumnCount();tt++)
								{
									res=res+String.valueOf(arr1[ff][tt])+" ";
								}
								for(Integer yy=0;yy<first.getColumnCount();yy++)
								{
									res=res+String.valueOf(arr2[ss][yy])+" ";
								}
								
								printLine(res);
								
							}
						}
					}
				}
				else //the most sensitive portion happens here and if things go wrong GG 
				{
					String strr = one.replaceAll(",", "");
					//printLine(strr);
					String required_columns[]=strr.split(" "); //it has SELECT in it
					ArrayList<String> targets=new ArrayList<String>();
					
					for(Integer w=1;w<required_columns.length;w++) //must  start from 1 to avoid SELECT
					{
						targets.add(required_columns[w]);  //adding everything except select
					}
					
				/*	for(Integer w=0;w<targets.size();w++) //must comment out this loop 
					{
						printLine(targets.get(w));  //debugging purpose
					}  */
					
					ArrayList<String> which_table=new ArrayList<String>();
					ArrayList<Integer> which_column= new ArrayList<Integer>();
					String header="";
					for(Integer w=0;w<targets.size();w++)
					{
						//if()
						//targets.get(w);  //adding everything except select
						String assume[]=targets.get(w).split("\\.");
						//printLine(assume[0]+" "+assume[1]);
						if(assume[0].equals(helper2[2])) 
						{
							//printLine(assume[0]+" "+helper2[2]);
							
							which_table.add("first"); //which table to print from
							//Integer arr1[][]=first.getArr();
							ArrayList<String> cn1= first.getColumnNames();
							for(Integer uu=0;uu<cn1.size();uu++)
							{
								if(assume[1].equals(cn1.get(uu)))
								{	header=header+assume[1]+" ";
									which_column.add(uu); //which colum to print 
								}
							}
							
							
						}
						else if(assume[0].equals(helper3[2]))
						{
							//printLine(assume[0]+" "+helper3[2]);
							header=header+assume[1]+" ";
							which_table.add("second"); //which table to print from
						//	Integer arr2[][]=second.getArr();
							ArrayList<String> cn2= second.getColumnNames();
							for(Integer uuu=0;uuu<cn2.size();uuu++)
							{
								if(assume[1].equals(cn2.get(uuu)))
								{
									which_column.add(uuu); //which colum to print 
								}
							}
						}
					} 
					
					/*for(Integer ok=0;ok<which_table.size();ok++)
					{
						printLine(which_table.get(ok)+" "+which_column.get(ok)); //custom columns checking
					} */
					Integer arr1[][]=first.getArr();
					Integer arr2[][]=second.getArr();
					printLine(header);
					for(Integer ff=0;ff<first.getRowCount();ff++)
					{
						for(Integer ss=0;ss<second.getRowCount();ss++)
						{
							if(arr1[ff][index1]==arr2[ss][index2]) //important line
							{
								String res="";
								for(Integer ok=0;ok<which_table.size();ok++)
								{
									//printLine(which_table.get(ok)+" "+which_column.get(ok)); //custom columns checking
									if(which_table.get(ok).equals("first"))
									{
										res=res+String.valueOf(arr1[ff][which_column.get(ok)])+" ";
									}
									else if(which_table.get(ok).equals("second"))
									{
										res=res+String.valueOf(arr2[ss][which_column.get(ok)])+" ";
									}
								} 
								
								printLine(res);
								
							}
						}
					}
					
					
					
				}
				
				
				printLine(""); //to print new line after each query
			}  
    		
    	}
    	
    }
}
