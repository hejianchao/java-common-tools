package com.hjc.java_common_tools.apache.io;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class FileUtils_Study {

	@Test
	public void test() throws Exception {
		String content = "my name is hejianchao\nhow about you ?";
		File file = new File("c:/hello.txt");
		FileUtils.writeStringToFile(file, content);

		Assert.assertTrue(file.length() == content.length());
		
		List<String> lines = FileUtils.readLines(file);
		for (String string : lines) {
			System.out.println(string);
		}
		
		FileUtils.getUserDirectoryPath();
	}
}
