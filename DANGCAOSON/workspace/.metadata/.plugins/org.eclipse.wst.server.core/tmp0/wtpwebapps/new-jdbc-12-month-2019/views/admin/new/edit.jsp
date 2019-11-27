<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var ="NewURL" value="/admin-new"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs"></div>
        <script type="text/javascript">
            try {
                ace.settings.check("breadcrumbs", "fixed")
            } catch (e) {

            }
        </script>
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="#">Trang chủ</a>
            </li>
            <li class="active">Chỉnh sửa bài viết</li>
        </ul>
    </div>
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <c:if test="${not empty messageResponse}">
                    <div class="alert alert-${alert}">
                            ${messageResponse}
                    </div>
                </c:if>
                <form id="formSubmit">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">Thể loại</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="categoryCode" name="categoryCode">
                                <c:if test="${empty model.categoryCode}">
                                    <option value="">Chọn loại bài viết</option>
                                    <c:forEach var="item" items="${categories}">
                                        <option value="${item.code}">${item.name}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty model.categoryCode}">
                                    <option value="">Chọn loại bài viết</option>
                                    <c:forEach var="item" items="${categories}">
                                        <option value="${item.code}"
                                                <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
                                                ${item.name}
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <br/><br/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">Tiêu đề</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">Hình đại diện</label>
                        <div class="col-sm-5">
                            <input type="file" id="uploadImage" style="margin-top: 15px">
                        </div>
                        <div class="col-sm-5">
                            <c:if test="${not empty model.thumbnail}">
                                <c:set var="image" value="/repository/${model.thumbnail}"/>
                                <img src="${image}" id="viewImage" width="50px" height="50px">
                            </c:if>
                            <c:if test="${empty model.thumbnail}">
                                <img src="<c:url value='/template/image/default.png'/>" id="viewImage" width="50px" height="50px">
                            </c:if>
                        </div>
                    </div>
                    <br/><br/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">Mô tả ngắn</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="shortdescription" name="shortdescription"
                                   value="${model.shortdescription}"/>
                        </div>
                    </div>
                    <br/><br/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">Nội dung</label>
                        <div class="col-sm-10">
                            <textarea rows="20" cols="132" id="content" name="content">${model.content}</textarea>
                        </div>
                    </div>
                    <br/><br/>
                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <c:if test="${not empty model.id}">
                                <input type="button" class="btn btn-white btn-warning btn-bold"
                                       value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                            </c:if>
                            <c:if test="${empty model.id}">
                                <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm mới bài viết" id="btnAddOrUpdateNew"/>
                            </c:if>
                        </div>
                    </div>
                    <input type="hidden" value="${model.id}" name="id" id="id" />
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('content');
    });
    var thumbnailBase64 = '';
    var thumbnailImageName = '';
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        if(thumbnailBase64 != ''){
            data['thumbnailBase64'] = thumbnailBase64;
            data['thumbnailImageName'] = thumbnailImageName;
        }
        var id = $('#id').val();
        if(id == ""){
            addNew(data);
        }
        else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href="${NewURL}?type=edit&id="+result.id+"&message=INSERT_SUCCESS";
            },
            error: function (error) {
                window.location.href="${NewURL}?type=list&page=1&maxPageItem=2&message=ERROR_SYSTEM";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href="${NewURL}?type=edit&id="+result.id+"&message=UPDATE_SUCCESS";
            },
            error: function (error) {
                window.location.href="${NewURL}?type=list&page=1&maxPageItem=2&message=ERROR_SYSTEM";
            }
        });
    }
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            thumbnailBase64 = e.target.result;
            thumbnailImageName = file.name;
        };
        reader.readAsDataURL(file);
    });
</script>
</body>
</html>
