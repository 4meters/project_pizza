class ChangePassword {
  constructor(form,fields) {
    this.form = form;
    this.fields = fields
    this.validateonSubmit();
  }

  validateonSubmit() {
    let self = this;

    this.form.addEventListener("submit", (e) => {
    e.preventDefault();
    var error = 0;
    self.fields.forEach((field) => {
      console.log(field);
      const input = document.querySelector(`#${field}`);
      if (self.validateFields(input) == false) {
        error++;
      }
    });
    if (error == 0){
     console.log("working");
     self.sendNewPassword();
    }
  });
  }

  validateFields(field){
    if (field.value.trim() === "") {
      this.setStatus(
        field,
        `${field.value} cannot be blank`,
        "error"
      );
      return false;
    }
      if (field.value.trim() === "") {
        this.setStatus(
          field,
          `${field.value} cannot be blank`,
          "error"
        );
        return false;
      } else {
  			if (field.type == "password") {
  				if (field.value.length < 8) {
  					this.setStatus(
  						field,
  						"password must be at least 8 characters",
  						"error"
  					);
  					return false;
  				} else {
  					this.setStatus(field, null, "success");
  					return true;
  				}
  			} else {
  				this.setStatus(field, null, "success");
  				return true;
  			}
  		}

  }



  setStatus(field, message, status) {
  	const errorMessage = field.parentElement.querySelector(".error-message");

  	if (status == "success") {
  		if (errorMessage) {
  			errorMessage.innerText = "";
  		}
  		field.classList.remove("input-error");
  	}

  	if (status == "error") {
  		errorMessage.innerText = message;
  		field.classList.add("input-error");
  	}
  }





  sendNewPassword(fields){
  let self = this;
  let newPasswordRequset = new XMLHttpRequest();
  console.log(newPasswordRequset.status);
  console.log(login);
  newPasswordRequset.open('POST', 'http://127.0.0.1:8080/api/user/change-password');
  newPasswordRequset.setRequestHeader("Content-Type", "application/json");

  let toSend={
    login: login,
    password: document.getElementById('oldPassword').value,
    newPassword: document.getElementById('newPassword').value
  }

  console.log("JsontoSend "+JSON.stringify(toSend));
  newPasswordRequset.send(JSON.stringify(toSend));
  console.log(newPasswordRequset.status);
  newPasswordRequset.onreadystatechange = function() {
    if(this.status == 400){
      console.log("jest juz taki użytkownik");
      console.log(newPasswordRequset.readyState);
      }
      else if(this.status == 201){
      alert("Udało się");
      console.log("working send");
      }
    }
  }
}



class DeleteAccount {
  constructor() {
    this.login = login;
    this.password = deletePassword;
    this.deleteButton = deleteButton;
  }


  deleteAccount(login, password){
    let self = this;
    let deleteAccountRequset = new XMLHttpRequest();

    deleteAccountRequset.open('POST', 'http://127.0.0.1:8080/api/user/delete');
    deleteAccountRequset.setRequestHeader("Content-Type", "application/json");

    let toSend={
      login: self.login,
      password: document.getElementById("deletePassword").value
    }
    console.log("JsontoSend "+JSON.stringify(toSend));
    deleteAccountRequset.send(JSON.stringify(toSend));
    console.log(deleteAccountRequset.status);
    deleteAccountRequset.onreadystatechange = function() {
      if(this.status == 400){
        console.log("delete coś jest nie tak");
        console.log(deleteAccountRequset.readyState);
        }
        else if(this.status == 200){
        auth.logOut();
        }
      }
  }
}



const form = document.querySelector(".ChangePassword")
if(form){
  const fields = ["oldPassword", "newPassword"];
  const validator = new ChangePassword(form, fields);

}

const login = localStorage.getItem("login");
const d = new DeleteAccount();
document.getElementById("deleteButton").addEventListener("click", (e) => {
	d.deleteAccount();
});
