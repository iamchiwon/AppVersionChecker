package com.iamchiwon.appversionchecker;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class AppVersion {
	@Id
	public String appkey;
	public String version;
}
