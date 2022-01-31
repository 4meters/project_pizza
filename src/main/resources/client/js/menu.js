var content = document.getElementById("content");
var topR = document.getElementById("topR");
var content2 = document.getElementById("content2");


var ourRequset = new XMLHttpRequest();
ourRequset.open('GET', 'http://127.0.0.1:8080/api/product/get-productlist');
ourRequset.onload =function() {
		var ourData = JSON.parse(ourRequset.responseText);
		renderHTML(ourData["productList"]);

};
ourRequset.send();





function renderHTML(data){
	var htmlString=" ";
	var costString=" ";
	var maxPizzaID=0;
	var maxDrinkID=0;

	var pizzaList=[];
	var drinkList=[];
	var setList=[];

	var btn="<input type=button class=btn-edit value=Edit>";
	content.insertAdjacentHTML('beforeend', "<h3>Pizza</h3>");

	/*for (i=0; i<data.length; i++)
	{
		if(data[i].type!=0)break;
		htmlString+="<a href=editProduct.html?idProduct=" + data[i].id + " id=edit>" +data[i].id +".  "+ data[i].name +" "+ data[i].description +" </a>";
		costString+="<p >" + data[i].costS +"zł "+ data[i].costM +"zł "+ data[i].costL +"zł </p>";

	}
	maxPizzaID = i;
	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString );


	htmlString=" ";
	costString=" ";
	//maxDrinkID = maxPizzaID;
	content.insertAdjacentHTML('beforeend', "<p>Napoje</p>");
	topR.insertAdjacentHTML('beforeend', "Cennik Napojów");
	for (i=maxPizzaID; i<data.length; i++)
	{
		if(data[i].type!=1)break;
		htmlString+="<a href=editProduct.html?idProduct=" + data[i].id + " id=edit>" +data[i].id +".  "+ data[i].name +"  </a>";
		costString+="<p>" + data[i].costU +"zł </p>";
		maxDrinkID+=1
		//maxDrinkID++;
	}
	maxDrinkID=i;
	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString);

	htmlString = " ";
	costString = " ";

	content.insertAdjacentHTML('beforeend', "<p>Zestawy</p> ");
	topR.insertAdjacentHTML('beforeend', "Cennik Zestawów");


	for (i = maxDrinkID; i < data.length; i++){
		htmlString+="<a href=editProduct.html?idProduct=" + data[i].id + " id=edit>" +data[i].id +".  "+ data[i].name +" "+ data[i].description +" </a>";
		costString +="<p>" + data[i].costU +"zł </p>";
	}*/

	for (let i=0; i<data.length; i++)
	{
		switch(data[i].type){
			case 0:
				pizzaList.push(data[i]);
				break;
			case 1:
				drinkList.push(data[i]);
				break;
			case 2:
				setList.push(data[i]);
				break;
		}
	}

	for (let i=0; i<pizzaList.length; i++)
	{
		htmlString+="<a href=editProduct.html?idProduct=" + pizzaList[i].id + " id=edit>" +pizzaList[i].id +".  "+ pizzaList[i].name +" "+ pizzaList[i].description +" </a>";
		costString+="<p >" + pizzaList[i].costS +"zł "+ pizzaList[i].costM +"zł "+ pizzaList[i].costL +"zł </p>";

	}

	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString );


	htmlString=" ";
	costString=" ";
	//maxDrinkID = maxPizzaID;
	content.insertAdjacentHTML('beforeend', "<p>Napoje</p>");
	topR.insertAdjacentHTML('beforeend', "Cennik Napojów");

	for (let i=0; i<drinkList.length; i++)
	{

		htmlString+="<a href=editProduct.html?idProduct=" + drinkList[i].id + " id=edit>" +drinkList[i].id +".  "+ drinkList[i].name +"  </a>";
		costString+="<p>" + drinkList[i].costU +"zł </p>";
		maxDrinkID+=1
		//maxDrinkID++;
	}

	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString);

	htmlString = " ";
	costString = " ";

	content.insertAdjacentHTML('beforeend', "<p>Zestawy</p> ");
	topR.insertAdjacentHTML('beforeend', "Cennik Zestawów");


	for (let i = 0; i < setList.length; i++){
		htmlString+="<a href=editProduct.html?idProduct=" + setList[i].id + " id=edit>" +setList[i].id +".  "+ setList[i].name +" "+ setList[i].description +" </a>";
		costString +="<p>" + setList[i].costU +"zł </p>";
	}

	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString);
}
