package test;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import mblog.utils.PropertiesLoader;

public class PropertiesLoaderTest {
	
	@Test
	public void testReadProperties(){
		PropertiesLoader pl = new PropertiesLoader("test.properties");
		String strProp = pl.getProperty("rootPath");
		String notExist = pl.getProperty("propertyNotExist", "propertyNotExist");
		double doubleProp = pl.getDouble("doubleProp");
		boolean bp = pl.getBoolean("booleanProp");
		int intProp = pl.getInteger("intProp");
		int intProp2 = pl.getInteger("intNotExist", 888);
		Properties properties = pl.getProperties();
		Assert.assertEquals(strProp, "/data/mblog");
		Assert.assertEquals("propertyNotExist", notExist);
		Assert.assertFalse(bp);
		Assert.assertEquals(0.1D, doubleProp, 0);
		Assert.assertEquals(666, intProp);
		Assert.assertEquals(888, intProp2);
		Assert.assertNotNull(properties);
		
	}

}
