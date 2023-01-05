document.addEventListener("DOMContentLoaded", function() {
  const modalFade = document.querySelector(".modal-fade");
  const delBtn = document.getElementById("del_btn");
  const modiBtn = document.getElementById("modi_btn");
  const delModal = document.getElementById("delete_modal");
  const closeBtn = document.querySelector(".close");

  delBtn.addEventListener("click", ()=>{
    delModal.style.display = "block";
  })

  closeBtn.addEventListener("click", ()=>{
    delModal.style.display="none";
  })

})
