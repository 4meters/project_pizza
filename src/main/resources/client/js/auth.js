class Auth {
	constructor() {
    document.getElementsByClassName("login")[0].style.display = "none";
		const auth = localStorage.getItem("auth");

		this.validateAuth(auth);
	}

	validateAuth(auth) {
		if (auth == null) {
			document.getElementsByClassName("login")[0].style.display = "inline";


		} else {
          document.getElementsByClassName("logout")[0].style.display = "inline";
					document.getElementsByClassName("options")[0].style.display = "inline";
		}
	}


	logOut() {
		localStorage.removeItem("auth");
		localStorage.removeItem("admin");
		localStorage.removeItem("login");
		window.location.replace("home.html");
	}
}
