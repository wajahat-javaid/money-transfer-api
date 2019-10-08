package com.simple.money.transfer.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationProperties 
{
	private static final String PROPERTY_FILE = "application.properties";

	private static Properties properties = new Properties();
	static Logger log = LoggerFactory.getLogger(ApplicationProperties.class);


	static 
	{

		loadConfig(PROPERTY_FILE);
	}


	public static void loadConfig(String fileName) {
		if (fileName == null) {
			log.warn("loadConfig: config file name cannot be null");
		} else {
			try {
				log.info("loadConfig(): Loading config file: " + fileName );
				final InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
				properties.load(fis);

			} catch (FileNotFoundException fne) {
				log.error("loadConfig(): file name not found " + fileName, fne);
			} catch (IOException ioe) {
				log.error("loadConfig(): error when reading the config " + fileName, ioe);
			}
		}

	}


	public static String getStringProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			value = System.getProperty(key);
		}
		return value;
	}

	/**
	 * @param key:       property key
	 * @param defaultVal the default value if the key not present in config file
	 * @return string property based on lookup key
	 */
	public static String getStringProperty(String key, String defaultVal) {
		String value = getStringProperty(key);
		return value != null ? value : defaultVal;
	}


	public static int getIntegerProperty(String key, int defaultVal) {
		String valueStr = getStringProperty(key);
		if (valueStr == null) {
			return defaultVal;
		} else {
			try {
				return Integer.parseInt(valueStr);

			} catch (Exception e) {
				log.warn("getIntegerProperty(): cannot parse integer from properties file for: " + key + "fail over to default value: " + defaultVal, e);
				return defaultVal;
			}
		}
	}


}
