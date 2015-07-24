/**
 * 
 */
package mblog.commons.data;

import mtons.modules.pojos.UserProfile;

/**
 * @author langhsu
 *
 */
public class AccountProfile extends UserProfile {
	private static final long serialVersionUID = 1748764917028425871L;
	
	private int roleId;
	
	public AccountProfile(long id, String username) {
		super(id, username);
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
