<% if(session.getAttribute("user_name") !=null){ response.sendRedirect(application.getInitParameter("WebAppContextPath")
  + "/list" ); } %>
  <%@ include file="static/header.jsp" %>
    <div class="container my-4">
      <div class="row">

        <div class="col-lg-3">
          <h2 style="color:red;">Content</h2>

          <div>Never give up!</div>

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
              <h2>Welcome to JSP Servlet User Management With MySQL DB</h2>

              <h3>Login to account</h3>
              <p class="text-muted">Provide your credentials</p>
              <% if(request.getAttribute("status")=="success" ) { %>
                <div class='alert alert-success border-1 border-dark alert-dismissible fade show' role='alert'>
                  <strong>Success!</strong> Registration successful. Please login.
                  <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span
                      aria-hidden='true'>&times;</span></button>
                </div>
                <% }else if(request.getAttribute("status")=="failed" ){ %>
                  <div class='alert alert-danger border-1 border-dark alert-dismissible fade show' role='alert'>
                    <strong>Error!</strong> If an account exists, login is invalid.
                    <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                  </div>
                  <% }else if(request.getAttribute("status")=="invalidEmail" ){ %>
                    <div class='alert alert-danger border-1 border-dark alert-dismissible fade show' role='alert'>
                      <strong>Error!</strong> Email is invalid.
                      <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span
                          aria-hidden='true'>&times;</span></button>
                    </div>
                    <% }else if(request.getAttribute("status")=="invalidPassword" ){ %>
                      <div class='alert alert-danger border-1 border-dark alert-dismissible fade show' role='alert'>
                        <strong>Error!</strong> Password is invalid.
                        <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span
                            aria-hidden='true'>&times;</span></button>
                      </div>
                      <% } %>

                        <form action="login" method="post">

                          <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" name="username" id="username"
                              placeholder="Username">
                            <small class="form-text text-muted">Enter username</small>
                          </div>

                          <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password"
                              placeholder="Password">
                            <small class="form-text text-muted">Enter prefered password</small>
                          </div>

                          <button type="submit" class="btn btn-success">Login</button>

                        </form>

            </div>

          </div>
          <!-- /.row -->

        </div>
        
      </div>
    </div>

    <%@ include file="static/footer.jsp" %>