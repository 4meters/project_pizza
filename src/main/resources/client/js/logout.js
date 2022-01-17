const auth = new Auth();
const authAdmin = new AuthAdmin();

document.getElementsByClassName("logout")[0].addEventListener("click", (e) => {
	auth.logOut();
});
