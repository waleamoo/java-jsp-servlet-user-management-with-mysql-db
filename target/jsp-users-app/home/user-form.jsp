<%@ include file="/static/header.jsp" %>
<div class="container my-4">
    <div class="row">
        <div class="col-lg-3">

            <h1 class="my-4">Sidebar Content</h1>
            <div class="list-group">
                <a href="#" class="list-group-item">Category 1</a>
                <a href="#" class="list-group-item">Category 2</a>
                <a href="#" class="list-group-item">Category 3</a>
            </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <div class="row">

                <div class="col-12">
                    <ul class="navbar-nav">
                        <li>
                            <a href="<%= request.getContextPath() %>/list" class="nav-list">Students List</a>
                        </li>
                    </ul>

                    <c:if test="${student != null}">
                        <form action="update" method="post">
                    </c:if>
                    <c:if test="${student == null}">
                        <form action="add" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit User
                            </c:if>
                            <c:if test="${student == null}">
                                Add User 
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />">
                    </c:if>


                        <div class="form-group">
                          <label for="name">Student Name</label>
                          <input type="text" class="form-control" name="name" id="name" value="<c:out value='${student.name}' />" placeholder="Student name">
                          <small class="form-text text-muted">Enter your full name</small>
                        </div>

                        <div class="form-group">
                          <label for="email">Student Email</label>
                          <input type="text" class="form-control" name="email" id="email" value="<c:out value='${student.email}' />" placeholder="Student email">
                          <small class="form-text text-muted">Enter email address</small>
                        </div>

                        <div class="form-group">
                          <label for="country">Student Country</label>
                          <input type="text" class="form-control" name="country" id="country" value="<c:out value='${student.country}' />" placeholder="Student country">
                          <small class="form-text text-muted">Enter country</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Save</button>

                    </form>
                </div>

            </div>
            <!-- /.row -->

        </div>
    </div>
</div>

<%@ include file="/static/footer.jsp" %>