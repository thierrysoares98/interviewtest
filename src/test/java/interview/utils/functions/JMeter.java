package interview.utils.functions;

import java.io.File;
import java.io.IOException;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;


import static org.apache.jmeter.JMeter.convertSubTree;

public class JMeter {

    public JMeter(){}

    public void callJMeterFromJMX(String jMeterHome, String jmxFilePath, String resultPath){
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties(jMeterHome + "/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(jMeterHome);
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        try {
            SaveService.loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load existing .jmx Test Plan
        HashTree testPlanTree = null;
        try {
            testPlanTree = SaveService.loadTree(new File(jmxFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove disabled test elements
        convertSubTree(testPlanTree);

        // Add summariser
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        // Store execution results into a .jtl file
        String logFile = resultPath;
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        testPlanTree.add(testPlanTree.getArray()[0], logger);

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
    }

}
