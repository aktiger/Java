

// http://dev.mysql.com/doc/refman/5.6/en/create-table.html
create_table_statement:
	create_table_statement1 | create_table_statement2 | create_table_statement3
;

create_table_statement1:	
	CREATE (TEMPORARY)? TABLE (IF NOT_SYM EXISTS)? table_name
	LPAREN create_definition (COMMA create_definition)* RPAREN
	(table_options)?
	(partition_options)?
	(select_statement)?
;

create_table_statement2:
	CREATE (TEMPORARY)? TABLE (IF NOT_SYM EXISTS)? table_name
	(table_options)?
	(partition_options)?
	select_statement
;

create_table_statement3:
	CREATE (TEMPORARY)? TABLE (IF NOT_SYM EXISTS)? table_name
	( (LIKE_SYM table_name) | (LPAREN LIKE_SYM table_name RPAREN) )
;