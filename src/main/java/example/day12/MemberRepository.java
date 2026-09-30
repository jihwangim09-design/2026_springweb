package example.day12;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity , Long >{
    // JPA 사용시 기본적인 CRUD 메소드제공, save findAll findById deleteById 등등
    // * 메소드쿼리(망명규칙) 또는 네이티브쿼리 추가 정의
    // findByXXX : XXX에 필드명을 넣어서 조회 추상메소드 만들기 , 주의할점: 카멜표기법!! findBy속성명and속성명
    MemberEntity findByMid(String mid); // mid를 이용하여 엔티티 조회
    Optional<MemberEntity> findByMname(String mname); // mname 일치하면 엔티티 조회
}
