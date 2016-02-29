package com.sinosoft.cisfrm.manage.user.repository;

import com.sinosoft.cisfrm.manage.user.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Dawn on 16/2/22.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r.moduleId,r.name from Role r where r.status=:status")
    List<Object[]> findByStatus(@Param("status") int status);
}
