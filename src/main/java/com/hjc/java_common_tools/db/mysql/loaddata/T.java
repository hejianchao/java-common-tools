package com.hjc.java_common_tools.db.mysql.loaddata;

public class T {

	public static void main(String[] args) {
		System.out.println(replaceChars("abcd\001a asdfsd\001", new char[] {
				'\n', ' ', '\001', 0 }));

	}

	public static String replaceChars(String old, char[] rchars) {
		if (null == rchars)
			return old;

		int oldLen = old.length();
		int rLen = rchars.length;

		StringBuilder sb = new StringBuilder(oldLen);
		char[] oldArrays = old.toCharArray();
		boolean found;
		char c1;

		for (int i = 0; i < oldLen; i++) {
			found = false;
			c1 = oldArrays[i];
			for (int j = 0; j < rLen; j += 2) {
				if (c1 == rchars[j]) {
					if (rchars[j + 1] != 0) {
						sb.append(rchars[j + 1]);
					}
					found = true;
				}
			}
			if (!found) {
				sb.append(c1);
			}
		}

		return sb.toString();
	}
}
