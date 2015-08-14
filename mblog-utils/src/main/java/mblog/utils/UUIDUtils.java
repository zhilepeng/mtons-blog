package mblog.utils;


import java.util.UUID;

/**
 * 
 * @author C
 *
 */
public class UUIDUtils {
    
	public static String getUUID(){
		
		return UUID.randomUUID().toString();
	}
	
	public static String getUUID2String() {
		StringBuffer uuIdBuffer = new StringBuffer();
		String _uuid = getUUID();
		uuIdBuffer.append(_uuid.substring(0, 8));
		uuIdBuffer.append(_uuid.substring(9, 13));
		uuIdBuffer.append(_uuid.substring(14, 18));
		uuIdBuffer.append(_uuid.substring(19, 23));
		uuIdBuffer.append(_uuid.substring(24));
		return uuIdBuffer.toString();
	}

	public static String getUUID2String8(){
		StringBuffer uuIdBuffer = new StringBuffer();
		String _uuid = getUUID();
		uuIdBuffer.append(_uuid.substring(0, 8));
		return uuIdBuffer.toString();
	}
}
