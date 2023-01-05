document.addEventListener("DOMContentLoaded", function() {
  const existFileRowOne = document.getElementById("exist_file_row0");
  const existFileRowTwo = document.getElementById("exist_file_row1");
  const existFileRowThree = document.getElementById("exist_file_row2");
  const existOneDeleteBtn = document.getElementById("delete_row0");
  const existTwoDeleteBtn = document.getElementById("delete_row1");
  const existThreeDeleteBtn = document.getElementById("delete_row2");
  const existFileOneId = document.getElementById("file_uuid0");
  const existFileTwoId = document.getElementById("file_uuid1");
  const existFileThreeId = document.getElementById("file_uuid2");
  const restoreBtnOne = document.getElementById("restoreBtn0");
  const restoreBtnTwo = document.getElementById("restoreBtn1");
  const restoreBtnThree = document.getElementById("restoreBtn2");
  const restoreRowOne = document.getElementById("restoreRow0");
  const restoreRowTwo = document.getElementById("restoreRow1");
  const restoreRowThree = document.getElementById("restoreRow2")

  const fileOne = document.querySelector("#file1");
  const fileOnePlaceholder = document.querySelector(".upload_name1");
  const fileTwo = document.querySelector("#file2");
  const fileTwoPlaceholder = document.querySelector(".upload_name2");
  const fileThree = document.querySelector("#file3");
  const fileThreePlaceholder = document.querySelector(".upload_name3");
  const deleteFileList = document.getElementById("deleteFileList");
  const submitBtn = document.querySelector(".save_button");

  let deletingFiles = [];

  // 제출 버튼 클릭 시 삭제 파일 명단 리스트로 넘겨주기
  submitBtn.addEventListener("click", ()=>{
    deleteFileList.value = deletingFiles;
  })

  // 리스트에서 특정값제거위한 함수
  function removeListItem(value){
    const index = deletingFiles.indexOf(value);
    if (index > -1){
      deletingFiles.splice(index, 1);
    }
  }

  // 게시글에 따른 첫번째 파일이 있을경우
  if (existOneDeleteBtn){
    // 삭제(예정) 버튼 클릭 시,
    existOneDeleteBtn.addEventListener("click",()=>{
      existFileRowOne.style.display = "none";
      deletingFiles.push(existFileOneId.value);
      restoreRowOne.style.display="flex";
    })
    // 복구 버튼 (삭제취소) 클릭 시,
    restoreBtnOne.addEventListener("click", ()=>{
      existFileRowOne.style.display = "flex";
      removeListItem(existFileOneId.value);
      restoreRowOne.style.display="none";
    })
  }

  // 두번째파일
  if (existTwoDeleteBtn){
    existTwoDeleteBtn.addEventListener("click",()=>{
      existFileRowTwo.style.display = "none";
      deletingFiles.push(existFileTwoId.value);
      restoreRowTwo.style.display="flex";
    })
    restoreBtnTwo.addEventListener("click", ()=>{
      existFileRowTwo.style.display = "flex";
      removeListItem(existFileTwoId.value);
      restoreRowTwo.style.display="none";
    })
  }


  // 세번째파일
  if(existThreeDeleteBtn){
    existThreeDeleteBtn.addEventListener("click",()=>{
      existFileRowThree.style.display = "none";
      deletingFiles.push(existFileThreeId.value);
      restoreRowThree.style.display="flex";
  })
    restoreBtnThree.addEventListener("click", ()=>{
      existFileRowThree.style.display = "flex";
      removeListItem(existFileThreeId.value);
      restoreRowThree.style.display="none";
    })
}


  fileOne.addEventListener("change", (e) => {
    fileOnePlaceholder.value = e.target.value;
  })
  fileTwo.addEventListener("change", (e) => {
    fileTwoPlaceholder.value = e.target.value;
  })
  fileThree.addEventListener("change", (e) => {
    fileThreePlaceholder.value = e.target.value;
  })
})
