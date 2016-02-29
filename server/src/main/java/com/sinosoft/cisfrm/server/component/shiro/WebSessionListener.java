package com.sinosoft.cisfrm.server.component.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Dawn on 16/2/28.
 */
@Component
public class WebSessionListener extends SessionListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(WebSessionListener.class);

    @Override
    public void onStart(Session session) {
        log.info("start>>>>>>>>>>>" + session.toString());
        super.onStart(session);
    }

    @Override
    public void onExpiration(Session session) {
        log.info("expiration>>>>>>" + session.toString());
        super.onExpiration(session);
    }

    @Override
    public void onStop(Session session) {
        log.info("stop>>>>>>>" + session.toString());
        super.onStop(session);
    }
}
