class AddNewProduct {
  constructor(form,fields,token) {
    this.fields = fields;
    this.form = form;
    this.token = token;
    this.submit();
    console.log(fields);
    console.log(form);
    console.log(token);
  }

  submit(){
    this.form.addEventListener("submit", (e) => {
    this.sendNewProduct(this.fields, this.token);
  });
  }

  sendNewProduct(){

    let newProduct = new XMLHttpRequest();
    console.log(this.token);
    console.log(document.getElementsByName('id')[0].value);
    newProduct.open('POST', 'http://127.0.0.1:8080/api/product/add-product?token=' + this.token  );
    newProduct.setRequestHeader("Content-Type", "application/json");
    newProduct.onload = function (){

    };
    let toSend = {
      id:document.getElementsByName('id')[0].value,
      type:document.getElementsByName('type')[0].value,
      name:document.getElementsByName('name')[0].value,
      description:document.getElementsByName('description')[0].value,
      costS:document.getElementsByName('costS')[0].value,
      costM:document.getElementsByName('costM')[0].value,
      costL:document.getElementsByName('costL')[0].value,
      costU:document.getElementsByName('costU')[0].value,
    }
    console.log("JsontoSend "+JSON.stringify(toSend));
  	newProduct.send(JSON.stringify(toSend));
  	console.log(newProduct.status);
  	newProduct.onreadystatechange = function() {
  		console.log(this.status);
  		if(this.status == 400){
  			console.log("nie działa");
  		}
  		else if(this.status == 201){
  			console.log("działa");
  			//window.location.replace("home.html");
  			//this.form.submit();
  			}
  		}
  }

}

const token = localStorage.getItem("auth")
const form = document.querySelector(".addNewProduct");
console.log(form);
if (form) {
	const fields = ["id", "type","name","description","costS","costM","costL","costU"];
	const newProduct = new AddNewProduct(form, fields,token);
}
