<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Silverfang</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" postId="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/post/create">Create Post</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" postId="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    SortBy
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form action="/post">
                        <input type="hidden" name="sortBy" value="title">
                        <input type="submit" style="border: none;background: none" value="SortByTitle">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="sortBy" value="content">
                        <input type="submit" style="border: none;background: none" value="SortByContent">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="sortBy" value="createdAt">
                        <input type="submit" style="border: none;background: none" value="SortByCreation">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="sortBy" value="updatedAt">
                        <input type="submit" style="border: none;background: none" value="SortByUpdate">
                    </form>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" postId="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Filter By
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form action="/post">
                        <input type="hidden" name="filterBy" value="horror">
                        <input type="submit" style="border: none;background: none" value="Filter by horror">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="filterBy" value="SCI-FI">
                        <input type="submit" style="border: none;background: none" value="Filter by Sci-fi">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="filterBy" value="Comic">
                        <input type="submit" style="border: none;background: none" value="Filter by Comic">
                    </form>
                    <form action="/post">
                        <input type="hidden" name="filterBy" value="Romance">
                        <input type="submit" style="border: none;background: none" value="Filter by Romance">
                    </form>
                </div>
            </li>
            <li>
                <security:authorize access="!isAuthenticated()">
                    <a class="nav-link" href="/login">Login</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <a class="nav-link" href="/logout">Logout</a>
                </security:authorize>
            </li>
            <li>
                <security:authorize access="isAuthenticated()">
                    <a class="nav-link" href="/myPost">MyPost</a>
                </security:authorize>
            </li>
            <li>
                <security:authorize access="!isAuthenticated()">
                <a  class="nav-link" href="/register">Register as Author</a>
                </security:authorize>
            </li>
            <li><security:authorize access="!isAuthenticated()">
                <a  class="nav-link" href="/forgotPassword">Forgot Your pass</a>
            </security:authorize>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="/post" >
            <input class="form-control mr-sm-2"  name="key" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>