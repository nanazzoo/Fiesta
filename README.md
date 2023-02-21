![header](https://capsule-render.vercel.app/api?type=Waving&color=timeGradient&height=250&section=header&text=Fiesta&fontSize=60&animation=twinkling&fontColor=ffffff&fontAlign=80)

# :pushpin: [Fiesta](http://146.56.188.235:8080/)
>SNS(Instagram)을 벤치마킹하여 Fiesta만의 고유의 색을 담은 SNS 페이지로 재해석하였다.
>
>[Fiesta Demo 바로가기](http://146.56.188.235:8080/)
>(Test Account ID: user01@naver.com PW: pass01!!)

</br>
</br>

## 1. 제작 기간 & 참여 인원
- 2022년 10월 18일 ~ 12월 10일
- 5인 팀 프로젝트
- **역할**: **팀장**(프론트, 백엔드)
- **구현 기능**
   - 게시글 상세 조회 및 삭제
   - 좋아요, 북마크
   - 댓글 작성 및 삭제(대댓글)
   - 좋아요 수 숨기기, 댓글 기능 사용 안함
   - 언급 및 해시태그 자동 완성
   - 신고, 해당 게시물로 이동, 링크 복사 등
   - 웹소켓을 이용한 알림 기능 구현
- **기여도**: 설계 단계에서 DB, 화면 구현 부분에 적극적으로 참여하여 설계를 주도하였고 
         백엔드 부분에서 팀원이 어려워 하는 부분을 도와주거나 함께 해결해 주었습니다.


</br></br>


</br></br>

  
## 2. 사용 기술

<div align="center">
  
### **Back-end**
<img src="https://img.shields.io/badge/Java11-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/Spring5.3.14-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Oracle21C-F80000?style=for-the-badge&logo=oracle&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/Apache Tomcat9.0-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
    <img src="https://img.shields.io/badge/Apache Maven-C71A36?style=for-the-badge&logo=ApacheMaven&logoColor=white">
    <img src="https://img.shields.io/badge/Spring Sequrity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">

### **Front-end**
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 

</div>

</br></br>

## 3. ERD 설계

<img src="/SNS 프로젝트.png">


</br></br>

## 4. 핵심 기능

이 서비스의 핵심 기능은 한 페이지에 사용자가 팔로우하고 있는 회원 및 해시태그가 담긴 게시물을 모두 상세 조회 할 수 있다는 것입니다. 
사용자가 로그인만 하면 메인 페이지로 이동하고, JSP를 통해 10 개의 게시물이 출력 됩니다.  
무한 스크롤을 구현하여 스크롤만 내리면 계속해서 게시물을 이어 볼 수 있습니다.

</br></br>

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">



</br></br>

### 4.1. 전체 흐름
  
<img src="/spring.png">


</br></br>

### 4.2. 사용자 요청

- 사용자가 로그인을 하면 메인 페이지로 Redirect 합니다.


</br></br>

### 4.3. Controller

<img src="/controller.png">

- 요청 처리
  - Controller에서 화면 단에서 넘어온 요청을 Service 계층에 위임합니다.
- 결과 응답
  - Service 계층에서 넘어온 결과(map)을 model에 저장한 후 메인 페이지로 Forward 합니다.


</br></br>

### 4.4. Service
  
<img src="/service.png">


- 게시물 목록 조회
  - 조회할 게시물의 수 조회해 옵니다.
  - 게시물 수와 현재 페이지 번호를 매개변수로 하여 Pagination 객체를 생성합니다.
  - 여기서 현재 페이지가 1로 고정된 것은 JSP를 통해서 보여질 페이지만 불러오기 때문입니다.
  - Pagination 객체와 회원 번호를 매개변수로 하여 게시물 목록을 조회합니다.


</br></br>


### 4.5. Repository

<img src="/repository.png"> 

- pagination 객체를 이용하여 조회해 올 게시물의 시작점을 계산합니다.
- mybatis의 기능을 이용하기 위해 rowBounds 객체를 생성하고 
- mapper를 호출합니다.


</br></br>

### 4.6. Mapper

<img src="/mapper.png"> 

- 로그인 한 사용자의 메인 페이지에 출력할 게시물을 불러오는 SQL문 입니다.
- 사용자가 팔로우한 회원 및 팔로우한 해시태그 연관 게시물을 불러옵니다.
- 조회해 온 게시물은 다시 Repository - Service - Controller를 거쳐 화면에 출력 됩니다.


</br></br>
</div>
</details>

</br></br>

</br></br>



## 5. 트러블 슈팅

[추천 멤버 선정 기준 수정](https://github.com/FiestaUpdate/Fiesta/blob/main/troubleshooting/%EC%B6%94%EC%B2%9C%20%EB%A9%A4%EB%B2%84%20%EC%84%A0%EC%A0%95%20%EA%B8%B0%EC%A4%80.md)

</br>
