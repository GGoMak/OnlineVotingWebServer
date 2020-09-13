package com.ggomak.vote.springboot.repository;

import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.Vote;
import com.ggomak.vote.springboot.domain.enums.Department;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByStudentId(String studentId);

    List<User> findAllByRoleType(RoleType roleType);

    @Query("Select c from User c where c.department like %:department%")
    Page<User> findAllByDepartment(Pageable pageable, Department department);

    @Query("Select c from User c where c.name like %:name%")
    Page<User> findAllByName(Pageable pageable, String name);

    @Query("Select c from User c where c.studentId like %:studentId%")
    Page<User> findAllByStudentId(Pageable pageable, String studentId);

    @Modifying
    @Query("Update User c set c.roleType = :roleType where c.roleType = 'GUEST'")
    void updateAllRole(RoleType roleType);

    @Modifying
    @Query("Update User c set c.roleType = :roleType where c.studentId = :studentId")
    void updateRole(String studentId, RoleType roleType);

    @Modifying
    @Query("Update User c set c.isVoted = true where c.studentId = :studentId")
    void updateIsVoted(String studentId);

    @Query("select count(c) from User c where c.grade = :grade and c.isVoted = true")
    long countByVoted(Long grade);

    @Query("select count(c) from User c where c.roleType = 'GUEST'")
    long countAll();

    @Query("select count(c) from User c where c.grade = :grade")
    long countByGrade(Long grade);

    @Query("select count(c) from User c where c.department = :department")
    long countByDepartment(Department department);
}
