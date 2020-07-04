# Online Voting System

  - Java Spring을 기반으로 하는 온라인 투표 웹 구현
  
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
  
## QA

  - boardform.html postform.html '목록으로' 뒤로가기 방식 수정