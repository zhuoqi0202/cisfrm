package com.sinosoft.cisfrm.manage.user.repository;

import com.sinosoft.cisfrm.manage.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dawn on 16/2/20.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);
}
