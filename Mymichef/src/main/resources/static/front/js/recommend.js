let data1 = [];
let data2 = [];

$.ajax({
  url: "/recommend/default",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(data1),

  success: function (response) {
    console.log("recommend success");
    console.log(response);
    const userData = JSON.parse(response);
    console.log("RECIPE_NM_KO: " + userData.RECIPE_NM_KO);
  },
  error: function (error) {
    console.log("recommend error");
    console.log(error);
    console.log(error.responseText);
  },
});

setTimeout(function () {
  $.ajax({
    url: "/recommend/save",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(data2),

    success: function (response) {
      console.log("recommendInfo save success");
      console.log(response);
    },
    error: function (error) {
      console.log("recommendInfo save error");
      console.log(error);
      console.log(error.responseText);
    },
  });
  console.log("Works!");
}, 3000);

$.ajax({
  url: "/recommend/nation",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(data1),

  success: function (response) {
    console.log("recommend(nation) success");
    console.log(response);
  },
  error: function (error) {
    console.log("recommend(nation) error");
    console.log(error);
    console.log(error.responseText);
  },
});

// 추천순 종류
const selectRecommend = document.getElementById("recommend_type");
const recommendList = document.getElementById("recommend_list");

// 추천순 select
function onRecommend(event) {
  const type = event.target.value;
  if (type === "country") {
    const koreanBtn = `<li><input type="checkbox" name="recommend_country" value="korean" id="korean"><label for="korean">한식</label></li>`;
    const westernBtn = `<li><input type="checkbox" name="recommend_country" value="western" id="western"><label for="western">양식</label></li>`;
    const italianBtn = `<li><input type="checkbox" name="recommend_country" value="italian" id="italian"><label for="italian">이탈리아</label></li>`;
    const japaneseBtn = `<li><input type="checkbox" name="recommend_country" value="japanese" id="japanese"><label for="japanese">일식</label></li>`;
    const chineseBtn = `<li><input type="checkbox" name="recommend_country" value="chinese" id="chinese"><label for="chinese">중식</label></li>`;
    const fusionBtn = `<li><input type="checkbox" name="recommend_country" value="fusion" id="fusion"><label for="fusion">퓨전</label></li>`;
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
    const easyBtn = `<li><input type="checkbox" name="recommend_difficulty" value="easy" id="easy"><label for="easy">초보환영</label></li>`;
    const normalBtn = `<li><input type="checkbox" name="recommend_difficulty" value="normal" id="normal"><label for="normal">보통</label></li>`;
    const difficultBtn = `<li><input type="checkbox" name="recommend_difficulty" value="difficult" id="difficult"><label for="difficult">어려움</label></li>`;

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
      if (
        element.name === "recommend_country" ||
        element.name === "recommend_difficulty"
      ) {
        recommend_json.recommend = element.value;
      }
    }
  }

  // error
  const errorMsg = document.getElementById("error_msg");
  if (dishList.length === 0) {
    console.log("dish error");
    errorMsg.innerText = "아침, 점심, 저녁 중 최소 하나를 선택해 주세요";
  }
  if (startDate.value === "" || endDate.value === "") {
    console.log("date error1");
    errorMsg.innerText = "날짜를 선택해 주세요";
  }
  if (startDate.value > endDate.value) {
    console.log("date error2");
    errorMsg.innerText = "올바른 날짜를 선택해 주세요";
  }

  recommend_json.dish = dishList;
  recommend_json.start = startDate.value;
  recommend_json.end = endDate.value;

  console.log(recommend_json);
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
