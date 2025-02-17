package com.transfer.saad.utils;

import java.sql.Timestamp;
import java.util.Random;

public class Utils {
	
	public static String generateAccNum() {
		Random rnd = new Random();
		int part1 = rnd.nextInt(654321);
		int part2 = rnd.nextInt(99999);
		return String.valueOf(part1 + "" + part2);
	}
	
	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return String.valueOf(timestamp);
	}

}
