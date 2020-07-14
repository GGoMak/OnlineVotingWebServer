# Online Voting System

  - Java Spring을 기반으로 하는 온라인 투표 웹 구현
  
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
학번 : 20150010 ~ 20150039
패스워드 : 123
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
  
## QA

  - boardform.html postform.html '목록으로' 뒤로가기 방식 수정
  - ~~후보자 등록 페이지의 소속 검색을 위한 ENUM Type Query 어노테이션 수정~~
  - 투표하기 페이지 디자인 추후 개선 필요  
  - DB에 유저 IP정보 추가
  
## 출처

  - 소스코드
    - 게시글 CRUD API : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 저)
    - 게시글 Pageable : 처음 배우는 스프링 부트 2(김영재 저)
    - 전자서명 javascript : 네이버 블로그(세 아이 아빠의 일상 기록)
    
  - 페이지 템플릿 : https://html5up.net/future-imperfect