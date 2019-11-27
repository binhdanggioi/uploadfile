<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>
	<div class="row" style="padding-top:54px;">

		<div class="col-lg-3">
			<h1 class="my-4">Thể Loại</h1>
            <div class="list-group" name="title">
				<c:forEach var="item" items="${categories}">
					<a href="#" class="list-group-item">${item.name}</a>
				</c:forEach>
            </div>
		</div>
		<div class="col-lg-9">
			<br/>
			<div class="row">
				<c:forEach var="item" items="${content}">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" name="title">${item.title}</a>
								</h4>
								<h5>$24.99</h5>

								<p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.col-lg-9 -->
	</div>
</body>
</html>