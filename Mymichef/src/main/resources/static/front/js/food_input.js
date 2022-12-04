// 서버 음식 api accept
let ingredientsData = {};
$.ajax({
  url: "##",
  type: "POST",
  contentType: "application/json",
  data: JSON.stringify(ingredientsData),

  success: function (response) {
    console.log("ingredients api post success");
    console.log(response);
  },
  error: function (error) {
    console.log("ingredients api post error");
    console.log(error);
  },
});

$(document).ready(function () {
  ingredientInputInit();
});

function ingredientInputInit() {
  // 식재료 찾기
  const ingredientSearch = document.getElementById("ingredientSearch");
  const ingredientsResult = document.getElementById("ingredientsResult");

  let ingredientsName = [
    { name: "쌀" },
    { name: "감자" },
    { name: "고구마" },
    { name: "당근" },
    { name: "콩나물" },
    { name: "토마토" },
    { name: "배추" },
    { name: "오이" },
    { name: "호박" },
    { name: "양파" },
    { name: "마늘" },
    { name: "돼지고기" },
    { name: "쇠고기" },
    { name: "닭고기" },
  ];
  ingredientsName = ingredientsName.sort((a, b) => {
    if (a.name > b.name) return 1;
    if (a.name < b.name) return -1;
  });

  // 검색창 재료 목록
  for (let i = 0; i < ingredientsName.length; i++) {
    const ingredientName = ingredientsName[i].name;
    const ingredientImg = "../jpg/logo!.png";

    const divName = `<div class="ingredientName">${ingredientName}</div>`;
    const divImg = `<img class="ingredientImg" src=${ingredientImg} alt=${ingredientName}>`;

    ingredientsResult.innerHTML =
      ingredientsResult.innerHTML +
      `<div class="ingredient">` +
      divImg +
      divName +
      `</div>`;
  }

  // 재료 검색
  function onSearch(event) {
    const search = event.target.value.toLowerCase();
    const items = document.getElementsByClassName("ingredient");

    for (let i = 0; i < items.length; i++) {
      const item = items[i].getElementsByClassName("ingredientName");
      if (item[0].innerHTML.toLowerCase().indexOf(search) !== -1) {
        items[i].style.display = "inline-block";
      } else {
        items[i].style.display = "none";
      }
    }
  }
  ingredientSearch.addEventListener("keyup", onSearch);

  // 재료 정보 입력
  const infoForm = document.getElementById("ingredientInfoForm");
  const infoName = document.getElementById("infoName");
  const items = Array.from(document.getElementsByClassName("ingredient"));

  // 재로 클릭 이벤트
  function onResultClick(event) {
    const id = event.target.id;
    console.log(event);
    console.log(id);
  }
  items.forEach((item) => item.addEventListener("click", onResultClick));

  // 사용자 재료 리스트
}

(function () {
  "use strict";

  document
    .querySelector(".material-design-hamburger__icon")
    .addEventListener("click", function () {
      var child;

      document.body.classList.toggle("background--blur");
      this.parentNode.nextElementSibling.classList.toggle("menu--on");

      child = this.childNodes[1].classList;

      if (child.contains("material-design-hamburger__icon--to-arrow")) {
        child.remove("material-design-hamburger__icon--to-arrow");
        child.add("material-design-hamburger__icon--from-arrow");
      } else {
        child.remove("material-design-hamburger__icon--from-arrow");
        child.add("material-design-hamburger__icon--to-arrow");
      }
    });
})();
