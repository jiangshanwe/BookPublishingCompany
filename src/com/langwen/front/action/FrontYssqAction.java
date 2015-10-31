package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.YssqService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontYssqAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private YssqService yssqService = new YssqService();

	public String yssq() {
		session.put("yssq", yssqService.loadYssq());
		return "yssq";
	}

}
