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
  function fnList(){
    location.href = '${contextPath}/cs/list.do';
  }
  
  function fnFileCheck(vThis){

    // 최대 크기 10MB
    let maxSize = 1024 * 1024 * 10;
    
    // 첨부된 파일 목록
    let files = vThis.files;
    
    // 첨부된 파일 순회(크기 체크 + 첨부된 파일명 표시)
    $('#fileList').empty();
    $.each(files, function(i, file){
      
      // 크기 체크
      if(file.size > maxSize){
        alert('각 첨부파일의 최대 크기는 10MB입니다.');
        $(vThis).val('');  // 첨부된 파일을 모두 지운다.
        $('#fileList').text('첨부 파일의 목록이 이 곳에 표시됩니다');
        return;
      }
      
      // 첨부된 파일명 표시
      $('#fileList').append('<div>' + file.name + '</div>');
      
    })
  }
  
  $(function(){
    $('#csContent').summernote({
      width: 640,
      height: 480,
      lang: 'ko-KR',
      toolbar: [
        ['style', ['bold', 'italic', 'underline', 'clear']],
        ['font', ['strikethrough', 'superscript', 'subscript']],
        ['fontname', ['fontname']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['fullscreen', 'codeview', 'help']]
      ]
    })
  })
  
</script>
<style>
.seacrh{
text-align:center;
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


    <div>
    <h1>1:1 문의</h1>
    <form method="post" enctype="multipart/form-data" action="${contextPath}/cs/add.do">
      <div>
        <label for="csTitle">제목</label>
        <input type="text" id="csTitle" name="csTitle">
      </div>
      <div>
        <label for="csWriter">작성자</label>
        <input type="text" id="csWriter" name="csWriter">
      </div>
      <div>
        <label for="csContent">내용</label><br>
        <textarea id="csContent" name="csContent"></textarea>
      </div>
      <div>
        <label for="files">첨부</label>
        <input type="file" id="files" name="files" multiple="multiple" onchange="fnFileCheck(this)">
        <div id="fileList">첨부 파일의 목록이 이 곳에 표시됩니다</div>
      </div>
      <hr>
      <div>
        <button>작성완료</button>
        <input type="button" value="목록" onclick="fnList()">
      </div>
    </form>
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