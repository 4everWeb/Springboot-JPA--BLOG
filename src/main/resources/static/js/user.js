const save = document.querySelector("#btn-save");

function handleToDoSave(event) {
    event.preventDefault();
	
	let data = {
		username: document.querySelector("#username").value,
		password: document.querySelector("#password").value,
		email: document.querySelector("#email").value
	};
	
//	console.log(data) //자바스크립트 오브젝트
//	console.log(JSON.stringify(data)) //json 오브젝트
	
	//ajax 호출시 default가 비동기 호출
	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert
	// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
	$.ajax({
		type:"POST",
		url:"/api/user",
		data:JSON.stringify(data),  //HTTP body data > MIME 타입 지정 필요
		contentType:"application/json; charset=utf-8",
		dataType:"json"  
		//요청을 서버로해서 응답이 왔을 때 
		//기본적으로 모든 것이 String(생긴게 json이라면 javascript obect로 변경)
	}).done(function(resp){
		alert("회원가입이 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
	
}

save.addEventListener("click", handleToDoSave)











