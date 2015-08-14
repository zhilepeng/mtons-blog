/**
 * 
 */
package mblog.persist.service;

import java.util.List;

import mtons.modules.pojos.Paging;
import mblog.data.Tag;
import mblog.data.ValidateUserRst;

/**
 * @author C
 *
 */
public interface ValidateUserRstService {
	
	/**
	 * 获取注册授权码信息
	 * @param validateCode
	 * @return
	 */
	ValidateUserRst getUserRst(String validateCode);
	/**
	 * 保存用户授权码信息
	 * @param vRst
	 */
	void saveUserRst(ValidateUserRst vRst);
}
