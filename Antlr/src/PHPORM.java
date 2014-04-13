/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class PHPORM {
	
    public static class PHPGenerator extends SQLiteBaseListener{
        Map<String,String> filemap = new OrderedHashMap<String, String>();
        //ArrayList<String> tmp = new ArrayList<String>();
        String tableName;
        public void exitCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
            //String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
            //String value = ctx.STRING().getText();
            //props.put(id, value);
        	tableName= ctx.table_name().getText();
        	
        	
        }
        
        public void exitColumn_def(@NotNull SQLiteParser.Column_defContext ctx) {
        	
        	String column_def = ctx.column_name().getText();
        	//tmp.add(column_def);
        	String type = ctx.type_name().getText();
        	//tmp.add(type);
        	filemap.put(column_def, type);
        }
    }

    //将下划线转换为驼峰
	public static String camelName(String name) {

	    StringBuilder result = new StringBuilder();

	    // 快速检查

	    if (name == null || name.isEmpty()) {

	        // 没必要转换

	        return "";

	    } else if (!name.contains("_")) {

	        // 不含下划线，仅将首字母小写

	        return name.substring(0, 1).toLowerCase() + name.substring(1);

	    }

	    // 用下划线将原始字符串分割

	    String camels[] = name.split("_");

	    for (String camel :  camels) {

	        // 跳过原始字符串中开头、结尾的下换线或双重下划线

	        if (camel.isEmpty()) {

	            continue;

	        }

	        // 处理真正的驼峰片段

	        if (result.length() == 0) {

	            // 第一个驼峰片段，全部字母都小写

	            result.append(camel.toLowerCase());

	        } else {

	            // 其他的驼峰片段，首字母大写

	            result.append(camel.substring(0, 1).toUpperCase());

	            result.append(camel.substring(1).toLowerCase());

	        }

	    }

	    return result.toString();

	}

    public static void main(String[] args) throws Exception {
        
    	String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        SQLiteLexer lexer = new SQLiteLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(tokens);
        ParseTree tree = parser.parse();

        // create a standard ANTLR parse tree walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker
        PHPGenerator loader = new PHPGenerator();
        walker.walk(loader, tree);        // walk parse tree
        //System.out.println(loader.filemap);// print results
        
        Map<String,String> columnTypeMap= new OrderedHashMap<String, String>();
        Iterator<Map.Entry<String, String>> entries = loader.filemap.entrySet().iterator();
        String tableName = loader.tableName.replace('`', ' ').trim();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> setNames = new ArrayList<String>();
        ArrayList<fieldNameAndSetName> fNS = new ArrayList<fieldNameAndSetName>();
        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();
            String columnName = entry.getKey();
            columnName = columnName.replace('`', ' ').trim();
            if(columnName.equalsIgnoreCase("PRIMARY") || columnName.equalsIgnoreCase("KEY") || 
            		columnName.equalsIgnoreCase("CONSTRAINT")) {
            	continue;
            }
            names.add(columnName);
            String cloumnType = entry.getValue();
            columnTypeMap.put(columnName, cloumnType);
        }
        
        for(String n: names) {
        	setNames.add(camelName("set_"+n));
        }
        for(int i=0; i<names.size(); i++) {
        	String field = names.get(i);
        	String funcName = setNames.get(i);
        	String cond = "is_string";
        	String fieldType = columnTypeMap.get(field);
        	if(fieldType.indexOf("int") != -1) {
        		cond = "is_integer";
        	}
        	fieldNameAndSetName e = new fieldNameAndSetName(field, funcName, cond);
        	fNS.add(e);
        }
        
        entries = columnTypeMap.entrySet().iterator();
        while (entries.hasNext()) {

            Map.Entry<String, String> entry = entries.next();
            String columnName = entry.getKey();
            columnName = columnName.replace('`', ' ').trim();
            String cloumnType = entry.getValue();
            columnTypeMap.put(columnName, cloumnType);
            
            System.out.println("Key = " + columnName + ", Value = " + cloumnType);

        }
        
        System.out.println("--------------------");
        String dbtable = tableName;
        tableName =  camelName(tableName);
        tableName = "DB" + tableName.substring(0,1).toUpperCase() + tableName.substring(1);
        System.out.println("tablename=" + tableName);
        
        
        STGroup group = new STGroupFile("./Template.stg");

        ST stClass= group.getInstanceOf("classDeclare");
        stClass.add("classname", tableName);
        stClass.add("fieldnames", names);
        stClass.add("setNames", fNS);
        stClass.add("length_1",fNS.size()-1);
        stClass.add("dbtable", dbtable);
        String result = stClass.render(); 
        System.out.println(result);
        
    }
}
