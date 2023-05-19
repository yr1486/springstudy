<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/home/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${contextPath}/resources/home/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>SemiProject - PLAYON</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/home/js/scripts.js"></script>
<script>

  $(function(){
    
    // 원글 달기 결과 메시지
    if('${addResult}' != ''){
      if('${addResult}' == '1') {
        alert('원글이 달렸습니다.');
      } else {
        alert('원글 달기가 실패했습니다.');
      }
    }
    
    // 게시글 삭제 결과 메시지
    if('${removeResult}' != ''){
      if('${removeResult}' == '1') {
        alert('게시글이 삭제되었습니다.');
      } else {
        alert('게시글 삭제가 실패했습니다.');
      }
    }
    
    // 답글 달기 결과 메시지
    if('${addReplyResult}' != ''){
      if('${addReplyResult}' == '1') {
        alert('답글이 달렸습니다.');
      } else {
        alert('답글 달기가 실패했습니다.');
      }
    }
    
    // 삭제 버튼 이벤트
    $('.frm_remove').on('submit', function(event){
      if(confirm('BBS를 삭제할까요?') == false){
        event.preventDefault();
        return;
      }
    })
    
    // 답글 작성 화면 표시/숨기기
    $('.btn_reply').on('click', function(){

      // 작성화면
      let write = $(this).closest('.list').next();  // write는 jQuery객체이다. (jQuery wrapper가 필요 없다.)

      // 작성화면이 blind를 가지고 있다 = 다른 작성화면이 열려 있다
      if(write.hasClass('blind')){
        
        $('.write').addClass('blind');  // 모든 작성화면을 닫자
        write.removeClass('blind');     // 현재 작성화면을 열자
        // 달라가 있는 라이트와 없는 라이트의 구분을 해야해. write =>  this 참고
        
      // 작성화면이 blind를 가지고 있지 않다 = 현재 작성화면이 열려 있다
      } else {
        write.addClass('blind');        // 현재 작성화면을 닫자
      }
      
    })
    
  })

</script>
<style>
.seacrh{
text-align:center;

 .blind {
    display: none;
  }
}


</style>
</head>
<body>
  <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="${contextPath}/">MOOVOD</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">로그인</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">회원가입</a></li>
                        <li class="nav-item"><a class="nav-link" href="${contextPath}/cs/main.do">고객센터</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Store</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            장바구니
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
        
        
        <!-- Header-->
        <header class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-black">
                    <h1 class="display-4 fw-bolder">MOOVOD</h1>
                    <p class="lead fw-normal text-black-50 mb-0">세상의 모든 영화를 MOOVOD 하세요</p>
                </div>
            </div>
        </header>
        <div class="seacrh">
        <input type="text" id="topSearchTxt" name="topSearchTxt"
         placeholder="작품,배우,감독명을 입력해보세요."
         value=""/>
        <label for="topSearchTxt" class="btn_search">
          <button type="button" >검색</button>
        </label>
    </div>
    <hr>
        <!-- navbar-->
        <nav>
          <div id="navbar">
            <ul class="nav justify-content-center">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${contextPath}/reviews/list.do">Review</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${contextPath}/reviews/buy.do">buy</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
          </li>
        </ul>
          </div>
        </nav>
        <hr>

            
          <!-- body  -->
          
  
  <h1>Q&A</h1>
  
  <div>
    <a href="${contextPath}/cs/write.do">1:1 문의 작성하러 가기(들어가서 로그인페이지로 연결해야되는지)</a>
  </div>
 
  <br>
  
  <h1>계층 게시판 자리</h1>
  
  
  <hr>
  
  <div>
    <div>${pagination}</div>
    
    <table border="1">
      <thead>
        <tr>
          <td>NO</td> 
          <td>ID</td>
          <td>문의유형category</td>
          <td>제목title</td>
          <td>IP</td>
          <td>작성일자</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${bbsList}" var="bbs" varStatus="vs">
          <c:if test="${bbs.state == 1}">
            <!-- 게시글 내용 -->
            <tr class="list">
              <td>${beginNo - vs.index}</td>
              <td>${bbs.writer}</td>
              <td>
                <!-- DEPTH에 의한 들여쓰기 -->
                <c:forEach begin="1" end="${bbs.depth}" step="1">&nbsp;&nbsp;&nbsp;</c:forEach>
                <!-- 답글은 [Re] 표시하기 -->
                <c:if test="${bbs.depth > 0}">[Re]</c:if>
                <!-- 제목 -->
                ${bbs.title}
                <!-- 답글작성하기 버튼 -->
                <input type="button" value="답글" class="btn_reply">
              </td>
              <td>${bbs.ip}</td>
              <td>${bbs.createdAt}</td>
              <td>
                <form class="frm_remove" method="post" action="${contextPath}/bbs/remove.do">
                  <input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
                  <button>삭제</button>
                </form>
              </td>
            </tr>
            <!-- 답글 작성 화면 -->
            <tr class="write blind">
              <td colspan="6">
                <form method="post" action="${contextPath}/bbs/reply/add.do">
                  <div>
                    <label for="writer">작성자</label>
                    <input id="writer" name="writer" required="required">
                  </div>
                  <div>
                    <label for="title">제목</label>
                    <input id="title" name="title" required="required">
                  </div>
                  <div>
                    <button>답글달기</button>
                    <!-- 원글의 depth, groupNo, groupOrder를 함께 보낸다. -->
                    <input type="hidden" name="depth" value="${bbs.depth}">
                    <input type="hidden" name="groupNo" value="${bbs.groupNo}">
                    <input type="hidden" name="groupOrder" value="${bbs.groupOrder}">
                  </div>
                </form>
              </td>
            </tr>
          </c:if>
          <c:if test="${bbs.state == 0}">
            <tr>
              <td>${beginNo - vs.index}</td>
              <td colspan="5">삭제된 게시글입니다.</td>
            </tr>
          </c:if>
        </c:forEach>
      </tbody>
    </table>
  </div>        
            
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                       
      
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        

</body>
</html>