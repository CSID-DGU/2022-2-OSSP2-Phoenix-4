// 서버 음식 api accept
let foodsData = {};
$.ajax({
  url: "##",
  type: "POST",
  contentType: "application/json",
  data: JSON.stringify(foodsData),

  success: function (response) {
    console.log("foods api post success");
    console.log(response);
  },
  error: function (error) {
    console.log("foods api post error");
    console.log(error);
  },
});

function foodInputInit() {
  const foodsname = [
    "a",
    "b",
    "c",
    "d",
    "e",
    "f",
    "g",
    "h",
    "i",
    "j",
    "k",
    "l",
    "m",
  ];
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
