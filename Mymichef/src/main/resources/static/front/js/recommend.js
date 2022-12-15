// 추천순 종류
const selectRecommend = document.getElementById("recommend_type");
const recommendList = document.getElementById("recommend_list");

// 팝업 만들기
// recipe: 레시피 리스트
// date: 날짜 일수
// time: 아침, 점심, 저녁 선택 개수
function popupOpen(recipe, date, time) {
  const popDiv = document.getElementById("popup_container");
  const popWrap = document.getElementById("popup_wrap");

  for (let i = 0; i < date; i++) {
    const date = recipe[i * time].date;
    const recipeDiv = `<div class="recipe_wrap" id=${
      "recipe" + i
    }><h1>${date}</h1></div>`;
    popWrap.innerHTML = popWrap.innerHTML + recipeDiv;
  }

  for (let i = 0; i < recipe.length; i++) {
    const name = recipe[i].RECIPE_NM_KO;
    const dbTime = recipe[i].time;
    const nameDiv = `<p class="popup_recipe_name">${name}</p>`;
    const timeDiv = `<p class="popup_recipe_time">${dbTime}</p>`;
    const recipeDiv = document.getElementById("recipe" + Math.floor(i / time));

    recipeDiv.innerHTML = recipeDiv.innerHTML + timeDiv + nameDiv;
  }
  const shoppingBtn = `<button id="shoppingBtn">장바구니 추가</button>`;
  const submitBtn2 = `<button id="submitBtn2">추천확인</button>`;
  popWrap.innerHTML += shoppingBtn + submitBtn2;

  $(function () {
    $("#confirm").click(function () {
      modalClose();
      //컨펌 이벤트 처리
    });
    $("#shoppingBtn").click(function () {
      $("#popup").css("display", "flex").hide().fadeIn();
    });
    $("#close").click(function () {
      modalClose();
    });
    function modalClose() {
      $("#popup").fadeOut();
    }
  });

  // 팝업 제거
  function popupClose() {
    popDiv.style.display = "none";
    popWrap.innerHTML = "";
    console.log("click popup close");
  }

  const submitBtn = document.getElementById("submitBtn2");
  submitBtn.addEventListener("click", popupClose);

  console.log(submitBtn);
  popDiv.style.display = "block";
}

// 두 날짜 일수 차이
const getDateDiff = (d1, d2) => {
  const date1 = new Date(d1);
  const date2 = new Date(d2);

  const diffDate = date1.getTime() - date2.getTime();

  return diffDate / (1000 * 60 * 60 * 24); // 밀리세컨 * 초 * 분 * 시 = 일
};

// 추천순 select
function onRecommend(event) {
  const type = event.target.value;
  if (type === "country") {
    const koreanBtn = `<li><input type="checkbox" name="recommend_country" value="한식" id="korean"><label for="korean">한식</label></li>`;
    const westernBtn = `<li><input type="checkbox" name="recommend_country" value="서양" id="western"><label for="western">양식</label></li>`;
    const italianBtn = `<li><input type="checkbox" name="recommend_country" value="이탈리아" id="italian"><label for="italian">이탈리아</label></li>`;
    const japaneseBtn = `<li><input type="checkbox" name="recommend_country" value="일본" id="japanese"><label for="japanese">일식</label></li>`;
    const chineseBtn = `<li><input type="checkbox" name="recommend_country" value="중국" id="chinese"><label for="chinese">중식</label></li>`;
    const fusionBtn = `<li><input type="checkbox" name="recommend_country" value="퓨전" id="fusion"><label for="fusion">퓨전</label></li>`;
    recommendList.innerHTML =
      `<ul class="recommend-box">` +
      koreanBtn +
      westernBtn +
      italianBtn +
      japaneseBtn +
      chineseBtn +
      fusionBtn +
      `</ul>`;

    // radio 기능
    $(document).ready(function () {
      $(`input[type="checkbox"][name="recommend_country"]`).click(function () {
        if ($(this).prop(`checked`)) {
          $(`input[type="checkbox"][name="recommend_country"]`).prop(
            `checked`,
            false
          );
          $(this).prop(`checked`, true);
        }
      });
    });
  } else if (type === "difficulty") {
    const easyBtn = `<li><input type="checkbox" name="recommend_difficulty" value="초보환영" id="easy"><label for="easy">초보환영</label></li>`;
    const normalBtn = `<li><input type="checkbox" name="recommend_difficulty" value="보통" id="normal"><label for="normal">보통</label></li>`;
    const difficultBtn = `<li><input type="checkbox" name="recommend_difficulty" value="어려움" id="difficult"><label for="difficult">어려움</label></li>`;

    recommendList.innerHTML =
      `<ul class="recommend-box">` +
      easyBtn +
      normalBtn +
      difficultBtn +
      `</ul>`;

    // radio 기능 추가
    $(document).ready(function () {
      $(`input[type="checkbox"][name="recommend_difficulty"]`).click(
        function () {
          if ($(this).prop(`checked`)) {
            $(`input[type="checkbox"][name="recommend_difficulty"]`).prop(
              `checked`,
              false
            );
            $(this).prop(`checked`, true);
          }
        }
      );
    });
  } else {
    recommendList.innerHTML = "";
  }
}

selectRecommend.addEventListener("change", onRecommend);

// 추천받기 버튼
const submitBtn = document.getElementById("submitBtn");
submitBtn.addEventListener("click", (event) => {
  event.preventDefault();
  const recommend_json = {};

  const inputList = document.querySelectorAll("input");
  const startDate = document.getElementById("start");
  const endDate = document.getElementById("end");

  const dishList = [];
  for (let i = 0; i < inputList.length; i++) {
    const element = inputList[i];
    if (element.checked) {
      if (element.name === "dish") {
        dishList.push(element.value);
      }
      if (element.name === "recommend_country") {
        recommend_json.nation = element.value;
      }
      if (element.name === "recommend_difficulty") {
        recommend_json.difficulty = element.value;
      }
    }
  }

  // error
  const errorMsg = document.getElementById("error_msg");
  const today = new Date();
  const start = new Date(startDate.value);
  if (dishList.length === 0) {
    console.log("dish error");
    errorMsg.innerText = "아침, 점심, 저녁 중 최소 하나를 선택해 주세요";
    return;
  }
  if (startDate.value === "" || endDate.value === "") {
    console.log("date error1");
    errorMsg.innerText = "날짜를 선택해 주세요";
    return;
  }
  if (
    startDate.value > endDate.value ||
    Math.ceil(getDateDiff(start, today)) < 0
  ) {
    console.log("date error2");
    errorMsg.innerText = "올바른 날짜를 선택해 주세요";
    return;
  }

  recommend_json.dish = dishList;
  recommend_json.start = startDate.value;
  recommend_json.end = endDate.value;

  const diffDate = getDateDiff(endDate.value, startDate.value) + 1;
  const selectTime = recommend_json.dish.length;
  console.log("날짜 차이: " + diffDate);

  // 서버 통신
  console.log(recommend_json);
  console.log(Object.keys(recommend_json));
  // 나라별 추천
  if (Object.keys(recommend_json).find((value) => value === "nation")) {
    $.ajax({
      url: "/recommend/nation",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(recommend_json),

      success: function (response) {
        console.log("recommend(nation) success");
        const data = JSON.parse(response);
        console.log(data);
        popupOpen(data, diffDate, selectTime);
      },
      error: function (error) {
        console.log("recommend(nation) error");
        console.log(error);
        console.log(error.responseText);
      },
    });
  } else if (
    // 난이도별 추천
    Object.keys(recommend_json).find((value) => value === "difficulty")
  ) {
    $.ajax({
      url: "/recommend/difficulty",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(recommend_json),

      success: function (response) {
        console.log("recommend(difficulty) success");
        const data = JSON.parse(response);
        console.log(data);
        popupOpen(data, diffDate, selectTime);
      },
      error: function (error) {
        console.log("recommend(difficulty) error");
        console.log(error);
        console.log(error.responseText);
      },
    });
  } else {
    // default
    $.ajax({
      url: "/recommend/default",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(recommend_json),

      success: function (response) {
        console.log("recommend success");
        const data = JSON.parse(response);
        console.log(data);
        popupOpen(data, diffDate, selectTime);
      },
      error: function (error) {
        console.log("recommend error");
        console.log(error);
        console.log(error.responseText);
      },
    });
  }
});

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

// $(input[(type = "submit")]).on("click", function (e) {
//   e.preventDefault();
// });

// $(function () {
//   $("#confirm").click(function () {
//     modalClose();
//     //컨펌 이벤트 처리
//   });
//   $("#shoppingBtn").click(function () {
//     $("#popup").css("display", "flex").hide().fadeIn();
//   });
//   $("#close").click(function () {
//     modalClose();
//   });
//   function modalClose() {
//     $("#popup").fadeOut();
//   }
// });
