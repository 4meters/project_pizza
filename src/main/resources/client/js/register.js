class Register {
	constructor(form, fields) {
		this.form = form;
		this.fields = fields;
		this.validateonSubmit();
	}

	validateonSubmit() {
		let self = this;
    var pass = document.getElementById("password");
    var pass2 = document.getElementById("password2");
		this.form.addEventListener("submit", (e) => {
			e.preventDefault();
			var error = 0;

			self.fields.forEach((field) => {
				console.log(field);
				const input = document.querySelector(`#${field}`);
				if (self.validateFields(input) == false ||self.validatePassword(pass, pass2) == false) {
					error++;

				}
			});

			if (error == 0) {
				//do Register api here
        console.log("WTF");
        self.sendRegister();
			//	this.form.submit();
      //  window.location.replace("login.html");
			}
		});
	}

	validateFields(field) {
		if (field.value.trim() === "") {
			this.setStatus(
				field,
				`${field.previousElementSibling.innerText} cannot be blank`,
				"error"
			);
			return false;
		} else {
			if (field.type == "password") {
				if (field.value.length < 8) {
					this.setStatus(
						field,
						`${field.previousElementSibling.innerText} must be at least 8 characters`,
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

 validatePassword(pass , pass2){
      if(pass.value != pass2.value){
        console.log(pass.value);
        console.log(pass2.value);
        this.setStatus(
          pass2,
          `${pass2.previousElementSibling.innerText}  must be the same`,
          "error",

        );
        this.setStatus(
          pass,
          `${pass.previousElementSibling.innerText}  must be the same`,
          "error",

        );
        return false;
      } else {
        this.setStatus(pass, null, "success");
        this.setStatus(pass2, null, "success");
        return true;
      }
  }

  sendRegister(fields){
  let self = this;
  let loginRequset = new XMLHttpRequest();
  console.log(loginRequset.status);
  loginRequset.open('POST', 'http://127.0.0.1:8080/api/user/create');
  loginRequset.setRequestHeader("Content-Type", "application/json");

  let toSend={
    login: document.getElementById('username').value,
    password: document.getElementById('password').value,
  }

  console.log("JsontoSend "+JSON.stringify(toSend));
  loginRequset.send(JSON.stringify(toSend));
  console.log("status"+loginRequset.status);
	console.log("readystatus"+loginRequset.readyState);
  loginRequset.onreadystatechange = function() {
    if(this.status == 400){
			alert("Zły login lub hasło");
      console.log("jest juz taki użytkownik");
      console.log(loginRequset.readyState);
      }
      else if(this.status == 201){
      window.location.replace("login.html")
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
}

const form = document.querySelector(".registerForm");
if (form) {
	const fields = ["username", "password","password2"];
	const validator = new Register(form, fields);
}
