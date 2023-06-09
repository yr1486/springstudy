<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
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
</head>
<body>

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
  
</body>
</html>