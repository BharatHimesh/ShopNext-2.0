
package com.esper.mule.demo;

import java.util.Scanner;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.module.client.MuleClient;

import com.esper.config.loader.DataLoader;
import com.esper.event.config.EsperConfiguration;

public class WebsocketDemo {
	
    public static void main(final String[] args) throws Exception {
        new WebsocketDemo().run();
    }

    private void run() throws Exception {
    	
//    	Creating an instance of Mule application
        final MuleContext muleContext = bootstrapMule();
        
//      Create Standard Events and Listeners for Esper
        EsperConfiguration.addEsperListners();

//      Load initial data during Esper startup
        DataLoader.loadInitialConfigData();
        
//     	Wait for User Input to terminate application
        waitForEnter();
        
//      Terminate Mule Instance
        terminateMule(muleContext);
    }

    /**
     * Creates a Mule Instance with supplied configuration file.
     * 
     * @return
     * @throws MuleException
     */
    private MuleContext bootstrapMule() throws MuleException {
        System.out.printf("Bootstrapping Mule with config: %s ...", getConfigurationFile());
        
        final MuleClient muleClient = new MuleClient(getConfigurationFile());
        final MuleContext muleContext = muleClient.getMuleContext();
        muleContext.start();
        
        System.out.println(" Done!\n");
        return muleContext;
    }

    /**
     * When the User hits Enter key the control is returned.
     */
    private static void waitForEnter() {
        System.out.println("Hit ENTER to stop...\n");
        new Scanner(System.in).nextLine();
    }

    /**
     * Returns Mule Configuration XML
     * 
     * @return
     */
    private String getConfigurationFile() {
        return "mule-config.xml";
    }
    
    /**
     * Terminates the Mule Instance.
     * 
     * @param muleContext
     */
    private void terminateMule(final MuleContext muleContext) {
        System.out.print("Terminating Mule...");
        muleContext.dispose();
        System.out.println(" Bye!");
        System.exit(0);
    }
}
