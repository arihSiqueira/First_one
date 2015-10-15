import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class BancoTeste{
	
	private Statement executor;
	private Connection con;
	
	public BancoTeste(){
		try{
			con = DriverManager.getConnection("jdbc:derby:OficioReceita;create=true");
			executor = con.createStatement();
			if(!existeTabela("oficioJuiz")){
				criarJuiz();}
			if(!existeTabela("chefesSetec")){
				criarChefes();}
			if(!existeTabela("varaOficio")){
				criarTableVara();}
			if(!existeTabela("oficioReceita")){
				criaOficio();}
			if(!existeTabela("oficioResposta")){
				criarOficioResposta();}
			if(!existeTabela("juiz_vara")){
				criarJuizVara();}
			if(!existeTabela("oficio_resposta")){
				criarOficioResp();}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public  Connection getConexao(){
			return con;
	}
	
	private boolean existeTabela(String s) throws SQLException{
		try{
			executor.execute("SELECT * FROM"+" "+s);
		}catch(SQLException sqle){
			String theError = (sqle).getSQLState();
			if(theError.equals("42x14")){
				return false; }
			else if (theError.equals("42X14") || theError.equals("42821")) {//Estrutura da tabela está errada
				System.out.println("\nTabela foi encontrada porém sua estrtura é diferente da esperada, exclua para solucionar o problema!\n");
	            throw sqle; }
			else if(theError.equals("42X05")){
				return false;
			}
			else {
				String theError2 = (sqle).getSQLState();
				System.out.println("Chgou aqui +"+theError2);
				throw sqle; 
	            }
	        }
	        
	        return true;
	}
	
	private void criaOficio() throws SQLException{
		String createString = "CREATE TABLE oficioReceita"
		  + "( COD INT NOT NULL GENERATED ALWAYS AS IDENTITY " 
		  +  " CONSTRAINT Codreceita_PK PRIMARY KEY, "
		  +  " oficio VARCHAR(32),"
		  +  " processo VARCHAR(32) NOT NULL,"
	      +  " pa VARCHAR(32) NOT NULL ,"
		  +  " nome_servidor VARCHAR(32) NOT NULL, "
	      +  " cpf_servidor  VARCHAR(32) NOT NULL,"
	      +  " cod_juiz int  NOT NULL references oficioJuiz,"
	      +  " cod_chefe int NOT NULL references chefesSetec)";
		
		executor.execute(createString);
	}
	
	private void criarOficioResp() throws SQLException{
		String create = " CREATE TABLE oficio_resposta"
				+ "(cod_oficio INT NOT NULL REFERENCES oficioReceita(COD),"
				+ " cod_resp INT NOT NULL REFERENCES oficioResposta(cod_resp),"
				+ " CONSTRAINT oficioResp_pk PRIMARY KEY (cod_oficio, cod_resp))";
		
		executor.execute(create);
	}
	
	private void criarJuiz() throws SQLException{
		String createString = "CREATE TABLE oficioJuiz "
		  + "( cod_juiz INT NOT NULL GENERATED ALWAYS AS IDENTITY " 
	      +  " CONSTRAINT CodJuiz_PK PRIMARY KEY, " 
	      +  " nomeJuiz VARCHAR(32) NOT NULL)";
		  executor.execute(createString);
	}
	
	private void criarChefes() throws SQLException{
		String createString = "CREATE TABLE chefesSetec "
			+"( cod_chefe INT NOT NULL GENERATED ALWAYS AS IDENTITY "
			+" CONSTRAINT codChefe_PK PRIMARY KEY, "
			+" nome_chefe VARCHAR(32), "
			+" caracteristica VARCHAR(32) )";
		executor.execute(createString);
	}
	
	private void criarOficioResposta()throws SQLException{
		String createString = "CREATE TABLE oficioResposta "
				+"( cod_resp INT NOT NULL GENERATED ALWAYS AS IDENTITY "
				+ "CONSTRAINT codResposta_PK PRIMARY KEY,"
				+ "titulo VARCHAR(32),"
				+ "resposta VARCHAR(244))";
		
		executor.execute(createString);
		}
	
	private void criarTableVara() throws SQLException{
		String create = "CREATE TABLE varaOficio"
				+"(COD INT NOT NULL GENERATED ALWAYS AS IDENTITY "
				+ "CONSTRAINT VARA_PK PRIMARY KEY,  "
				+"poder_vara VARCHAR(32),"
				+"nome_vara VARCHAR(32) NOT NULL,"
				+"bairro_vara VARCHAR(32),"
				+"cidade VARCHAR(32),"
				+"abreviacao VARCHAR(12),"
				+"uf VARCHAR(2),"
				+"telefone INT,"
				+"cep INT)";
		
		executor.execute(create);
	}
	
	private void criarJuizVara() throws SQLException{
		String create = "CREATE TABLE juiz_vara"
				+ "(codJuiz INT NOT NULL CONSTRAINT codJ_PK REFERENCES oficioJuiz,"
				+ "codVara INT NOT NULL,"
				+ "FOREIGN KEY (codVara)  REFERENCES varaOficio(COD),"
				+ "CONSTRAINT codJuizVara_PK PRIMARY KEY(codJuiz, codVara)"
				+ ")";
		executor.execute(create);
	}

	public void show() throws SQLException{
		String show = "SELECT * FROM oficioReceita";
		
		executor.execute(show);
	}
	
	
}
