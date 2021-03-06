const save = document.querySelector("#btn-save");

function handleToDoSave(event) {
    event.preventDefault();
	
	let data = {
		title: document.querySelector("#title").value,
		content: document.querySelector("#content").value,
	};
	
//	console.log(data) //자바스크립트 오브젝트
//	console.log(JSON.stringify(data)) //json 오브젝트
	
	//ajax 호출시 default가 비동기 호출
	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert
	// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
	$.ajax({
		type:"POST",
		url:"/api/board",
		data:JSON.stringify(data),  //HTTP body data > MIME 타입 지정 필요
		contentType:"application/json; charset=utf-8",
		dataType:"json"  
		//요청을 서버로해서 응답이 왔을 때 
		//기본적으로 모든 것이 String(생긴게 json이라면 javascript obect로 변경)
	}).done(function(resp){
		alert("글쓰기가 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
	
}
if(save != null){
save.addEventListener("click", handleToDoSave);
}



const deleteId = document.querySelector("#btn-delete");

function handleToDoDelete(event) {
    event.preventDefault();
	
	 const id =  document.querySelector("#id").textContent;
	$.ajax({
		type:"DELETE",
		url:"/api/board/"+id,
		dataType:"json"  
	}).done(function(resp){
		alert("삭제가 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
	
}
if(deleteId != null){
deleteId.addEventListener("click", handleToDoDelete)
}



const updateDo = document.querySelector("#btn-update");

function handleToDoUpdate(event) {
    event.preventDefault();
		let id = document.querySelector("#id").value;
		
		let data = {
		title: document.querySelector("#title").value,
		content: document.querySelector("#content").value,
	};
	
	$.ajax({
		type:"PUT",
		url:"/api/board/"+id,
		data:JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		dataType:"json"  
	}).done(function(resp){
		alert("글수정이 완료되었습니다.");
		location.href="/";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
	
}
if(updateDo != null){
updateDo.addEventListener("click", handleToDoUpdate)
}

const replySave = document.querySelector("#btn-reply-save");

function handleToDoReply(event) {
    event.preventDefault();
	
	let data = {
		content: document.querySelector("#reply-content").value,
		};
	console.log(data);
	
	const boardId = document.querySelector("#boardId").value;
	
	$.ajax({
		type:"POST",
		url:`/api/board/${boardId}/reply`,
		data:JSON.stringify(data),
		contentType:"application/json; charset=utf-8",
		dataType:"json"  
	}).done(function(resp){
		alert("댓글작성이 완료되었습니다.");
		location.href=`/board/${boardId}`;
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); 
	
}
if(replySave != null){
replySave.addEventListener("click", handleToDoReply)
}


