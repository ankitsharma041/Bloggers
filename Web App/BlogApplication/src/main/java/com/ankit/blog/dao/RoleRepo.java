package com.ankit.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankit.blog.entities.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
