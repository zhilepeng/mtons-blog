/**
 * 
 */
package mblog.persist.dao;


import mtons.modules.persist.Dao;
import mblog.persist.entity.ValidateUserRstPo;

/**
 * @author C
 *
 */
public interface ValidateUserRstDao extends Dao<ValidateUserRstPo> {
	/**
	 * 获取授权码
	 * @param validateCode
	 * @return
	 */
	ValidateUserRstPo getUserRstPo(String validateCode);
	/**
	 * 保存注册用户验证信息
	 * @param vPo
	 */
	void saveUserRstPo(ValidateUserRstPo vPo);
}
