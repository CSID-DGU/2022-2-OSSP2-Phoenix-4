let data1 = [];
let data2 = [];

$.ajax({
  url: "/mypage/userInfo",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(data1),

  success: function (response) {
    console.log("userInfo success");
    const userData = JSON.parse(response);
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

document.addEventListener("DOMContentLoaded", () => {
  const parentForm = document.querySelector("#ModifyAccount");
  const userdata = {};

  parentForm.addEventListener("submit", (event) => {
    if (confirm("저장하시겠습니까?")) {
      event.preventDefault();

      const inputName = event.target[0].value;
      const inputEmail = event.target[1].value;
      const inputphoneNumber = event.target[2].value;
      const inputHeight = event.target[3].value;
      const inputWeight = event.target[4].value;
      const inputCal = event.target[5].value;
      const inputGender = event.target[6].value;
      if (
        inputName !== "" &&
        inputEmail !== "" &&
        inputphoneNumber !== "" &&
        inputHeight !== "" &&
        inputWeight !== ""
      ) {
        userdata.name = inputName;
        userdata.email = inputEmail;
        userdata.phoneNumber = inputphoneNumber;
        userdata.height = inputHeight;
        userdata.weight = inputWeight;

        $.ajax({
          url: "/mypage/updateUserInfo",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(userdata),

          success: function (response) {
            console.log("updateUserInfo success");
            console.log(response);
            alert("저장이 완료되었습니다.");
            window.location.replace("http://localhost:8080/mypage");
          },
          error: function (error) {
            console.log("updateUserInfo error");
            console.log(error);
            console.log(error.responseText);
          },
        });
      }
      return;
    }
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
  if (!confirm("취소하시겠습니까?새로 입력한 정보가 사라집니다.")) {
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

$("#chat-label").click(function () {
  $("#live-chat").toggleClass("open-chat");
  $("#chat-label").toggleClass("open-chat-label");
  $(".live-chat-up-arrow").toggleClass("hide-up-arrow");
  $(".live-chat-down-arrow").toggleClass("show-down-arrow");
});

var expression = "";
var reset = false;

function updateDisplay(val) {
  // console.log(val);
  expression = expression + val;
  document.getElementById("display").innerHTML = expression;
}

function updateDisplayNumber(val) {
  // console.log(val);
  if (reset) expression = val;
  else expression = expression + val;
  document.getElementById("display").innerHTML = expression;
}

function computeExpression() {
  // console.log(expression)
  var result = eval(expression);
  document.getElementById("display").innerHTML = result;
  expression = result;
}

function clearDisplay() {
  expression = "";
  reset = true;
  // console.log(expression);
  document.getElementById("display").innerHTML = "0";
}

function deleteChar() {
  if (expression.length > 1) {
    expression = expression.substr(0, expression.length - 1);
    document.getElementById("display").innerHTML = expression;
  } else {
    document.getElementById("display").innerHTML = "0";
  }
}

function computePercent() {}

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function () {
  modal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
};
