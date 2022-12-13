// 서버 음식 api accept
let ingredientList = {};
$.ajax({
  url: "/food/list",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(ingredientList),

  success: function (response) {
    console.log("userInfo success");
    console.log(response);
    const foodsUnit = JSON.parse(response);

    console.log(foodsUnit);
    let ingredientsData = [];

    let i = 0;
    for (let key in foodsUnit) {
      const ingredient = {};

      ingredient.name = key;
      ingredient.unit = foodsUnit[key];
      ingredientsData.push(ingredient);
    }

    // 오름차순 정렬
    ingredientsData = ingredientsData.sort((a, b) => {
      if (a.name > b.name) return 1;
      if (a.name < b.name) return -1;
    });

    const ingredientsResult = document.getElementById("ingredientsResult");
    for (let i = 0; i < ingredientsData.length; i++) {
      const ingredientName = ingredientsData[i].name;
      const ingredientImg = "../jpg/logo!.png";

      const divName = `<div class="ingredientName" id=${
        ingredientName + "name"
      }>${ingredientName}</div>`;
      const divImg = `<img class="ingredientImg" id=${
        ingredientName + "img"
      } src=${ingredientImg} alt=${ingredientName}>`;

      ingredientsResult.innerHTML =
        ingredientsResult.innerHTML +
        `<div class="ingredient" id=${ingredientName}>` +
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
    const ingredientSearch = document.getElementById("ingredientSearch");
    ingredientSearch.addEventListener("keyup", onSearch);

    const infoForm = document.getElementById("ingredientInfoForm");
    const infoDiv = document.getElementById("ingredientInfo");
    const infoName = document.getElementById("infoName");
    const items = Array.from(document.getElementsByClassName("ingredient"));

    // 재로 클릭 이벤트
    function onResultClick(event) {
      const id = event.target.id;
      let clickItem;
      let itemImg;

      for (let i = 0; i < ingredientsData.length; i++) {
        const element = ingredientsData[i].name;
        if (
          id === element ||
          id === element + "name" ||
          id === element + "img"
        ) {
          clickItem = element;
          itemImg = `<img class="infoImg" src=${clickItem} alt=${clickItem}>`;
          infoForm.style.display = "flex";
        }
      }
      infoName.innerText = clickItem;
      // infoDiv.innerHTML += itemImg;

      let inputData = {
        name: clickItem,
      };
    }
    items.forEach((item) => item.addEventListener("click", onResultClick));
  },
  error: function (error) {
    console.log("userInfo error");
    console.log(error);
    console.log(error.responseText);
  },
});

$.ajax({
  url: "/food/check",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(ingred),

  success: function (response) {
    console.log("userInfo success");
    console.log(response);
  },
  error: function (error) {
    console.log("userInfo error");
    console.log(error);
    console.log(error.responseText);
  },
});
//$.ajax({
//  url: "/food/input",
//  type: "POST",
//  contentType: "application/json",
//  data: JSON.stringify(ingredientsData),
//
//  success: function (response) {
//    console.log("ingredients api post success");
//    console.log(response);
//  },
//  error: function (error) {
//    console.log("ingredients api post error");
//    console.log(error);
//  },
//});

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
