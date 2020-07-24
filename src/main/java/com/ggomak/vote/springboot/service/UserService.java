package com.ggomak.vote.springboot.service;

import com.ggomak.vote.springboot.domain.Candidate;
import com.ggomak.vote.springboot.domain.User;
import com.ggomak.vote.springboot.domain.dto.CandidateRegDto;
import com.ggomak.vote.springboot.domain.dto.UserRoleChangeDTO;
import com.ggomak.vote.springboot.domain.enums.RoleType;
import com.ggomak.vote.springboot.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.vote.springboot.repository.CandidateRepository;
import com.ggomak.vote.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.h2.api.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private String filePath = "/Users/ggomak/Desktop/VoteImgDir/";

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

    public Page<Candidate> findCandidateList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return candidateRepository.findAll(pageable);
    }

    @Transactional
    // 후보자 권한 설정
    public Long roleChange(UserRoleChangeDTO requestDto){

        User user = userRepository.findById(requestDto.getIdx()).get();

        user.updateRole(RoleType.valueOf(requestDto.getValue()));

        return user.getIdx();
    }

    @Transactional
    public Long roleChangeAllVoter(UserRoleChangeDTO requestDto){

        userRepository.updateAllRole(RoleType.valueOf(requestDto.getValue()));

        return requestDto.getIdx();
    }

    @Transactional
    public void updateIsVoted(String studentId){
        userRepository.updateIsVoted(studentId);
    }

    @Transactional
    public Long registCandidate(CandidateRegDto requestDto, MultipartFile poster, ArrayList<MultipartFile> picture){

        if(!requestDto.getCandidate3().equals("")) {
            candidateRepository.save(Candidate.builder()
                    .teamName(requestDto.getTeamName())
                    .department(requestDto.getDepartment())
                    .user1(userRepository.findByStudentId(requestDto.getCandidate1()).get())
                    .user2(userRepository.findByStudentId(requestDto.getCandidate2()).get())
                    .user3(userRepository.findByStudentId(requestDto.getCandidate3()).get())
                    .imgPath(filePath)
                    .thumbnail1(requestDto.getTeamName() + '_' + requestDto.getCandidate1() + ".jpg")
                    .thumbnail2(requestDto.getTeamName() + '_' + requestDto.getCandidate2() + ".jpg")
                    .thumbnail3(requestDto.getTeamName() + '_' + requestDto.getCandidate3() + ".jpg")
                    .pledgePoster("Pledge_" + requestDto.getTeamName() + ".jpg")
                    .build()
            );
            userRepository.updateRole(requestDto.getCandidate1(), RoleType.CANDIDATE);
            userRepository.updateRole(requestDto.getCandidate2(), RoleType.CANDIDATE);
            userRepository.updateRole(requestDto.getCandidate3(), RoleType.CANDIDATE);

            fileUpload(picture.get(0), filePath + requestDto.getTeamName() + '/', requestDto.getTeamName() + '_' + requestDto.getCandidate1());
            fileUpload(picture.get(1), filePath + requestDto.getTeamName() + '/', requestDto.getTeamName() + '_' + requestDto.getCandidate2());
            fileUpload(picture.get(2), filePath + requestDto.getTeamName() + '/', requestDto.getTeamName() + '_' + requestDto.getCandidate3());
        }
        else{
            candidateRepository.save(Candidate.builder()
                    .teamName(requestDto.getTeamName())
                    .department(requestDto.getDepartment())
                    .user1(userRepository.findByStudentId(requestDto.getCandidate1()).get())
                    .user2(userRepository.findByStudentId(requestDto.getCandidate2()).get())
                    .imgPath(filePath)
                    .thumbnail1(requestDto.getTeamName() + '_' + requestDto.getCandidate1() + ".jpg")
                    .thumbnail2(requestDto.getTeamName() + '_' + requestDto.getCandidate2() + ".jpg")
                    .pledgePoster("Pledge_" + requestDto.getTeamName() + ".jpg")
                    .build()
            );

            userRepository.updateRole(requestDto.getCandidate1(), RoleType.CANDIDATE);
            userRepository.updateRole(requestDto.getCandidate2(), RoleType.CANDIDATE);
            fileUpload(picture.get(0), filePath + requestDto.getTeamName() + '/', requestDto.getTeamName() + '_' + requestDto.getCandidate1());
            fileUpload(picture.get(1), filePath + requestDto.getTeamName() + '/', requestDto.getTeamName() + '_' + requestDto.getCandidate2());
        }

        fileUpload(poster, filePath + requestDto.getTeamName() + '/', "Pledge_" + requestDto.getTeamName());

        return null;
    }

    @Transactional
    public Object getUser(String studentId){

        User user = userRepository.findByStudentId(studentId).get();

        if(user.getRoleType() == RoleType.CANDIDATE){
            return new ResponseEntity<>("Already Regist" , HttpStatus.BAD_REQUEST);
        }

        return user;
    }

    public void fileUpload(MultipartFile multipartFile, String filePath, String fileName){
        File dirCheck = new File(filePath);

        if(!dirCheck.isDirectory()){    // 디렉토리 유무를 체크하고 없을경우 생성
            dirCheck.mkdir();
        }

        File file = new File(filePath + fileName + ".jpg");

        try {
            multipartFile.transferTo(file); // 파일 저장
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long regSignature(SessionUser user, MultipartFile image){
        fileUpload(image, filePath+"signature/", user.getStudentId() + "_" + LocalDateTime.now());

        return userRepository.findByStudentId(user.getStudentId()).get().getIdx();
    }
}
