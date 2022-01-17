class AuthAdmin {
  constructor() {
    const login = localStorage.getItem("login");
		this.validateAuthAdmin(login);
  }


  validateAuthAdmin(login){
    if(login == "admin"){
        console.log("działa admin");
    } else{
      setTimeout(function() {
        console.log("działa nie admin");
        document.getElementById("content").style.pointerEvents = "none";
      }, 100);
      document.getElementById("newItem").style.display = "none";

      }
    }


}
console.log("check");
const authAdmin = new AuthAdmin();
