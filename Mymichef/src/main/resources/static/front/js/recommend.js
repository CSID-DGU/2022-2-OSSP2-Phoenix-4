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

$(input[(type = "submit")]).on("click", function (e) {
  e.preventDefault();
});
