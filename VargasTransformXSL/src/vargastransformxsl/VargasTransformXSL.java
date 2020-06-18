
package vargastransformxsl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.OutputKeys;

/**
 *
 * @author Roser
 */
public class VargasTransformXSL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VargasTransformXSL ap = new VargasTransformXSL();
        ap.run();
    }  
    
    private void run() {

        try {
            // Read file names from user.;
            String xmlInputFilename = inputString("Enter source XML file name (with \".xml\" at the end): ");
            String xslInputFilename = inputString("Enter source XSL file name (with \".xsl\" at the end): ");
            String xmlOutputFilename = inputString("Enter destination XML file name (with \".html\" at the end): ");
            
            //get the type of files given 
            String[] provArray= xmlInputFilename.split("\\.");
            String extXML= provArray[provArray.length-1];
            provArray= xslInputFilename.split("\\."); 
            String extXSL= provArray[provArray.length-1];
            provArray= xmlOutputFilename.split("\\."); 
            String extHTML= provArray[provArray.length-1];
            
            //check if the given files matches with the type of files needed
            if ("xml".equals(extXML) & "xsl".equals(extXSL) & "html".equals(extHTML)) {            
                //Create sources (in) i result (out)
                Source input  = new StreamSource(xmlInputFilename);
                Source xsl    = new StreamSource(xslInputFilename);
                Result output = new StreamResult(xmlOutputFilename);
                // Perform transformation: create a transformer factory
                TransformerFactory factory = TransformerFactory.newInstance();
                // create a transformer (by  giving xsl document)
                Transformer transformer = factory.newTransformer(xsl);
                //indent
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                //tranform
                transformer.transform(input, output);
                System.out.println("File created");
            } else {
                System.out.println("Type of files not valid, please, check them.");
            }
        } catch (TransformerException ex) {
            Logger.getLogger(VargasTransformXSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VargasTransformXSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method for reading input message by console
     * @param message Shown message to user asking an answer
     * @return the answer written by user
     * @throws IOException 
     */
    private String inputString(String message) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return bf.readLine();
    }    
    
}
