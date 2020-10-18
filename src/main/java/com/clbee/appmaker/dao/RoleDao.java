package com.clbee.appmaker.dao;

import com.clbee.appmaker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}