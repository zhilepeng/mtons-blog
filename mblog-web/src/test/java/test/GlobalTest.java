package test;

import junit.framework.Assert;
import mblog.core.context.Global;

import org.junit.Test;

public class GlobalTest {

	@Test
	public void testGlobal(){
		String rootPath = Global.getRoot();
		String ava = Global.getAvaDir();
		String origDir = Global.getOrigDir();
		String temp = Global.getTempDir();
		String thumb = Global.getThumbsDir();
		
//		rootPath=/data/mblog
//		origDir=/store/orig
//		thumbsDir=/store/thumbs
//		avaDir=/store/ava
//		tempDir=/store/temp
		Assert.assertEquals("/data/mblog", rootPath);
		Assert.assertEquals("/store/ava", ava);
		Assert.assertEquals("/store/orig", origDir);
		Assert.assertEquals("/store/temp", temp);
		Assert.assertEquals("/store/thumbs", thumb);
	}
}
