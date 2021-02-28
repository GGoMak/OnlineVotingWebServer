# Online Voting System

  - Java Spring을 기반으로 하는 온라인 투표 웹 구현
  - 대학교 학생회 투표를 목적으로 제작되었으며 학과별, 한 후보에 대해 투표를 진행하는 방식으로 구현되었습니다.
  후보가 여럿일 경우에 대한 경우는 추후에 추가구현 예정입니다.
  - 데모 영상 : https://youtu.be/9ExKqcbh_28
## 실행 방법

```$xslt
git clone https://github.com/GGoMak/OnlineVotingWebServer.git
gradle build
cd build/libs
java -jar Vote-1.0-SNAPSHOT.jar
```

  - 테스트용 관리자ID
```$xslt
학번 : 20150000
패스워드 : 123
```

  - 테스트용 유저ID
```$xslt
학번 : 20150010 ~ 20150179 | 20160010 ~ 20160179 | 20170010 ~ 20170179 | 20180010 ~ 20180179
패스워드 : 123
```

## 설정

  - src/main/java/com/ggomak/vote/springboot/oauthsecurity/config/WebConfig.java에서 이미지 경로 지정
```$xslt
registry.addResourceHandler("/images/**").addResourceLocations("file:///{이미지 폴더 경로}").setCachePeriod(0);
```

  - src/main/java/com/ggomak/vote/springboot/service/UserService.java에서 filePath 경로 지정
```$xslt
private String filePath = "{이미지 폴더 경로}";
```  

## v1.0

  - 메인페이지 구현
  - 인 메모리 DB(h2) 연동
  - 게시판 뷰 구현(공지사항, 자유게시판)
  
## v1.1 

  - 게시판 Pagination 구현
  - 중복되는 html 소스 분리(header.html, menu.html)
  - 게시판 html 소스 수정(Redirection)
  - pagination 스타일 변경으로 이한 main.css 수정
  
## v1.2

  - 게시글 작성 및 읽기를 위한 페이지 구현(postform.html, boardform.html)
  - 게시글 CRUD 위한 API 구현
  - ajax를 이용하여 게시글 정보 전달(board.js)
  - User DB 추가
  - 로그인 및 로그아웃 구현
  - Spring Security 설정(SecurityConfig.java, WebConfig.java)
  - SessionUser DTO 추가 및 편의성을 위한 LoginUser 어노테이션 구현
  
## v1.2.1

  - 로그인 폼 디자인 개선 및 유효성 검사 추가
  
## v1.3

  - '투표하기' 페이지 구성
  - 후보자 등록 페이지 및 User DB 조회 및 검색 기능 추가(관리자만 이용가능)
  - 선거 공약(포스터) 열람 페이지 구현
  
## v1.4

  - 전사서명 기능 추가(javascript 구현)
  - 후보자 리스트 및 투표 페이지 구성
  - 투표하기 API 구현
  
## v1.4.1

  - 투표 페이지 디자인 수정
  
## v1.4.2

  - 중복투표방지 기능 추가
  - 유저 권한 설정 기능 추가
  - Vote DB IP주소 항목 추가
  
## v1.4.3

  - 관리자와 후보자를 제외한 유저 전체를 한번에 투표자로 변경하는 기능 추가
  
## v1.5.0

  - 후보자 등록 기능 추가
    - 유저 검색을 통한 후보자 추가
    - 후보자 사진 업로드
    - 공약 포스터 업로드
    - 후보자 전체 목록 출력
    
## v1.5.1

  - 공약 포스터 및 후보자 사진 접근 문제로 인한 이미지 외부 경로 설정
    - WebConfig ResourceHandler 추가
  - 후보자 등록 예외처리 추가
  
## v1.5.2

  - 전자서명 저장 기능 추가
    - signature 폴더 '학번_시간.jpg'의 형태로 저장
    
## v1.6.0

  - 투표결과 페이지 및 api 추가
    - Google Chart
    - 시간대별 전체 투표수, 학년별 투표수, 학과별 투표수 열람 가능
    
## v1.6.1

  - 테스트용 데이터 추가 생성
    - 유저 학년별로 180명씩 생성
    - 후보는 컴퓨터학부, 철학과, 회계학과에서 한 팀씩 총 3팀
    - 투표는 학년별로 18명씩 총 72명 진행
    - 학과별로는 총 24명 진행(16명 찬성, 8명 반대)
    
## v1.6.2

  - 투표 결과 조회 값을 '투표수' -> '투표율'로 변경
  - 학과별 찬성, 반대, 미투표 결과보기 추가
  
## v1.6.3

  - 후보 등록시 이미 등록된 후보 예외처리 추가
  - 투표결과 접근 예외처리 추가('GUEST'는 접근 불가)
  - 로그인시 오늘 날짜 표시
  - GUEST의 투표하기 접근 차단
  
## v1.6.4

  - 회원 관리 역할 지정 시 request 오류 수정
  - Spring Security 권한 수정
  - UserRepository 쿼리문 수정
  
## QA

  - boardform.html postform.html '목록으로' 뒤로가기 방식 수정
  - ~~후보자 등록 페이지의 소속 검색을 위한 ENUM Type Query 어노테이션 수정~~
  - 투표하기 페이지 디자인 추후 개선 필요  
  - DB에 유저 IP정보 추가
  - ~~Image 업로드 시 접근 문제 발생~~
  - 투표결과 조회를 위한 DB 설계 정리 필요
  - 후보등록시 없는 학번을 입력할 경우 예외처리 필요
  
## 출처

  - 소스코드
    - 게시글 CRUD API : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 저)
    - 게시글 Pageable : 처음 배우는 스프링 부트 2(김영재 저)
    - 전자서명 javascript : 네이버 블로그(세 아이 아빠의 일상 기록)
    
  - 페이지 템플릿 : https://html5up.net/future-imperfect
