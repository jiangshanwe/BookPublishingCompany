package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.LwjjService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontLwjjAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private LwjjService lwjjService = new LwjjService();

	private Integer id;

	public String lwjj() {
		session.put("lwjjList", lwjjService.allLwjj());
		// 根据id判断显示哪一个朗文简介项目
		if (id == null) {
			Integer lid = lwjjService.allLwjj().get(0).getId();
			session.put("lid", lid);
			setId(lid);
		} else {
			session.put("lid", getId());
		}
		session.put("lwjj", lwjjService.loadLwjj(id));
		return "lwjjList";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
