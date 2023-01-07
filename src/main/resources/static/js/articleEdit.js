const fileOne = document.querySelector("#file1");
const fileOnePlaceholder = document.querySelector(".upload_name1");
const fileTwo = document.querySelector("#file2");
const fileTwoPlaceholder = document.querySelector(".upload_name2");
const fileThree = document.querySelector("#file3");
const fileThreePlaceholder = document.querySelector(".upload_name3");
const deleteFileList = document.getElementById("deleteFileList");
const submitBtn = document.querySelector(".save_button");

let deletingFiles = [];

// deletingFiles 리스트에서 특정값제거위한 함수
/**
 * 삭제취소를 위해 deletingFiles 리스트에서 특정값제거위한 함수
 * @param value file uuid
 */
function removeListItem(value){
  const index = deletingFiles.indexOf(value);
  if (index > -1){
    deletingFiles.splice(index, 1);
  }
}

/**
 * 서버에 존재하는 파일 클래스
 */
class fileOnServer{
  constructor(fileContainer, fileUuid) {
    this.fileContainer=fileContainer;
    this.fileUuid=fileUuid;
  }

  /**
   * 파일 삭제(staged)
   */
  deleteStaged(){
    this.fileContainer.style.display="none";
    deletingFiles.push(this.fileUuid);
  }

  /**
   * 파일 삭제 (unstaged)
   */
  unStage(){
    this.fileContainer.style.display="flex";
    removeListItem(this.fileUuid);
  }
}

// 제출 버튼 클릭 시 삭제 파일 명단 리스트 value set
submitBtn.addEventListener("click", ()=>{
  deleteFileList.value = deletingFiles;
})

for (let i = 0; i<3; i++){
  let delBtn = document.getElementById("delete_row"+i);
  let restoreBtnOne = document.getElementById("restoreBtn"+i);
  if (delBtn) {
    let fileRow = document.getElementById("exist_file_row"+i);
    let fileUuid = document.getElementById("file_uuid"+i).value;
    let storedFile = new fileOnServer(fileRow, fileUuid);

    delBtn.addEventListener("click", ()=>{
      storedFile.deleteStaged();
    })

    restoreBtnOne.addEventListener("click",()=>{
      storedFile.unStage();
    })

  }
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
