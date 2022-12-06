const data1 = [];
const data2 = [];

document.addEventListener("DOMContentLoaded",()=>{
    const parentForm=document.querySelector("#parent");
/*와ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ 못해먹겠다 js 후하후하,,,,,,,,,,,,SOS...*/


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



/*와ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ 못해먹겠다 js 후하후하,,,,,,,,,,,,SOS...*/
parentForm.addEventListener("submit",(event) => {
    event.preventDefault();

    const inputName=event.target[0].value;
    const

    inputEmail = event.target[1].value;

    inputPassword = event.target[2].value;

    inputPhoneNumber=event.target[4].value;

    inputHeight=event.target[5].value;

    inputWeight=event.target[6].value;

    inputAllergy=event.target[7].value;

    inputGender=event.target[8].value;
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
}
);

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


function undoEdit()  {
  $('.button--trigger').show()
    $(this).removeClass('active').addClass('out');

        setTimeout(function(){
          $('.button--disappear').hide()
        }, 0); //Same time as animation
}

$('.button--trigger').on('click', function(e) {
  $('.button--trigger').hide()
  $('.button--disappear').show();
  $('.button--disappear').removeClass('out').addClass('active');
});
});

