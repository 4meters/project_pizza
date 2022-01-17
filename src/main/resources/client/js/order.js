
//Wypisanie Menu w orderze

$(document).ready(function() {
	var content = document.getElementById("content");
	var topR = document.getElementById("topR");
	var content2 = document.getElementById("content2");
	var pole = document.getElementById("pole");
	var nSale = 0;
	var ourData;

	var ourRequset = new XMLHttpRequest();
	ourRequset.open('GET', 'http://127.0.0.1:8080/api/product/get-productlist');
	ourRequset.onload = function () {
		ourData = JSON.parse(ourRequset.responseText);
		renderHTML(ourData["productList"]);

	};

	ourRequset.send();


	function renderHTML(data) {
		var htmlString = " ";
		var costString = " ";
		var maxPizzaID = 0;
		var maxDrinkID = 0;

		contentOrder.insertAdjacentHTML('beforeend', "Pizza ");

		for (i = 0; i < data.length; i++) {
			if(data[i].type!=0)break;

			htmlString += "<p>" + data[i].id + ".  " + data[i].name + " </p>";
			costString += "<p>" + "<a class=add-to-cart href=\"#\" data-id='"+data[i].id+"' data-name='"+data[i].name+"' data-cost='"+data[i].costS+"' data-size='S'>" +
				data[i].costS + "</a>" + "zł" + "<a class=add-to-cart href=\"#\" data-id='"+data[i].id+"' data-name='"+data[i].name+"' data-cost='"+data[i].costM+"' data-size='M'>" +
				data[i].costM + "</a>" + "zł " + "<a class=add-to-cart href=\"#\" data-id='"+data[i].id+"' data-name='"+data[i].name+"' data-cost='"+data[i].costL+"' data-size='L'>" +
				data[i].costL + "</a>" + "zł </p>";

				maxPizzaID++;

		}
		contentOrder.insertAdjacentHTML('beforeend', htmlString);
		price.insertAdjacentHTML('beforeend', costString);




		htmlString = " ";
		costString = " ";
		maxDrinkID = maxPizzaID;

		contentOrder.insertAdjacentHTML('beforeend', "Napoje ");
		price.insertAdjacentHTML('beforeend', "Cennik Napojów");
		for (i = maxPizzaID; i < data.length; i++) {
			if(data[i].type!=1)break;
			htmlString += "<p>" + data[i].id + ".  " + data[i].name + " </p>";
			costString += "<p>" + "<a class=add-to-cart href=\"#\" data-id='"+data[i].id+"' data-name='"+data[i].name+"' data-cost='"+data[i].costU+"' data-size='U'>" + data[i].costU + "</a>" + "zł</p>";

			maxDrinkID++;
		}

		contentOrder.insertAdjacentHTML('beforeend', htmlString);
		price.insertAdjacentHTML('beforeend', costString);

		htmlString = " ";
		costString = " ";

		contentOrder.insertAdjacentHTML('beforeend', "Zestawy ");
		price.insertAdjacentHTML('beforeend', "Cennik Zestawów");
		for (i = maxDrinkID; i < data.length; i++) {
			htmlString += "<p>" + data[i].id + ".  " + data[i].name + " </p>";
			costString += "<p>" + "<a class=add-to-cart href=\"#\" data-id='"+data[i].id+"' data-name='"+data[i].name+"' data-cost='"+data[i].costU+"' data-size='U'>" + data[i].costU + "</a>" + "zł</p>";
		}

		contentOrder.insertAdjacentHTML('beforeend', htmlString);
		price.insertAdjacentHTML('beforeend', costString);

	}

	function decodeSize(itemm){
		let rozmiar="";
		if(itemm.size==="S")rozmiar="Mała";
		else if(itemm.size==="M")rozmiar="Średnia";
		else if(itemm.size==="L")rozmiar="Duża";
		else rozmiar="";
		return rozmiar;
	}

	function  showSale()
	{


		saleString = " ";
		saleString =  "Koszt całkowity: "+ checkoutData.cost + "zł Cena po zniżce: " +checkoutData.costDiscount + "zł " ;
		saleProductsString = " ";
		saleProductsString = "Produkty objęte promocją:";

		if(nSale==0){
			pole.insertAdjacentHTML('beforeend','<p id="pole1">'+saleString+'</p>');
			if(checkoutData.discountType==="1"){
				let saleProduct1=discountList[0].name;
				let saleProduct2=discountList[1].name;
				pole.insertAdjacentHTML('beforeend', '<p id="pole2">'+saleProductsString+'</p>');
				pole.insertAdjacentHTML('beforeend','<p id="pole21">'+saleProduct1+" "+decodeSize(discountList[0])+'</p>');
				pole.insertAdjacentHTML('beforeend','<p id="pole22">'+saleProduct2+" "+decodeSize(discountList[1])+'</p>');
			}
			else if(checkoutData.discountType==="2"){
				let saleProduct1=discountList[0].name;
				let saleProduct2=discountList[1].name;
				let saleProduct3=discountList[2].name;
				pole.insertAdjacentHTML('beforeend', '<p id="pole2">'+saleProductsString+'</p>');
				pole.insertAdjacentHTML('beforeend','<p id="pole21">'+saleProduct1+" "+decodeSize(discountList[0])+'</p>');
				pole.insertAdjacentHTML('beforeend','<p id="pole22">'+saleProduct2+" "+decodeSize(discountList[1])+'</p>');
				pole.insertAdjacentHTML('beforeend','<p id="pole23">'+saleProduct3+" "+decodeSize(discountList[2])+'</p>');
			}
			else if(checkoutData.discountType==="3"){
				saleProductsString="Zniżka 20%"
				pole.insertAdjacentHTML('beforeend', '<p id="pole2">'+saleProductsString+'</p>');
			}
			nSale++;
		}
		else
		{
			removeSale();
			nSale=0;
			showSale();
		}


	}

	function removeSale()
	{
		if(document.contains(document.getElementById("pole1"))){
			pole1.remove();
		}
		if (document.contains(document.getElementById("pole2"))) {
			pole2.remove();
		}
		if (document.contains(document.getElementById("pole21"))) {
			pole21.remove();
		}
		if(document.contains(document.getElementById("pole22"))){
			pole22.remove();
		}
		if(document.contains(document.getElementById("pole23"))){
			pole23.remove();
		}
		document.getElementById('person').style.display='none';
		document.getElementById('pdf').style.display='none';
		document.getElementById('account').style.display='none';
		nSale=0;
	}






	// Tutaj zaczyna sie koszyk

	var cart = [];
	var discountList= [];
	var checkoutData;
	var orderList="";
	var fileName="";

	$(document).on('click', "a.add-to-cart", function(e) {
		e.preventDefault();
		var id=$(this).attr("data-id");
		var name=$(this).attr("data-name");
		//var price = Number($(this).attr("data-cost"));
		var price = parseFloat($(this).attr("data-cost").replace(',','.'));
		var size = $(this).attr("data-size")
		addItemToCart(id, name, size, price, 1)
		console.log(cart);
		displayCart();
		removeSale();
	});


	$("#clear-cart").click(function(e)
	{
		clearCart();
		removeSale();
		displayCart();
		document.getElementById('account').style.display='none';
		document.getElementById('person').style.display='none';
		document.getElementById('pdf').style.display='none';
	});


	$("#send").click(function(e)
	{
		if(cart.length>=1){
			sendCart();

			document.getElementById('person').style.display='block';
			document.getElementById('pdf').style.display='none';
			document.getElementById('account').style.display='block';
		}



	});

	$("#account").click(function(e)
	{
		sendPdfRequest();
		document.getElementById('person').style.display='none';
		document.getElementById('pdf').style.display='block';
		document.getElementById('account').style.display='none';

	});



	$("#show-cart").on("click", ".delete-item",function(e)
	{
		var name=$(this).attr("data-name");
		var size=$(this).attr("data-size");
		removeItemFromCartAll(name,size);
		saveCart();
		displayCart();
		removeSale();

	});
	$("#show-cart").on("click", ".substract-item",function(e)
	{
		var name=$(this).attr("data-name");
		var size=$(this).attr("data-size");
		var count=$(this).attr("data-count");
		removeItemFromCart(name,size,count);
		saveCart();
		displayCart();
		removeSale();
	});
	$("#show-cart").on("click", ".plus-item",function(e)
	{
		var name=$(this).attr("data-name");
		var size=$(this).attr("data-size");
		var count=$(this).attr("data-count");
		plusItemFromCart(name,size,count);
		saveCart();
		displayCart();
		removeSale();
	});
	$("#pdf").click(function(e)
	{
		//document.getElementById('pdf').style.display='none';

	});

/*	function downloadpdf(url) {

		console.log(url);
		$("#pdf").click(function(e)
		{
			//document.getElementById('pdf').style.display='none';
			this.href = url1;
		});

	}
	*/

	function sendPdfRequest(){

		let generatePdfRequest = new XMLHttpRequest();
		let requestAnswer;
		const token1 = localStorage.getItem("auth");
		const pdf = document.getElementById("pdf");



		generatePdfRequest.open('POST', 'http://127.0.0.1:8080/api/files/create-file');
		generatePdfRequest.setRequestHeader("Content-Type", "application/json");
		generatePdfRequest.onload = function () {
			requestAnswer = JSON.parse(generatePdfRequest.responseText);
			fileName=requestAnswer["fileName"];
			fileName+="?token=";
			url="http://127.0.0.1:8080/api/files/" + fileName;
			url1 = url+"?token="+token1;
			//--->debug only
			console.log("Odpowiedź serwera:\n fileName: "+requestAnswer["fileName"]);
			//
			console.log(url);
			console.log(url1);
			console.log(pdf);
			//<---
			//downloadpdf(url1);
			pdf.href=url1;
			console.log(pdf);
		};
		let toSend={
			firstName: document.getElementById('Imie').value,
			lastName: document.getElementById('Nazwisko').value,
			orderList: orderList,
			token: token1
		}
					if(token1 != null){
					console.log("JsontoSend "+JSON.stringify(toSend));
					generatePdfRequest.send(JSON.stringify(toSend));
				} else{
					alert("Wymagane zalogowanie");
				}




	}


	function displayCart()
	{
		var cartArray=listCart();
		var output = "";
		var rozmiar;



		for (var i in cartArray)
		{
			if(cartArray[i].size==="S")rozmiar="Mała";
			if(cartArray[i].size==="M")rozmiar="Średnia";
			if(cartArray[i].size==="L")rozmiar="Duża";
			if(cartArray[i].size==="U"){
				output+="<p>"
				+cartArray[i].name
				+" Cena: "+cartArray[i].price
				+"zł  Ilość:"+cartArray[i].count
				+"<button class='plus-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"' data-count='"+cartArray[i].count+"'>+</button>"
				+"<button class='substract-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"' data-count='"+cartArray[i].count+"'>-</button>"
				+"<button class='delete-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"'>X</button>"+"</p>";
			}
			else
			output+="<p>"+cartArray[i].name
						+"  Rozmiar: "+rozmiar
						+" Cena: "+cartArray[i].price +"zł  Ilość:"
						+cartArray[i].count
						+"<button class='plus-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"' data-count='"+cartArray[i].count+"'>+</button>"
						+"<button class='substract-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"' data-count='"+cartArray[i].count+"'>-</button>"
						+"<button class='delete-item' data-name='"+cartArray[i].name+"' data-size='"+cartArray[i].size+"'>X</button>"+"</p>";
		}
		$("#show-cart").html(output);
		//$("#total-cart").html (totalCart() );

	}


	function isEmpty(str)
	{
		return (!str || 0 === str.length);
	}

	var Item = function (id, name, size, price, count)
	{
		this.id=id;
		this.name = name;
		this.size = size;
		this.price = price;
		this.count = count;
	};


	function addItemToCart(id, name, size, price, count)
	{
		for (var i in cart) {
			if (cart[i].id === id && cart[i].size === size) {
				cart[i].count += count;
				return;
			}
		}
		var item = new Item(id, name, size, price, count);
		cart.push(item);
		saveCart();
	}

	function clearCart()
	{
		cart=[];


		saveCart();
	}

	function saveCart()
	{
		localStorage.setItem("shoppingCart",JSON.stringify(cart));
	}

	function loadCart()
	{
		cart = JSON.parse(localStorage.getItem("shoppingCart"));
			if(cart === null){
			cart=[];
			}
	}




	function removeItemFromCart(name,size,count)
	{
		for (var i in cart) {
			if (cart[i].name === name && cart[i].size===size) {
				cart[i].count--;
				if (cart[i].count === 0)
					cart.splice(i, 1);
				break;
			}

		}
	}

	function removeItemFromCartAll(name,size)
	{
		for (var i in cart) {
			if (cart[i].name === name && cart[i].size === size) {
				cart.splice(i, 1);
				break;

			}

		}
	}
	function plusItemFromCart(name,size,count)
	{
		for (var i in cart) {
			if (cart[i].name === name && cart[i].size === size) {
				cart[i].count++;
				break;
			}

		}
	}



	function listCart()
	{	var cartCopy =[];
		for(var i in cart)
		{
			var item =cart[i];
			var itemCopy={};
			for (var p in item)
			{
				itemCopy[p] = item[p];
			}
			cartCopy.push(itemCopy);
		}
		return cartCopy;
	}








	function sendCart(){
		if(discountList.length>=1){discountList = [];}
		orderList="";
		for(let i in cart){
			if (isEmpty(orderList)) {
				orderList='('+cart[i].id+','+cart[i].size+','+cart[i].count+')';
			}
			else{
				orderList+=",("+cart[i].id+','+cart[i].size+','+cart[i].count+')';
			}
		}

		console.log("orderList to send: "+orderList);
		var checkoutRequest = new XMLHttpRequest();
		checkoutRequest.open('POST', 'http://127.0.0.1:8080/api/checkout');
		checkoutRequest.setRequestHeader("Content-Type", "application/json");
		checkoutRequest.onload = function () {
			checkoutData = JSON.parse(checkoutRequest.responseText);
			//--->debug only
			console.log("Odpowiedź serwera:\n cost: "+checkoutData["cost"]);
			console.log("costDiscount: "+checkoutData["costDiscount"]);
			console.log("discountType: "+checkoutData["discountType"]);
			console.log("discountProductList: "+checkoutData["discountProductList"]);
			//<---
			parseDiscountProductList();
			showSale(checkoutData);
		};
		checkoutRequest.send(JSON.stringify({orderList: orderList}));

	}

	function parseDiscountProductList(){
		console.log("parse discountID to discountProductList")
		let orderId="";
		let orderSize="";
		for(let i=0;i<checkoutData["discountProductList"].length;i++){
			if(checkoutData["discountProductList"].charAt(i)==='('){
				i++;
				while(checkoutData["discountProductList"].charAt(i)!=','){
					orderId+=checkoutData["discountProductList"].charAt(i);
					i++;
				}
				i++;
				while(checkoutData["discountProductList"].charAt(i)!=')'){
					orderSize+=checkoutData["discountProductList"].charAt(i);
					i++;
				}
				var item=decodeProduct(orderId,orderSize);
				discountList.push(item);
				orderId="";
				orderSize="";
			}
		}
		//debug
		for(let i in discountList){
			console.log(discountList[i]);
		}
	}

	function decodeProduct(id,size){
		let data=ourData["productList"]
		let name="";
		let price;
		for(let i in data){
			if(data[i].id === id){
				name=data[i].name;
				switch (size){
					case "S": {
						price=parseFloat(data[i].costS.replace(',','.'));
						break;
					}
					case "M": {
						price=parseFloat(data[i].costM.replace(',','.'));
						break;
					}
					case "L": {
						price=parseFloat(data[i].costL.replace(',','.'));
						break;
					}
					case "U": {
						price=parseFloat(data[i].costU.replace(',','.'));
						break;
					}
				}
			}
		}
		return new Item(id,name,size,price,1);
	}
	loadCart();
	displayCart();


});
