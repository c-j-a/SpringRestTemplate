package com.solarwinds.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

public class ClientProperties {

    private final static String PROPERTIES_FILE = "client.properties";

    public static final String BASE_URL = "base.url";
    public static final String REFRESH_TOKEN = "refresh.token";
    public static final String DEBUG = "debug";

    private Properties dbProps = null;

    public ClientProperties() {
        loadProps();
    }

    /**
     * Gets the property.
     *
     * @param propName the name of the Property to get
     *
     * @return the property
     */
    public String getProperty(final String propName) {
        String retVal = null;
        if (dbProps != null) {
            retVal = dbProps.getProperty(propName);
            if (retVal == null) {
                retVal = "";
            }
        }
        return retVal;
    }

    /**
     * Load up Values From Property Files.
     * Will try to create a default property file.
     */
    private void loadProps() {
        if (dbProps == null) {
            Properties p = new Properties();
            try {
                File f = new File(PROPERTIES_FILE);
                if (!f.exists()) {
                    dbProps = createDefaultProps();
                    if (dbProps != null) {
                        println("\nCreated default properties: " + f.getAbsolutePath());
                        println("Please edit and restart.");
                        System.exit(0);
                    }
                }
                else {
                    String msg = "Loading properties " + getFilePath(f);
                    System.out.println(msg);
                    FileInputStream propFile = new FileInputStream(f);
                    p.load(propFile);
                    propFile.close();
                    dbProps =  p;
                }
            } catch (Exception e) {
                println("loadProps() Exception " + e);
            }
        }
    }

    // Used to create the default properties file
    /**
     * Creates the default props.
     */
    private Properties createDefaultProps() {
        Properties p = new Properties();
        p.put(BASE_URL, "http://localhost:8123/iwc");
        p.put(REFRESH_TOKEN, "ChangeMe");
        p.put(DEBUG, "false");

        writeProps(p);
        return p;
    }

    /**
     * Write props.
     *
     * @param p the properties to write
     */
    private void writeProps(Properties p) {
        try {
            File propsFile = new File(PROPERTIES_FILE);
            // File does not yet exists, lets create one
            if (!propsFile.exists()) {
                PropertiesConfiguration pc = new PropertiesConfiguration();
                PropertiesConfigurationLayout pcl = new PropertiesConfigurationLayout(pc);

                // Set the given properties on our commons config
                Set<Map.Entry<Object, Object>> entries = p.entrySet();
                for (Map.Entry<Object, Object> entry : entries) {
                    pc.setProperty(entry.getKey().toString(), entry.getValue());
                }

                // Add the comments/instructions
                FileWriter fWriter = new FileWriter(propsFile);
                pcl.setHeaderComment(getHeader());

                pcl.save(fWriter);
                fWriter.close();
            }
            // Does exist, update using commons to preserve comments
            else {
                PropertiesConfiguration propertiesConfigration = new PropertiesConfiguration();
                PropertiesConfigurationLayout propertiesConfigrationLayout = new PropertiesConfigurationLayout(propertiesConfigration);
                FileReader fReader = new FileReader(propsFile);
                propertiesConfigrationLayout.load(fReader);
                fReader.close();

                // Set the given properties on our commons config
                Set<Map.Entry<Object, Object>> entries = p.entrySet();
                for (Map.Entry<Object, Object> entry : entries) {
                    propertiesConfigration.setProperty(entry.getKey().toString(), entry.getValue());
                }
                FileWriter fWriter = new FileWriter(propsFile);
                propertiesConfigrationLayout.save(fWriter);
                fWriter.close();
            }
        } catch (Exception e) {
            println("AppProperties.writeProps() Exception " + e);
        }
    }

    private String getHeader() {
        return "##################################################################################################\n" +
               "# " + BASE_URL + " - The URL of DPA.\n" +
               "# " + REFRESH_TOKEN + " - The refresh token to use.\n" +
               "# " + DEBUG + " - Turn on request and response debug.\n" +
               "##################################################################################################";
    }

    private static String getFilePath(File fCurrentDir) {
        try {
            return fCurrentDir.getCanonicalPath();
        }
        catch (IOException e) {
            return fCurrentDir.getAbsolutePath();
        }
    }

    private static void println(String text) {
        System.out.println(text);
    }

}
