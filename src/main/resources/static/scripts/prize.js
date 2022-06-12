function createMainPrize(totalPrize){
	console.log(totalPrize);
	var prize = '';
	var prizes = 1
	for (var i = 0; i < 1; i++) {
        
        prize = prize+'<form action="#" th:action="@{/mainService/doRandomWinner}"  method="post">'+
        '<div>'+
        '<div name="prizeName">'+prizes+'等獎</div>'+
        '</div>'+
        '<div>'+
        '<div>人數</div>'+
        '<input name="prizePeople"></input>'+
        '</div>'+
        '<div>'+
        '<input type="submit" text="抽獎">'+
        '</div>'+
        '</form>'
    prizes++
    }
return prize;
}

