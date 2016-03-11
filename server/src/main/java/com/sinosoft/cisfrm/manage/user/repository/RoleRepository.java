package com.sinosoft.cisfrm.manage.user.repository;

import com.sinosoft.cisfrm.manage.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Dawn on 16/2/22.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r.moduleId,r.name from Role r where r.status=:status")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Object[]> findByStatus(@Param("status") byte status);
}
