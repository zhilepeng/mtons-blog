/**
 * 
 */
package mblog.persist.dao.impl;


import mtons.modules.persist.impl.DaoImpl;
import mblog.persist.dao.ValidateUserRstDao;
import mblog.persist.entity.ValidateUserRstPo;

/**
 * @author C
 * 
 */
public class ValidateUserRstDaoImpl extends DaoImpl<ValidateUserRstPo> implements ValidateUserRstDao {

	protected ValidateUserRstDaoImpl() {
		super(ValidateUserRstPo.class);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ValidateUserRstPo getUserRstPo(String validateCode) {
		return super.findUniqueBy("validateCode", validateCode);
	}

	@Override
	public void saveUserRstPo(ValidateUserRstPo vPo) {
		super.save(vPo);
	}
	
}
