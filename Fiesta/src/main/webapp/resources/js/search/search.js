// 검색창이라, 모든 페이지 아래에 들어가니, 아이디,클래스명 등 겹치지 않게 주의!
// 요소 생성 - 조립
// ajax 연결
                // append(요소) : 마지막 자식으로 추가
                // prepend(요소) : 첫 번째 자식으로 추가
                // after(요소) : 다음(이후)에 추가
                // before(요소) : 이전에 추가

                // 요소.setAttribute("속성명", "속성값")
                // 요소.removeAttribute("속성명")
    


// 해시태그 팔로우 버튼
const followHashtagBtn = document.getElementById("followHashtagBtn");

// 바뀐 상태가 고정되어 있어야 함.
// 팔로우 버튼을 누른 상태라면 btnClicked
// 팔로우 버튼을 안 누른 상태라면 followBtn
followHashtagBtn.classList.add("followBtn");
followHashtagBtn.classList.remove("btnClicked");



followHashtagBtn.addEventListener("click", () => {

    $.ajax({
        url: "/followHashtag",
        data:{"searchInput" : searchInput.value},
        type: "GET",
        success: (result) => {
            if(result > 0){
                followHashtagBtn.classList.add("btnClicked");
                followHashtagBtn.classList.remove("followBtn");
            }
        },
        error: (result) => {
            console.log("해시태그 팔로우 오류");
        }

    })
});


                
// (()=>{
    // const searchInput = document.getElementById("searchInput");

//     // ajax쓰기
//     $.ajax({
//         url: "/main/search/accountList",
//         data: {"searchInput" : searchInput.value},
//         type: "GET",
//         success: accountList => {
//             console.log(accountList); 
            

// /*
//             // 관련 있는 계정 (프로필이미지, 닉네임)
//             const accountContainer = document.getElementsByClassName("account-container")[0];
//             const divAccountGroup = document.getElementsByClassName("account-Group");
//             const aProfileImage = document.getElementsByClassName("profileImages");
//             const spanFollowButton = document.getElementsByClassName("follow-button-small");
//             const aFollow = document.getElementById("aFollow");
//                 <div class="account-Group">
//                   <a href="/feed/${loginMember.memberNickname}" class="profileImages">
//                     <img src="/resources/images/profile/profile.jpg">
//                   </a>
//                   <a href="/feed/${loginMember.memberNickname}" class="profileNickname">
//                     ${accountList.memberNickname}
//                   </a>
//                   <span class="follow-button-small">
//                     <a href="">팔로우</a>
//                   </span>
//                 </div>
// */


//             for(let member of accountList) {   //for(let member of memberList)
                
//                 // 프로필이미지
//                 const imgProfileImage = document.createElement("img");
//                 imgProfileImage.src = member.imgPath;

                
//                 // 닉네임(a태그)
//                 const aProfileNickname = document.createElement("a");
//                 aProfileNickname.innerText = member.memberNickname;
                
                

//                 accountContainer.append(divAccountGroup);
//                 divAccountGroup.append(aProfileImage, aProfileNickname, spanFollowButton)
//                 aProfileImage.after(aProfileNickname);
//                 aProfileImage.append(imgProfileImage);
//             }

//         },
//         error: () => {console.log("검색 실패");}
//     })
  
// })();  
    
    
/*

            // 관련 계정, 게시글 수
            const totalNumber = document.getElementsByClassName("total-number")[0];
            // totalNumber.innerText = "안녕하세요";
        
            const spanSearchAccountTotal = document.createElement("span");
            const spanSearchBoardTotal = document.createElement("span");
            
            totalNumber.append(spanSearchAccountTotal, spanSearchBoardTotal);
        
            // spanSearchAccountTotal.innerText = "${result}";
            spanSearchBoardTotal.innerText = "게시글 수";


*/

    // }
        










