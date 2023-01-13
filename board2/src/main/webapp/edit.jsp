<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>자유게시판</strong>
			<p>자유게시판 입니다.</p>
		</div>
		<div class="board_write_wrap">
			<form name="frm" method="post" action="update?board_no=${board.board_no}">
				<div class="board_write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" name="title" maxlength="50"
									value="${board.title}" />
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>글쓴이</dt>
							<dd>
								<input type="text" name="user_id" maxlength="10"
									value="${board.user_id}" />
							</dd>
						</dl>
						<!-- 					<dl>
						<dt>비밀번호</dt>
						<dd>
							<input type="password" placeholder="비밀번호 입력" value="1234" />
						</dd>
					</dl> -->
					</div>
					<div class="cont">
						<textarea name="content">${board.content}</textarea>
					</div>
				</div>
			</form>
			<div class="bt_wrap">
				<a href="#" class="on" onclick="chkForm(); return false;">수정</a> <a
					href="list">취소</a>
			</div>
		</div>
	</div>
	<script>
	  <c:if test="${error != null}">
	    alert("${error}");
	  </c:if>
	</script>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>