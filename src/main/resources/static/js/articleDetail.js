const modiBtn = document.getElementById("modi_btn");
const modiModal = document.getElementById("modi_modal");
const modiCloseBtn = document.getElementById("modiClose");
const delBtn = document.getElementById("del_btn");
const delModal = document.getElementById("delete_modal");
const delCloseBtn = document.getElementById("delClose");

delBtn.addEventListener("click", ()=>{
  delModal.style.display = "block";
})

delCloseBtn.addEventListener("click", ()=>{
  delModal.style.display="none";
})

modiBtn.addEventListener("click", ()=>{
  modiModal.style.display = "block";
})

modiCloseBtn.addEventListener("click", ()=>{
  modiModal.style.display="none";
})

