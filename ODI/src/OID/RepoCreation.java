package OID;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.odi.core.OdiInstance;
import oracle.odi.core.config.MasterRepositoryDbInfo;
import oracle.odi.core.config.OdiInstanceConfig;
import oracle.odi.core.config.PoolingAttributes;
import oracle.odi.core.repository.WorkRepository;
import oracle.odi.core.security.Authentication;
import oracle.odi.setup.*;
import oracle.odi.setup.support.MasterRepositorySetupImpl;
import oracle.odi.setup.support.WorkRepositorySetupImpl;


public class RepoCreation {
	
	static String arg1;
	static String arg2;
	static String arg3;
	
	
	
	
	
	/*public static void DBOperations(String configFile) throws SQLException, ClassNotFoundException, IOException
	{
		
		System.out.println("Database Creation");
		
		FileInputStream  propFile = new FileInputStream ("C:\\Users\\sbhun\\Documents\\RepoProperties.cfg");
		Properties  props = new Properties();
		
		props.load(propFile);
		
		String dbaUser = props.getProperty("dbaUser");
		String dbaPwd = props.getProperty("dbaPwd");
		
		System.out.println(props.getProperty("dbaUser"));
		Class.forName("oracle.jdbc.driver.OracleDriver");  

		Connection con=DriverManager.getConnection(  
				props.getProperty("RepositoryJdbcUrl"),dbaUser,dbaPwd); 
		Statement stmt=con.createStatement();
		
		if (arg1.equals("master"))
		{	
		
			
		
		
		String masterRepositoryJdbcUser = props.getProperty("masterRepositoryJdbcUser");
		String masterRepositoryJdbcPassword = props.getProperty("masterRepositoryJdbcPassword");
		

		ResultSet rsMasterTS=stmt.executeQuery("CREATE TABLESPACE "+ props.getProperty("masterTableSpace") + " DATAFILE '"+props.getProperty("masterTableSpaceDataFile")+"' size "+ props.getProperty("masterTableSpaceDataFileSize"));
		

		//MasterRepository Creation  
		ResultSet rs=stmt.executeQuery("create user " + masterRepositoryJdbcUser + " identified by " + masterRepositoryJdbcPassword + " default tablespace "+ props.getProperty("masterTableSpace") +" quota unlimited on "+props.getProperty("masterTableSpace")); 
		System.out.println(rs);
		ResultSet rsPriv1=stmt.executeQuery("GRANT RESOURCE, CREATE SESSION, CONNECT,CREATE ANY TABLE,CREATE VIEW,CREATE ANY PROCEDURE TO " + masterRepositoryJdbcUser);

		}
		
		if (arg1.equals("work"))
		{
			ResultSet rsWorkTS=stmt.executeQuery("CREATE TABLESPACE "+ props.getProperty("workTableSpace") + " DATAFILE '"+props.getProperty("workTableSpaceDataFile")+"' size " + props.getProperty("workTableSpaceDataFileSize") );;  

			String workRepositoryJdbcUrl = props.getProperty("RepositoryJdbcUrl");
			String workRepositoryJdbcDriver = props.getProperty("RepositoryJdbcDriver");
			String workRepositoryJdbcUser = props.getProperty("workRepositoryJdbcUser");
			String workRepositoryJdbcPassword = props.getProperty("workRepositoryJdbcPassword");
			String workRepositoryName = props.getProperty("workRepositoryName");

			  
			//WorkRepository Creation  
			ResultSet rsWork=stmt.executeQuery("create user " + workRepositoryJdbcUser + " identified by " + workRepositoryJdbcPassword + " default tablespace "+ props.getProperty("workTableSpace") +" quota unlimited on "+props.getProperty("workTableSpace")); ;  
			ResultSet rsPrivWk1=stmt.executeQuery("GRANT RESOURCE, CREATE SESSION, CONNECT,CREATE ANY TABLE,CREATE VIEW,CREATE ANY PROCEDURE TO " + workRepositoryJdbcUser);
			ResultSet rsPrivWk2=stmt.executeQuery("GRANT EXECUTE ON DBMS_LOCK TO " + workRepositoryJdbcUser);
		}

		  
		//close the connection object  
		con.close();
	}*/
	
	
	@SuppressWarnings("deprecation")
	public static void MasterRepoCreation(String configFile) throws SQLException, ClassNotFoundException {
		
	
	
	try {
	FileInputStream  propFile = new FileInputStream (configFile);
	Properties  props = new Properties();
	
	
	props.load(propFile);

	
	      
	String odiSupervisorUser = props.getProperty("odiSupervisorUser");
	String odiSupervisorPassword =props.getProperty("odiSupervisorPassword");


	String dbaUser = props.getProperty("dbaUser");
	String dbaPwd = props.getProperty("dbaPwd");


	TechnologyName masterRepositoryTechnology = TechnologyName.ORACLE;
	String masterRepositoryJdbcUrl = props.getProperty("RepositoryJdbcUrl");
	String masterRepositoryJdbcDriver = props.getProperty("RepositoryJdbcDriver");
	String masterRepositoryJdbcUser = props.getProperty("masterRepositoryJdbcUser");
	String masterRepositoryJdbcPassword = props.getProperty("masterRepositoryJdbcPassword");



	
	
	  

	IMasterRepositorySetup masterRepositorySetup = new MasterRepositorySetupImpl();
	AuthenticationConfiguration authConf = AuthenticationConfiguration.createStandaloneAuthenticationConfiguration(odiSupervisorPassword.toCharArray());

	
	JdbcProperties mRepJdbcInfo = new JdbcProperties(masterRepositoryJdbcUrl,
	                masterRepositoryJdbcDriver,
	                masterRepositoryJdbcUser,
	                masterRepositoryJdbcPassword.toCharArray());
	               
	try {
		masterRepositorySetup.createMasterRepository(mRepJdbcInfo,
		                                      dbaUser,
		                                      dbaPwd.toCharArray(),
		                                      masterRepositoryTechnology,
		                                      true,
		                                      authConf,
		                                      null);
		
		//masterRepositorySetup.upgradeMasterRepository(mRepJdbcInfo,dbaUser,dbaPwd.toCharArray(),"12.2.1.4.0");
		
	} catch (RepositorySetupException e1) {
		// TODO Auto-generated catch block
		
		e1.printStackTrace();
	}


	}
	catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
	
	public static void WorkRepoCreation(String configFile) throws SQLException, ClassNotFoundException {
		
	
	
	try {
	FileInputStream  propFile = new FileInputStream (configFile);
	Properties  props = new Properties();
	
	
	props.load(propFile);

	
	      
	String odiSupervisorUser = props.getProperty("odiSupervisorUser");
	String odiSupervisorPassword =props.getProperty("odiSupervisorPassword");


	String dbaUser = props.getProperty("dbaUser");
	String dbaPwd = props.getProperty("dbaPwd");


    TechnologyName masterRepositoryTechnology = TechnologyName.ORACLE;
	String masterRepositoryJdbcUrl = props.getProperty("RepositoryJdbcUrl");
	String masterRepositoryJdbcDriver = props.getProperty("RepositoryJdbcDriver");
	String masterRepositoryJdbcUser = props.getProperty("masterRepositoryJdbcUser");
	String masterRepositoryJdbcPassword = props.getProperty("masterRepositoryJdbcPassword");


	@SuppressWarnings("unused")
	TechnologyName workRepositoryTechnology = TechnologyName.ORACLE;
	String workRepositoryJdbcUrl = props.getProperty("RepositoryJdbcUrl");
	String workRepositoryJdbcDriver = props.getProperty("RepositoryJdbcDriver");
	String workRepositoryJdbcUser = props.getProperty("workRepositoryJdbcUser");
	String workRepositoryJdbcPassword = props.getProperty("workRepositoryJdbcPassword");
	String workRepositoryName = props.getProperty("workRepositoryName");
	
	

	IMasterRepositorySetup masterRepositorySetup = new MasterRepositorySetupImpl();
	AuthenticationConfiguration authConf = AuthenticationConfiguration.createStandaloneAuthenticationConfiguration(odiSupervisorPassword.toCharArray());

	


	MasterRepositoryDbInfo mRepDbInfo = new MasterRepositoryDbInfo(masterRepositoryJdbcUrl,
	                masterRepositoryJdbcDriver,
	                masterRepositoryJdbcUser,
	                masterRepositoryJdbcPassword.toCharArray(),
	                new PoolingAttributes()
	                );
	        OdiInstance odiInstance = OdiInstance.createInstance(new OdiInstanceConfig(mRepDbInfo, null));
	        
	        Authentication auth = odiInstance.getSecurityManager().createAuthentication(odiSupervisorUser, odiSupervisorPassword.toCharArray());

	     
	            odiInstance.getSecurityManager().setCurrentThreadAuthentication(auth);

	            IWorkRepositorySetup workRepositorySetup = new WorkRepositorySetupImpl(odiInstance);
	            //JdbcProperties wRepJdbcInfo = new JdbcProperties(workRepositoryJdbcUrl, workRepositoryJdbcDriver, workRepositoryJdbcUser, workRepositoryJdbcPassword)
	            try {
	            	if (props.getProperty("WorkRepositoryType").equals("D"))
	            	{
	            
	                workRepositorySetup.createWorkDevRepository(workRepositoryJdbcUrl,
                            workRepositoryJdbcDriver,
                            workRepositoryJdbcUser,
                            workRepositoryJdbcPassword,
                            workRepositoryName,
                            null,
                            workRepositoryTechnology,
                            true);
	            	}
	            	
	            	if (props.getProperty("WorkRepositoryType").equals("E"))
	            		
	            		
	            	{
	            		workRepositorySetup.createWorkRuntimeRepository(workRepositoryJdbcUrl,
	                            workRepositoryJdbcDriver,
	                            workRepositoryJdbcUser,
	                            workRepositoryJdbcPassword,
	                            workRepositoryName,
	                            null,
	                            workRepositoryTechnology,
	                            true);
	            	}
	            	
				} catch (RepositorySetupException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            odiInstance.close();
	}
	catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
	public static void main(String[] args) throws IOException
	{

		arg1 = args[0];
		arg2 = "RepoProperties.cfg";
		//arg3 = args[2];
		
		RepoCreation repoCreation=new RepoCreation();
		
		try {
			
			if (arg1.equals("Repository"))
			{
			System.out.println("Start Repository Creation");
			RepoCreation.MasterRepoCreation(arg2);
			RepoCreation.WorkRepoCreation(arg2);
			}
			
			else if (arg1.equals("master"))
			{
			RepoCreation.MasterRepoCreation(arg2);
			}
			
			else if (arg1.equals("work"))
			{
			 repoCreation.WorkRepoCreation(arg2);	
			}
			
			else
			{
				System.out.println("Argument does not match " + arg1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

