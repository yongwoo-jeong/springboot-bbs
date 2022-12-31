document.addEventListener("DOMContentLoaded", function() {
  const delBtn = document.getElementById("del_btn");
  const modiBtn = document.getElementById("modi_btn");
  const POPUPOPTION = "width=700, height=250, top=140, right=100";
  const URL = "/passwordVerificationPopup";
  modiBtn.onclick = function (){
    window.open(URL+"?action=modi",null, POPUPOPTION);
  }
  delBtn.onclick = function (){
    window.open(URL+"?action=del",null, POPUPOPTION);
  }
})
