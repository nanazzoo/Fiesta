![header](https://capsule-render.vercel.app/api?type=Waving&color=timeGradient&height=250&section=header&text=Fiesta&fontSize=60&animation=twinkling&fontColor=ffffff&fontAlign=80)

# :pushpin: [Fiesta](http://146.56.188.235:8080/)
>SNS(Instagram)을 벤치마킹하여 Fiesta만의 고유의 색을 담은 SNS 페이지로 재해석하였다.
>
>[Fiesta Demo 바로가기](http://146.56.188.235:8080/)

</br>
</br>

## 1. 제작 기간 & 참여 인원
- 2022년 10월 18일 ~ 12월 10일
- 5인 팀 프로젝트
- 


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

</br></br>

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">



### 4.1. 전체 흐름
  
<img src="/spring.png">


### 4.2. 사용자 요청

- 사용자가 로그인을 하면 메인 페이지로 Redirect 합니다.



### 4.3. Controller![controller](C:\Users\bboya\OneDrive\바탕 화면\controller.png)

- 요청 처리
  - Controller에서 화면 단에서 넘어온 요청을 Service 계층에 위임합니다.
- 결과 응답
  - Service 계층에서 넘어온 결과(map)을 model에 저장한 후 메인 페이지로 Forward 합니다.



### 4.4. Service
  
<img src="/service.png">


- 게시물 목록 조회
  - 조회할 게시물의 수 조회해 옵니다.
  - 게시물 수와 현재 페이지 번호를 매개변수로 하여 Pagination 객체를 생성합니다.
  - 여기서 현재 페이지가 1로 고정된 것은 JSP를 통해서 보여질 페이지만 불러오기 때문입니다.
  - Pagination 객체와 회원 번호를 매개변수로 하여 게시물 목록을 조회합니다.



### 4.5. Repository

<img src="/repository.png"> 

- pagination 객체를 이용하여 조회해 올 게시물의 시작점을 계산합니다.
- mybatis의 기능을 이용하기 위해 rowBounds 객체를 생성하고 
- mapper를 호출합니다.



### 4.6. Mapper

<img src="/mapper.png"> 

- 로그인 한 사용자의 메인 페이지에 출력할 게시물을 불러오는 SQL문 입니다.
- 사용자가 팔로우한 회원 및 팔로우한 해시태그 연관 게시물을 불러옵니다.
- 조회해 온 게시물은 다시 Repository - Service - Controller를 거쳐 화면에 출력 됩니다.


</div>
</details>

</br></br>

## 5. 핵심 트러블 슈팅
### 5.1. 컨텐츠 필터와 페이징 처리 문제
- 저는 이 서비스가 페이스북이나 인스타그램 처럼 가볍게, 자주 사용되길 바라는 마음으로 개발했습니다.  
때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  
다양한 기준(카테고리, 사용자, 등록일, 인기도)의 게시물 필터 기능을 넣어서 이를 보완하고자 했습니다.

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

</br>

## 6. 그 외 트러블 슈팅

[추천 멤버 선정 기준 수정](https://github.com/FiestaUpdate/Fiesta/blob/main/troubleshooting/%EC%B6%94%EC%B2%9C%20%EB%A9%A4%EB%B2%84%20%EC%84%A0%EC%A0%95%20%EA%B8%B0%EC%A4%80.md)

</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
