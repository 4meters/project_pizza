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
	var btn="<input type=button class=btn-edit value=Edit>";
	content.insertAdjacentHTML('beforeend', "<h3>Pizza</h3>");

	for (i=0; i<data.length; i++)
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
	}

	content.insertAdjacentHTML('beforeend', htmlString);
	topR.insertAdjacentHTML('beforeend', costString);
}
