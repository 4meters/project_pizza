class EditProduct {
  constructor(form,fields,token) {
    this.fields = fields;
    this.form = form;
    this.token = token;
    this.submit();
    this.btndeleteProduct();
  }
  btndeleteProduct(){
    this.form.addEventListener("click", (e) => {
      this.deleteProduct(this.token);
    });

  }

  submit(){
    this.form.addEventListener("submit", (e) => {
    this.sendEditProduct(this.fields, this.token);
  });
  }

  sendEditProduct(){

    let editProduct = new XMLHttpRequest();
    editProduct.open('POST', 'http://127.0.0.1:8080/api/product/edit-product?token=' + this.token  );
    editProduct.setRequestHeader("Content-Type", "application/json");
    editProduct.onload = function (){

    };

    let toSend = {
      id: id,
      type:document.getElementsByName('type')[0].value,
      name:document.getElementsByName('name')[0].value,
      description:document.getElementsByName('description')[0].value,
      costS:document.getElementsByName('costS')[0].value,
      costM:document.getElementsByName('costM')[0].value,
      costL:document.getElementsByName('costL')[0].value,
      costU:document.getElementsByName('costU')[0].value,
    }


  	editProduct.send(JSON.stringify(toSend));

  	editProduct.onreadystatechange = function() {
  		console.log(this.status);
  		if(this.status == 400){
  			console.log("nie działa");
        alert("nie działa");
  		}
  		else if(this.status == 201){
  			console.log("działa");
        alert("działa");
  			//window.location.replace("home.html");
  			//this.form.submit();
  			}
  		}
  }


  deleteProduct(){

    {
      const url = 'http://127.0.0.1:8080/api/product/delete-product?token=' + this.token +'&productId='+id
      let deleteProduct = new XMLHttpRequest();

      deleteProduct.open('DELETE', url );
      deleteProduct.onload = function (){

      };


      deleteProduct.send(null);

      deleteProduct.onreadystatechange = function() {
        console.log(this.status);
        if(this.status == 400){
          console.log("nie działa");
          alert("nie działa");
        }
        else if(this.status == 201){
          console.log("działa");
          alert("działa");
          //window.location.replace("home.html");
          //this.form.submit();
          }
        }

    }

  }


}

const token = localStorage.getItem("auth")
const form = document.querySelector(".editProduct");

if (form) {
	const fields = ["type","name","description","costS","costM","costL","costU"];
	const editProduct = new EditProduct(form, fields,token);
}















const id = (new URLSearchParams(window.location.search)).get('idProduct');
console.log(id);
document.getElementById('Test').innerHTML = "Id = "+id;
