package com.sinosoft.cisfrm.manage.component;

import com.sinosoft.cisfrm.manage.user.entity.Role;
import com.sinosoft.cisfrm.manage.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dawn on 16/2/29.
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            List<Object[]> roleIterable = roleRepository.findByStatus(1);
            redisTemplate.delete(Constants.ROLES_IN_REDIS);
            Iterator<Object[]> iterator = roleIterable.iterator();
            while (iterator.hasNext()) {
                Object[] objects = iterator.next();
                Role role = new Role();
                role.setModuleId(Long.valueOf(String.valueOf(objects[0])));
                role.setName(String.valueOf(objects[1]));
                redisTemplate.boundListOps(Constants.ROLES_IN_REDIS).leftPushAll(role);
            }
        }
    }
}
