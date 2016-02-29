package com.sinosoft.cisfrm.server.component.shiro;

import com.sinosoft.cisfrm.server.component.Constants;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dawn on 16/2/21.
 */
@Component
public class SessionDao extends EnterpriseCacheSessionDAO {
    @Autowired
    private RedisTemplate<String, Session> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        saveSession(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session != null) return session;
        session = redisTemplate.boundValueOps(new StringBuilder(Constants.SESSION_KEY_IN_REDIS).append(sessionId.toString()).toString()).get();
        if (session != null) super.update(session);
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.update(session);
        saveSession(session.getId(), session);
    }

    @Override
    protected void doDelete(Session session) {
        if (session != null) {
            redisTemplate.delete(new StringBuilder(Constants.SESSION_KEY_IN_REDIS).append(session.getId()).toString());
            super.delete(session);
        }
    }

    private Session saveSession(final Serializable id, final Session session) {
        final String key = new StringBuilder(Constants.SESSION_KEY_IN_REDIS).append(session.getId()).toString();
        redisTemplate.boundValueOps(key).set(session, session.getTimeout(), TimeUnit.MILLISECONDS);
        return session;
    }
}
