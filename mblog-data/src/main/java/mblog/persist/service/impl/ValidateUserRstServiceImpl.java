/**
 * 
 */
package mblog.persist.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.ValidateUserRst;
import mblog.persist.dao.ValidateUserRstDao;
import mblog.persist.entity.ValidateUserRstPo;
import mblog.persist.service.ValidateUserRstService;

/**
 * @author C
 *
 */
public class ValidateUserRstServiceImpl implements ValidateUserRstService {
	@Autowired
	private ValidateUserRstDao validateUserRstDao;
	@Override
	@Transactional(readOnly=true)
	public ValidateUserRst getUserRst(String validateCode) {
		ValidateUserRstPo vRstPo = validateUserRstDao.getUserRstPo(validateCode);//获取授权码信息
		ValidateUserRst vRst = null;
		if (null !=vRstPo) {
			vRst = new ValidateUserRst();
			vRst.setCreateTime(vRstPo.getCreateTime());
			vRst.setType(vRstPo.getType());
			vRst.setUserId(vRstPo.getUserId());
			vRst.setExpireTime(vRstPo.getExpireTime());
			vRst.setId(vRstPo.getId());
		}
		return vRst;
	}
	@Override
	@Transactional
	public void saveUserRst(ValidateUserRst vRst) {
		ValidateUserRstPo vPo =new ValidateUserRstPo();
		BeanUtils.copyProperties(vRst, vPo);
		validateUserRstDao.saveUserRstPo(vPo);
	}
	
	

}
