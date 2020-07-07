package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // User list 출력
    public Page<User> findUserList(Pageable pageable, String type, String value) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        switch(type) {
            //case "department" :
            //    return userRepository.findAllByDepartment(pageable, Department.computerScience);
            case "name" :
                return userRepository.findAllByName(pageable, value);
            case "studentId" :
                return userRepository.findAllByStudentId(pageable, value);
            case "all" :
                return userRepository.findAll(pageable);
            default :
                throw new IllegalArgumentException();
        }
    }

    // Vote List 출력
    public HashSet<String> findCandidateList() {

        List<User> userArrayList = userRepository.findAllByRoleType(RoleType.CANDIDATE);
        HashSet<String> hashSet = new HashSet<>();

        for(int i = 0; i < userArrayList.size(); i++){
            hashSet.add(userArrayList.get(i).getDepartment().getValue());
        }

        return hashSet;
    }

    // 후보자 권한 설정
    public Long candidateRegistration(Long idx){
        User user = userRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        user.updateCandidate();

        return idx;
    }
}
