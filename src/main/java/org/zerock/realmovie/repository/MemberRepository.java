package org.zerock.realmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.realmovie.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {



}
