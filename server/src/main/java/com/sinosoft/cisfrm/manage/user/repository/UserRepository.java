package com.sinosoft.cisfrm.manage.user.repository;

import com.sinosoft.cisfrm.manage.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * Created by Dawn on 16/2/20.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")}, forCounting = false)
    User findByUsername(String username);
}
