package com.tonyqiu.vspider;

import java.io.File;

import org.junit.Test;

public class FileTest {

	@Test
	public void testFileMkdirs() throws Exception {
		String file = "f:/tmp/vSpiderImage/a/b/c/d/aa.txt";
		File f =new File(file);
		f.getParentFile().mkdirs();
	}
}
