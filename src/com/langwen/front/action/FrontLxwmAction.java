package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.LxwmService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontLxwmAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private LxwmService lxwmService = new LxwmService();

	private Integer id;

	public String lxwm() {
		session.put("lxwmList", lxwmService.allLxwm());
		// 根据id判断显示哪一个朗文简介项目
		if (id == null) {
			Integer lid = lxwmService.allLxwm().get(0).getId();
			session.put("lid", lid);
			setId(lid);
		} else {
			session.put("lid", getId());
		}
		session.put("lxwm", lxwmService.loadLxwm(id));
		return "lxwmList";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
