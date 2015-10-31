package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.JxfwjsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontJxfwAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private JxfwjsService jxfwjsService = new JxfwjsService();

	public String jxfwList() {
		session.put("jxfwjs", jxfwjsService.loadJxfwjs());
		return "jxfwList";
	}

}
