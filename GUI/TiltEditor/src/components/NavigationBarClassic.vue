<template>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-light" href="#">
            <img src="../assets/tiltLogo.jpg" alt="" height="50">
        </a>
        <!-- <a class="navbar-brand" href="#">TILT as a Service</a> -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        Building blocks
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Meta</a>
                        <a class="dropdown-item" href="#">Controller</a>
                        <a class="dropdown-item" href="#">Data Protection Officer</a>
                        <a class="dropdown-item" href="#">Adequacy decisions</a>
                        <a class="dropdown-item" href="#">Access/Data portability</a>
                        <a class="dropdown-item" href="#">Right to complain</a>
                        <a class="dropdown-item" href="#">Automated decision making</a>
                        <a class="dropdown-item" href="#">Notification on change</a>
                        <a class="dropdown-item" href="#">Adequacy decisions</a>
                        <a class="dropdown-item" href="#">Access/Data portability</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        Sprache wählen
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Deutsch</a>
                        <a class="dropdown-item" href="#">Englisch</a>
<!--                    <a class="dropdown-pic" id="dropdown_1" height="15" href="#"><img src="united-kingdom-flag-png-xl.png" alt="Englisch">Englisch</a>
                        <a class="dropdown-pic" id="dropdown_2" height="15" href="#"><img src="51cNvRZc0nL._AC_SX425_.jpg" alt="Deutsch">Deutsch</a>
-->
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input id="Login" class="form-control mr-sm-2" type="login" placeholder="Benutzername" aria-label="Login">
                <input id="Login" class="form-control mr-sm-2" type="login" placeholder="Passwort" aria-label="Login">
                <button id="Login-btn" class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
            </form>
<!--
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        Sprache wählen
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-pic" id="dropdown" href="#"><img src="united-kingdom-flag-png-xl.png" alt="Englisch"></a>
                        <a class="dropdown-pic" id="dropdown" href="#"><img src="51cNvRZc0nL._AC_SX425_.jpg" alt="Deutsch"></a>
                    </div>
                </li>
            </ul>
-->
        </div>
    </nav>
</template>

<script>
    export default {
          name: "NavigationBar"
    }
</script>

<style scoped>
.navbar-brand-logo{
    height:50px;
    width:70px;
}

.navbar {
    background-color: whitesmoke !important;
    border-color: #649A9C !important;
}

.navbar-brand{
    color: #649A9C !important; 
}

.nav-link{
    color: #649A9C !important; 

}

</style>