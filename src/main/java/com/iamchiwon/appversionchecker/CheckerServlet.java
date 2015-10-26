package com.iamchiwon.appversionchecker;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class CheckerServlet extends HttpServlet {

	private static final long serialVersionUID = 5287674749489451645L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/versions");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String appkey = req.getParameter("appkey");
		String currentVersion = req.getParameter("cver");

		resp.setContentType("application/json");

		JSONObject json = new JSONObject();

		if (appkey == null) {
			json.put("status", "404");
		} else {
			json.put("appkey", appkey);
			String latestVersion = getLatestVersion(appkey, currentVersion);
			if (latestVersion == null) {
				json.put("message", "parameter 'cver' not found");
				json.put("status", "400");
			} else {
				json.put("lver", latestVersion);
				json.put("status", "200");
			}
		}

		resp.getWriter().println(json.toString());

	}

	public String getLatestVersion(String appkey, String current) {
		Key<AppVersion> courseKey = Key.create(AppVersion.class, appkey);
		AppVersion app = ObjectifyService.ofy().load().type(AppVersion.class).filterKey(courseKey).first().now();
		String latest = app == null ? null : app.version;

		if (latest == null && current == null)
			return null;

		if (current == null)
			return latest;

		if (latest == null || latest.compareTo(current) < 0) {
			latest = current;

			AppVersion newApp = new AppVersion();
			newApp.appkey = appkey;
			newApp.version = latest;

			ObjectifyService.ofy().save().entity(newApp).now();
		}

		return latest;
	}

}
