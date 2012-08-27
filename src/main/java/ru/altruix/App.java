package ru.altruix;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public void run()
    {
        LOGGER.info("Sample application");
        
        final DataReader reader = new DataReader();
        reader.setPath("death-rates.csv");
        reader.readData();
        
        final Map<Integer,Double> deathRatesByAge = reader.getData();
        
        
    }
    public static void main( String[] args )
    {
        final App app = new App();
        
        app.run();
    }
}
