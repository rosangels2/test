<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>
.page-item .fas{
	font-size:20px;
}
</style>
</head>
<div style="min-height: 561px;">
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>게시글 번호</th>
	        <th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
	        <th>조회수</th>
	      </tr>
	    </thead>
	    <tbody>
		    <c:if test="${list.size() ne 0}">
		    <c:forEach items="${list}" var="board">
		      <tr>
		        <th>${board.num}</th>
		        <td> <a href="<%=request.getContextPath()%>/board/display?num=${board.num}">${board.title}</a> </td>
		        <td>${board.writer}</td>
		        <td>${board.registered}</td>
		        <td>${board.views}</td>
		      </tr>
		    </c:forEach>
		    </c:if>
    		<c:if test="${list.size() eq 0}">
		      <tr>
		        <td colspan="5">게시글이 존재하지 않습니다.</td>
		      </tr>
		    </c:if>
	    </tbody>
	  </table>
	<ul class="pagination" style="justify-content: center;">
	    <c:if test="${pageMaker.prev}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.startPage-1}"><i class="fas fa-chevron-left"></i></i></a>
	        </li>
	    </c:if>
	    <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage}" var="index">
 			<c:choose>
 			<c:when test="${pageMaker.criteria.page != index}">
	 			<li class="page-item">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}">${index}</a>
		        </li>
		    </c:when>
		    <c:otherwise>
		        <li class="page-item active">
		            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${index}">${index}</a>
		        </li>
		    </c:otherwise>
	        </c:choose>
	    </c:forEach>
	    <c:if test="${pageMaker.next}">
	        <li class="page-item">
	            <a class="page-link" href="<%=request.getContextPath()%>/board/list?page=${pageMaker.endPage+1}"><i class="fas fa-chevron-right"></i></i></a>
	        </li>
	    </c:if>
	</ul>
</div>