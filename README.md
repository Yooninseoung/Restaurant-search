## 원주 지역 "식당 검색"

**목차**
> 1. 프로젝트 소개
> 2. 기술 스택
> 3. 조원 & 역할 분담
> 4. 구현 기능
> 5. ERD
> 6. 시연 영상상

<br/><br/>

### 1. 프로젝트 소개

강원 특별자치도 원주시의 **식당 정보**(약 3000개)를 이용해 사용자에게 서비스를 제공합니다.
 - 사용자들은 자신의 맛집 혹은 방문한 식당에 대해 리뷰를 남길 수 있습니다.
 - 사용자들이 뽑은 TOP21 맛집과 좋아요 순으로 나열한 식당 정보를 통해 실패없는 맛집 선택이 가능합니다.
   
   이외에도 모든 식당을 볼수 있는 화면과 검색 기능을 제공하며, 소소한 재미를 주는 메뉴 추천 버튼 등 다양한 기능들이 있습니다.

 - 사용자들에게 자유로운 소통이 가능한 **자유 게시판** 기능을 제공합니다. 서로 잘모르는 이웃과 소통의 창구가 되고 다양한 정보를 얻을 수 있습니다.
<br/>

### 2. 기술 스택

#### - 개발환경
IDE: IntelliJ IDEA Community / Spring Boot 3.3.1 / JDK 17 / mysql 5.1.41 / Lombok / Spring Data JPA / Spring Web / Thymeleaf

#### - 기술 스택
JAVA / JavaScript / HTML5 / CSS / JavaScript / mysql

<br/>

### 3. 조원 & 역할 분담
|팀장|역할|
|--|--|
|**윤인성**|원주시 식당정보를 가져와 DB에 저장, 식당 데이터베이스 설계, 식당 관련 기능 제작, 뷰 화면 제작, 관리자 기능|


|팀원|역할|
|--|--|
|김민수|자유 게시판 데이터베이스 설계, 자유게시판 기능 제작, 뷰 화면 제작|

<br/>

### 4. 구현 기능

1. 식당과 관련된 기능

- 모든 식당에 대한 정보를 제공합니다.
- 좋아요, 즐겨 찾기를 기준으로 식당 정보를 정렬해서 제공합니다.
- 특정 식당에 대한 검색을 지원합니다.
- 각 식당이 받은 좋아요 수와 싫어요 수, 평점을 포함한 정보를 사용자에게 제공합니다.
- 사용자에게 랜덤 메뉴를 추천합니다.
- 각 식당은 자신이 받은 리뷰를 사용자에게 보여줍니다.

---

2. 회원과 관련된 기능

- 회원 가입과 로그인 기능을 지원합니다.
- 각 식당에 대해 좋아요와 싫어요, 평점을 이용해 평가할 수 있습니다.
- 즐겨찾기 기능을 통해 원하는 식당의 정보를 저장 후 확인할 수 있습니다.
- 각 식당에 대한 리뷰와 평점, 사진을 작성할 수 있습니다.
- 좋아요와 싫어요, 즐겨 찾기를 취소할 수 있습니다.

---

3. 관리자 기능

- 특정 식당을 조회해 식당 정보에 접근해 데이터를 수정할 수 있습니다.
- 특정 식당에 작성되어있는 리뷰를 삭제할 수 있습니다.
- 데이터베이스에 없는 식당을 직접 추가할 수 있습니다.
- 회원의 아이디를 통해 특정 회원을 조회할 수 있고 정보 수정 및 삭제를 할 수 있습니다.
- 최신순과 신고 순으로 자유게시판을 정렬할 수 있습니다.
- 특정 게시글에 접근하여 글을 삭제하거나 댓글을 삭제할 수 있습니다.
<br/><br/><br/><br/>

### 5. ERD ###

<img src="https://github.com/user-attachments/assets/bcd12217-03eb-4f7c-891d-956785db733c"  width="900" height="600" align="center"/>
<br/><br/><br/>

### 6. 스크린 샷 ###
<br/>
<div align="center">
        <img src="https://github.com/user-attachments/assets/b0e783ff-ea38-4028-8bd0-e2c29c003446" width="900" height="600" >
        <img src="https://github.com/user-attachments/assets/8c341377-40d3-49e2-8e85-1ea7137e147e" width="700" height="600" >
        <img src="https://github.com/user-attachments/assets/effd1861-346f-4f3d-bf12-6d4e36354188" width="700" height="600" >
        <img src="https://github.com/user-attachments/assets/b15593f5-30bc-47b1-bf53-71ec77598df4" width="700" height="600" >
        <img src="https://github.com/user-attachments/assets/93f60466-2ced-4e81-bf3e-2ece37ef7c05" width="700" height="600" >
        <img src="https://github.com/user-attachments/assets/f016da66-0643-4c48-af73-6bbb08f356c6" width="700" height="600" >
        <img src="https://github.com/user-attachments/assets/aee90f58-e457-4299-bb35-88d8f7fa068a" width="700" height="600" >
</div>




