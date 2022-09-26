package com.si.familymemberapp.repositories;

import com.si.familymemberapp.domain.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

    List<FamilyMember> findAllByFamilyId(UUID familyId);
}
