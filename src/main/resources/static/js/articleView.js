document.addEventListener("DOMContentLoaded", function() {
  const delBtn = document.getElementById("del_btn");
  const modiBtn = document.getElementById("modi_btn");

  const queryString = location.search;
  const urlParams = new URLSearchParams(queryString);
  const articleId = urlParams.get("id");
  const POPUPOPTION = "width=700, height=250, top=140, right=100";
  const URL = "/passwordVerificationPopup";

  modiBtn.onclick = function (){
    window.open(URL+"?action=modi&id="+articleId,null, POPUPOPTION);
  }
  delBtn.onclick = function (){
    window.open(URL+"?action=del&id="+articleId,null, POPUPOPTION);
  }
})
