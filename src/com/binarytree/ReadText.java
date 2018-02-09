package com.binarytree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadText {

	public static void main(String[] args) throws IOException {
		
		List<String> tableList = new ArrayList<String>();
		List<String> columnList = new ArrayList<String>();
		
		BufferedReader tableReader = new BufferedReader(new FileReader(new File("src/tables.txt")));
		BufferedReader columnReader = new BufferedReader(new FileReader(new File("src/columns.txt")));
		
		StringBuilder strBldr = new StringBuilder();
		String line=null;
		
		while((line=tableReader.readLine()) != null){
			tableList.add(line);
		}
		
		while((line=columnReader.readLine()) != null){
			columnList.add(line);
		}
		
		System.out.println("Tota No Of Tables ==== "+tableList.size());
		System.out.println("Tota No Of Columns ==== "+columnList.size());
		
		for (int i = 0; i < tableList.size(); i++) {
			StringBuilder columns = new StringBuilder();
			String table = tableList.get(i);
			int start = columnList.indexOf(table) + 1;
			if((i+1)>=tableList.size()){
				int end = columnList.size();
				List<String> sub = columnList.subList(start, end);
				for (String col : sub) {
					columns.append(col).append(",");
				}
				System.out.println(columns.toString());
			} else {
			String nextTable = tableList.get(i+1);
			int end = columnList.indexOf(nextTable);
			List<String> sub = columnList.subList(start, end);
			for (String col : sub) {
				columns.append(col).append(",");
			}
			System.out.println(columns.toString());
			}
		}

		//boolean first = true;
		/*String table=null;
		int count =0;
		while((line=columnReader.readLine()) != null){
			if(tableList.contains(line)){
				System.err.println(count++ +"  ===  Table precessed === " + table);
				System.out.println(strBldr.toString());
				table=line;
				//if(!first)
					strBldr.replace(0, strBldr.length(), "");
			}else{
				//first = false;
				strBldr.append(line).append(",");
			}
		}*/
	}
}
