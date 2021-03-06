<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>
.contents{
	border: 1px solid gray;
	border-radius: 10px;
	padding: 10px;
}
</style>
</head>
<div style="min-height: 561px;">
	<h1>게시글 상세</h1>
	<div class="form-group">
	  <input type="text" class="form-control" name="title" value="${board.title}" readonly>
	</div>
	<div class="form-group">
	  <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
	</div>
	<div class="contents">
		${board.contents}
	</div>
	<div class="form-group">
		<label>첨부파일</label>
		<c:if test="${board.fileName ne ''}">
			<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}">
				${board.fileName}		
			</a>
		</c:if>
		<c:if test="${board.fileName eq ''}">없음</c:if>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&type=${cri.type}&search=${cri.search}"><button class="btn btn-success">게시글 목록</button></a>
		<c:if test="${user.id eq board.writer}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}&page=${cri.page}&type=${cri.type}&search=${cri.search}"><button class="btn btn-success">게시글 수정</button></a>
			<a href="<%=request.getContextPath()%>/board/delete?num=${board.num}"><button class="btn btn-success">게시글 삭제</button></a>
		</c:if>
	</div>	
</div>
<script>
$(document).ready(function(){

});	//레디
</script>