<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>
	
</style>
</head>
<div style="min-height: 561px;">
	<h1>게시글 수정</h1>
	<form method="post" action="<%=request.getContextPath()%>/board/modify" enctype="multipart/form-data">
		<input type="hidden" value="${board.num}" name="num">
		<div class="form-group">
		  <input type="text" class="form-control" name="title" value="${board.title}">
		</div>
		<div class="form-group">
		  <input type="text" class="form-control" name="writer" value="${user.id}" readonly>
		</div>
		<textarea id="summernote" name="contents">${board.contents}</textarea>	<!-- textarea를  -->
		<div class="form-group">
			<label>첨부파일</label>
			<c:if test="${board.fileName ne ''}">
				<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}" id="file-link">
					${board.fileName}		
				</a>
				<button type="button" id="modify-close">X</button>
				<input type="hidden" name="file" value="${board.file}">
			</c:if>
			<c:if test="${board.fileName eq ''}">없음</c:if>
		</div>
		<div>
		<input type="file" class="form-control-file border " name="file2">		
		<button type="submit" class="btn btn-success">수정하기</button>
		
	</form>
</div>
<script>
$(document).ready(function(){
	$('#modify-close').click(function(){
		$('#file-link').remove();
		$('input[name=file]').val('');
		$(this).css('display', 'none');
	});
	//써머노트
	$('#summernote').summernote({
    placeholder: 'Hello bootstrap 4',
    tabsize: 2,
    height: 300,
    lang: 'ko-KR'
  });
});		//레디
</script>