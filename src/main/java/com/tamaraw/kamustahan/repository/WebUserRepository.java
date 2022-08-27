package com.tamaraw.kamustahan.repository;

import com.tamaraw.kamustahan.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebUserRepository extends JpaRepository<WebUser, String> {

}
