const data1 = [];
const data2 = [];

$.ajax({
  url: "/mypage/userInfo",
  type: "POST",
  contentType: "application/json",
  data: JSON.stringify(data1),

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
$.ajax({
  url: "/updateUserInfo",
  type: "POST",
  contentType: "application/json",
  data: JSON.stringify(data2),

  success: function (response) {
    console.log("updateUserInfo success");
    console.log(response);
  },
  error: function (error) {
    console.log("updateUserInfo error");
    console.log(error);
    console.log(error.responseText);
  },
});

(function () {
  "use strict";

  document
    .querySelector(".material-design-hamburger__icon")
    .addEventListener("click", function () {
      console.log("click");
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

function completeEdit()  {
  const btnElement = document.getElementById('editBtn');
  btnElement.innerText = '저장';
}
