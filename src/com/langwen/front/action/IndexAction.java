package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.bean.Zsgdlg;
import com.langwen.admin.bean.Zsswny;
import com.langwen.admin.service.BannerService;
import com.langwen.admin.service.BjtjService;
import com.langwen.admin.service.YqljService;
import com.langwen.admin.service.ZsgdlgService;
import com.langwen.admin.service.ZsgdwkService;
import com.langwen.admin.service.ZsgzjyService;
import com.langwen.admin.service.ZsjsjyService;
import com.langwen.admin.service.ZsswnyService;
import com.langwen.admin.service.ZszzjyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	Map<String, Object> session = ActionContext.getContext().getSession();

	private BannerService bannerService = new BannerService();
	private BjtjService bjtjService = new BjtjService();
	private ZsgdlgService zsgdlgService = new ZsgdlgService();
	private ZsgdwkService zsgdwkService = new ZsgdwkService();
	private ZsswnyService zsswnyService = new ZsswnyService();
	private ZsgzjyService zsgzjyService = new ZsgzjyService();
	private ZszzjyService zszzjyService = new ZszzjyService();
	private ZsjsjyService zsjsjyService = new ZsjsjyService();
	private YqljService yqljService = new YqljService();

	public String index() {
		session.put("frontBanner", bannerService.pageBanner(1));
		session.put("frontBjtj", bjtjService.pageBjtj(1));
		session.put("frontZsgdlg", zsgdlgService.pageZsgdlg(1));
		session.put("frontZsgdwk", zsgdwkService.pageZsgdwk(1));
		session.put("frontZsswny", zsswnyService.pageZsswny(1));
		session.put("frontZsgzjy", zsgzjyService.pageZsgzjy(1));
		session.put("frontZszzjy", zszzjyService.pageZszzjy(1));
		session.put("frontZsjsjy", zsjsjyService.pageZsjsjy(1));
		session.put("frontYqlj", yqljService.pageYqlj(1));
		return SUCCESS;
	}
}
