package com.mysite.sbb.domain.user.repository;

import com.mysite.sbb.domain.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
}
