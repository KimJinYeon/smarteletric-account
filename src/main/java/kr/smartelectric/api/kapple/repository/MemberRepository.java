package kr.smartelectric.api.kapple.repository;

import kr.smartelectric.api.kapple.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmailEqualsAndIsMemberActiveFalse(String email);
    Boolean existsByEmailEqualsAndIsMemberActiveTrue(String email);
    Member getMemberByEmailEqualsAndIsMemberActiveFalse(String email);
    Optional<Member> getMemberByEmailEqualsAndIsMemberActiveTrue(String email);
    List<Member> getMembersByIsMemberActiveTrue();
    List<Member> getMembersByCustomerNumberIn(List<String> customerNumbers);
    Optional<Member> getMemberByEmailEquals(String email);

    Optional<Member> findByEmailEquals(String email);
}
