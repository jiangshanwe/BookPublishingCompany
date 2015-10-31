package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.RczpService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontRczpAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private RczpService rczpService = new RczpService();

	private Integer id;

	public String rczp() {
		session.put("rczpList", rczpService.allRczp());
		// ����id�ж���ʾ��һ�����ļ����Ŀ
		if (id == null) {
			Integer lid = rczpService.allRczp().get(0).getId();
			session.put("lid", lid);
			setId(lid);
		} else {
			session.put("lid", getId());
		}
		session.put("rczp", rczpService.loadRczp(id));
		return "rczpList";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
