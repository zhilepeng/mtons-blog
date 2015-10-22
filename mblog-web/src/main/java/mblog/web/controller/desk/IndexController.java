/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.desk;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.base.lang.Consts;
import mblog.web.controller.BaseController;

/**
 * @author langhsu
 *
 */
@Controller
public class IndexController extends BaseController{
	
	@RequestMapping(value= {"/", "/index"})
	public String root(Integer pn, String ord, ModelMap model) {
		String order = Consts.order.NEWEST;
		if (StringUtils.isNotBlank(ord)) {
			order = ord;
		}
		model.put("pn", pn);
		model.put("ord", order);
		return getView(Views.INDEX);
	}

}
