package ch.makery.address.persistance ;

public class TxnScript
{

	    /* example of call 
	VilleIterator myVilleIt = myTxnScript.getVilleIterator()
	int myCurrentCodePostal ;
	String myCurrentNom ;    
	while ( myVilleIt.hasNext() )
	{
		myCurrentNom = myVilleIt.nextNomVille ( ) ;
		myCurrentCodePostal = myVilleIt.nextCodePostalVille ( ) ;		
	        villeData.add(new Ville( myCurrentCodePostal, myCurrentNom));
	}
	*/
  
  public class VilleIterator
  {
    // https://www.baeldung.com/java-nested-classes#static-nested-classes 
    bool hasNext()
    {
	    return the_result ;
    }
	  
    String nextNomVille()
    { String the_result = "" ; return the_result ; }
	  
    String nextCodePostal()
    { String the_result = "" ; return the_result ; }
  }
  
}
