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
                    <h2>Register User</h2>

                    <% if(request.getAttribute("status") == "success"){ %>
                      <div class='alert alert-success border-1 border-dark alert-dismissible fade show' role='alert'>
                        <strong>Success!</strong> Registration successful.
                        <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                      </div>
                    <% } else if(request.getAttribute("status") == "failed") { %>
                      <div class='alert alert-danger border-1 border-dark alert-dismissible fade show' role='alert'>
                        <strong>Error!</strong> Registration successful.
                        <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                      </div>
                    <% } %>

                    <form action="register" method="post">

                        <div class="form-group">
                          <label for="fullname">Full Name</label>
                          <input type="text" class="form-control" name="fullname" id="fullname" placeholder="Full name">
                          <small class="form-text text-muted">Enter your full name</small>
                        </div>

                        <div class="form-group">
                          <label for="username">Username</label>
                          <input type="text" class="form-control" name="username" id="username" placeholder="Username">
                          <small class="form-text text-muted">Enter username</small>
                        </div>

                        <div class="form-group">
                          <label for="email">Email</label>
                          <input type="text" class="form-control" name="email" id="email" placeholder="Email Address">
                          <small class="form-text text-muted">Enter email</small>
                        </div>

                        <div class="form-group">
                          <label for="phone">Phone</label>
                          <input type="text" class="form-control" name="phone" id="phone" placeholder="Phone number">
                          <small class="form-text text-muted">Enter phone number</small>
                        </div>

                        <div class="form-group">
                          <label for="password">Password</label>
                          <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                          <small class="form-text text-muted">Enter prefered password</small>
                        </div>

                        <div class="form-group">
                          <label for="con_password">Confirm Password</label>
                          <input type="password" class="form-control" name="con_password" id="con_password" placeholder="Confirm Password">
                          <small class="form-text text-muted">Enter password again</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Register</button>

                    </form>
                </div>

            </div>
            <!-- /.row -->

        </div>
    </div>
</div>

<%@ include file="/static/footer.jsp" %>