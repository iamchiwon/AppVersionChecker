package com.iamchiwon.appversionchecker;

import java.util.Enumeration;
import java.util.Hashtable;

public class Versions {

	private static Versions _instance;

	public static Versions getInstance() {
		if (_instance == null)
			_instance = new Versions();
		return _instance;
	}

	private Hashtable<String, String> database;

	protected Versions() {
		database = new Hashtable<String, String>();
	}

	public String getLatestVersion(String appkey, String current) {
		String latest = database.get(appkey);

		if (latest == null && current == null)
			return null;

		if (current == null)
			return latest;

		if (latest == null || latest.compareTo(current) < 0) {
			latest = current;
			database.put(appkey, latest);
		}

		return latest;
	}

	public Enumeration<String> apps() {
		return database.keys();
	}
}
