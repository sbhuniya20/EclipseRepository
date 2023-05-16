package OID;

import oracle.odi.core.OdiInstance;
import oracle.odi.core.persistence.transaction.ITransactionStatus;
import oracle.odi.core.persistence.transaction.support.TransactionCallbackWithoutResult;
import oracle.odi.core.persistence.transaction.support.TransactionTemplate;
import oracle.odi.domain.project.OdiFolder;
import oracle.odi.domain.project.OdiProject;
import oracle.odi.domain.topology.OdiContext;
import oracle.odi.domain.topology.OdiContextualSchemaMapping;
import oracle.odi.domain.topology.OdiDataServer;
import oracle.odi.domain.topology.OdiLogicalSchema;
import oracle.odi.domain.topology.OdiPhysicalAgent;
import oracle.odi.domain.topology.OdiPhysicalSchema;
import oracle.odi.domain.topology.OdiTechnology;
import oracle.odi.domain.topology.finder.IOdiTechnologyFinder;
import oracle.odi.domain.util.ObfuscatedString;
import oracle.odi.publicapi.samples.SimpleOdiInstanceHandle;
 
public class AgentCreation {
 
private static OdiDataServer oracleDataServer;
protected static String pStringToObfuscate;
protected static OdiContext context;
 
/**
* @param args
*/
public static void main(String[] args) {
	


//SimpleOdiInstanceHandle.create(pStringToObfuscate, pStringToObfuscate, pStringToObfuscate, null, pStringToObfuscate, null)

@SuppressWarnings("deprecation")
final SimpleOdiInstanceHandle odiInstanceHandle = SimpleOdiInstanceHandle
.create("jdbc:oracle:thin:@localhost:1521:oracle",
"oracle.jdbc.OracleDriver",
"PTLABR8ODI_MASTER_REPO",
"Horekorekomba_1",
"WORKREPO1",
"SUPERVISOR",
"Horeorekomba_1");
 
final OdiInstance odiInstance = odiInstanceHandle.getOdiInstance();
try {
TransactionTemplate tx = new TransactionTemplate(odiInstance.getTransactionManager());
tx.execute(new TransactionCallbackWithoutResult()
{
   protected void doInTransactionWithoutResult(ITransactionStatus pStatus)
   {
 
      //
     //  ODI SDK Codes goes here
    //
 
OdiPhysicalAgent odiPhysicalAgent=new OdiPhysicalAgent("Test");
 
// Persist the Data Server , Physical and Logical Schema
 
odiInstance.getTransactionalEntityManager().persist(odiPhysicalAgent);
 
System.out.println("Done");
 
}
});
}
 
finally
 
{
odiInstanceHandle.release();
}
 
}
 
}