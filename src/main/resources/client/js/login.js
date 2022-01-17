class Login {
	constructor(form, fields) {
		this.form = form;
		this.fields = fields;
		this.validateonSubmit();
	}

	validateonSubmit() {
		let self = this;

		this.form.addEventListener("submit", (e) => {
			e.preventDefault();
			var error = 0;
			self.fields.forEach((field) => {
				const input = document.querySelector(`#${field}`);
				if (self.validateFields(input) == false) {
					error++;
				}
			});
			if (error == 0) {
				//do login api here

				console.log(document.getElementById('password').value);
				console.log(document.getElementById('username').value);

				self.sendLogin(self.fields);


				//this.form.submit();
        //window.location.replace("home.html");
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



	sendLogin(fields){
	let self = this;
	let loginRequset = new XMLHttpRequest();
	let response;
	console.log(loginRequset.status);
	loginRequset.open('POST', 'http://127.0.0.1:8080/api/user/login');
	loginRequset.setRequestHeader("Content-Type", "application/json");
	loginRequset.onload = function () {
	response = JSON.parse(loginRequset.responseText);
	console.log(response['token']);
	localStorage.setItem("auth", response['token']);
		localStorage.setItem("login", document.getElementById('username').value);
		localStorage.setItem("admin", "admin");
		//--->debug only
	};
	let toSend={
		login: document.getElementById('username').value,
		password: document.getElementById('password').value,
	}

	console.log("JsontoSend "+JSON.stringify(toSend));
	loginRequset.send(JSON.stringify(toSend));
	console.log(loginRequset.status);
	loginRequset.onreadystatechange = function() {
		console.log(this.status);
		if(this.status == 400){
			console.log("nie ma takiego użytkownika");
		}
		else if(this.status == 201){
			console.log("działa");
			window.location.replace("home.html");
			this.form.submit();
			}
		}
		/*	self.fields.forEach((field) => {
				console.log(field);
				const input = document.querySelector(`#${field}`);
					this.setStatus(
						input,
						`${input.previousElementSibling.innerText} bad`,
						"error"
					);
				});
				*/

	console.log(loginRequset.readyState);

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

const form = document.querySelector(".loginForm");
if (form) {
	const fields = ["username", "password"];
	const validator = new Login(form, fields);
}
