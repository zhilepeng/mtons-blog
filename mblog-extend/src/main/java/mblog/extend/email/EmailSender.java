/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.email;

import java.util.Map;

/**
 * @author langhsu on 2015/8/14.
 */
public interface EmailSender {

    void to(String template, String address, String subject, Map<String, Object> data);
}
