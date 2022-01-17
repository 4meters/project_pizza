const auth = new Auth();


document.getElementsByClassName("logout")[0].addEventListener("click", (e) => {
	auth.logOut();
});
