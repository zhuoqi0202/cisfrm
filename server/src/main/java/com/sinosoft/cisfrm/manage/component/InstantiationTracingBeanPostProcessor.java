package com.sinosoft.cisfrm.manage.component;

import com.sinosoft.cisfrm.manage.codetype.entity.CodeType;
import com.sinosoft.cisfrm.manage.codetype.repository.CodeTypeRepository;
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
    private CodeTypeRepository codeTypeRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            //clear
            clear();
            //roles init
            List<Object[]> roleIterable = roleRepository.findByStatus((byte) 1);
            Iterator<Object[]> iterator = roleIterable.iterator();
            while (iterator.hasNext()) {
                Object[] objects = iterator.next();
                Role role = new Role();
                role.setModuleId(Integer.valueOf(String.valueOf(objects[0])));
                role.setName(String.valueOf(objects[1]));
                redisTemplate.boundListOps(Constants.ROLES_IN_REDIS).leftPush(role);
            }
            //orgtype init
            List<CodeType> orgType = codeTypeRepository.findByCodeTypeAndValidStatus("Orgtype", "1");
            Iterator<CodeType> orgTypeIterator = orgType.iterator();
            while (orgTypeIterator.hasNext()) {
                redisTemplate.boundListOps(Constants.ORG_TYPE_IN_REDIS).leftPush(orgTypeIterator.next());
            }
            //cpytype init
            List<CodeType> cpyType = codeTypeRepository.findByCodeTypeAndValidStatus("CpyType", "1");
            Iterator<CodeType> cpyTypeIterator = cpyType.iterator();
            while (cpyTypeIterator.hasNext()) {
                redisTemplate.boundListOps(Constants.CPY_TYPE_IN_REDIS).leftPush(cpyTypeIterator.next());
            }
            //capital init
            List<CodeType> capitalType = codeTypeRepository.findByCodeTypeAndValidStatus("CapitalType", "1");
            Iterator<CodeType> capitalTypeIterator = capitalType.iterator();
            while (capitalTypeIterator.hasNext()) {
                redisTemplate.boundListOps(Constants.CAPITAL_TYPE_IN_REDIS).leftPush(capitalTypeIterator.next());
            }
        }
    }

    private void clear() {
        redisTemplate.delete(Constants.ROLES_IN_REDIS);
        redisTemplate.delete(Constants.ORG_TYPE_IN_REDIS);
        redisTemplate.delete(Constants.CPY_TYPE_IN_REDIS);
        redisTemplate.delete(Constants.CAPITAL_TYPE_IN_REDIS);
    }
}
