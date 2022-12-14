let data1 = [];
let data2 = [];

$.ajax({
  url: "/mypage/userInfo",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(data1),

  success: function (response) {
    console.log("userInfo success");
    console.log(response);
    const userData = JSON.parse(response);
    console.log(typeof response);
    console.log("name: " + userData.name);
    console.log("email: " + userData.email);
    console.log("phone: " + userData.phoneNumber);
    console.log("height: " + userData.height);
    console.log("weight: " + userData.weight);
    console.log("cal: " + userData.calory);

    const inputName = document.getElementById("name");
    const inputEmail = document.getElementById("email");
    const inputPhone = document.getElementById("phone");
    const inputHeight = document.getElementById("height");
    const inputWeight = document.getElementById("weight");
    const inputCal = document.getElementById("cal");

    inputName.value = userData.name;
    inputEmail.value = userData.email;
    inputPhone.value = userData.phoneNumber;
    inputHeight.value = userData.height;
    inputWeight.value = userData.weight;
  },
  error: function (error) {
    console.log("userInfo error");
    console.log(error);
    console.log(error.responseText);
  },
});

// 정보 업데이트
function updateInfo() {
  const inputName = document.getElementById("name");
  const inputEmail = document.getElementById("email");
  const inputPhone = document.getElementById("phone");
  const inputHeight = document.getElementById("height");
  const inputWeight = document.getElementById("weight");
  const inputCal = document.getElementById("cal");
  // const inputGender = document.getElementById();

  console.log(typeof userData);
}

updateInfo();

const parentForm = document.querySelector(".container");

// 정보 수정

parentForm.addEventListener("submit", (event) => {
  if(confirm('저장하시겠습니까?')){
    return;
  }
  event.preventDefault();

  const inputName = event.target[0].value;
  const inputEmail = event.target[1].value;

  let inputPhoneNumber = event.target[4].value;

  let inputHeight = event.target[5].value;

  let inputWeight = event.target[6].value;

  let inputGender = event.target[8].value;
  /*와ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ 못해먹겠다 js 후하후하,,,,,,,,,,,,SOS...*/

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

function undoEdit() {
      if(!confirm('취소하시겠습니까?새로 입력한 정보가 사라집니다.')){
        return;
      }

  $(".button--trigger").show();
  $(this).removeClass("active").addClass("out");

  setTimeout(function () {
    $(".button--disappear").hide();
  }, 0); //Same time as animation
}

$(".button--trigger").on("click", function (e) {
  $(".button--trigger").hide();
  $(".button--disappear").show();
  $(".button--disappear").removeClass("out").addClass("active");
});
