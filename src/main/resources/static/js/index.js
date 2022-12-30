document.addEventListener("DOMContentLoaded", function() {
  const startDate = document.querySelector(".start_date");
  const endDate = document.querySelector(".end_date");
  const category = document.querySelector("#category");
  function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
  }
  startDate.value = searchParam("startDate");
  endDate.value = searchParam("endDate");
  if (searchParam("category")){
    category.value = searchParam("category");
  }
})
