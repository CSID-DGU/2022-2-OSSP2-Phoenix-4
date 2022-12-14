function setFormMessage(formElement, type, message) {
  const messageElement = formElement.querySelector(".form__message");

  messageElement.textContent = message;
  messageElement.classList.remove(
    "form__message--success",
    "form__message--error"
  );
  messageElement.classList.add(`form__message--${type}`);
}

function setInputError(inputElement, message) {
  inputElement.classList.add("form__input--error");
  inputElement.parentElement.querySelector(
    ".form__input-error-message"
  ).textContent = message;
}

function clearInputError(inputElement) {
  inputElement.classList.remove("form__input--error");
  inputElement.parentElement.querySelector(
    ".form__input-error-message"
  ).textContent = "";
}

document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.querySelector("#login");
  const findIDForm = document.querySelector("#findID");
  const findPasswordForm = document.querySelector("#findPassword");
  const createAccountForm = document.querySelector("#createAccount");

  document.querySelector("#linkFindID").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.add("form--hidden");
    findIDForm.classList.remove("form--hidden");
  });

  document.querySelector("#id_linkLogin").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.remove("form--hidden");
    findIDForm.classList.add("form--hidden");
  });

  document.querySelector("#linkFindPassword").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.add("form--hidden");
    findPasswordForm.classList.remove("form--hidden");
  });

  document.querySelector("#pw_linkLogin").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.remove("form--hidden");
    findPasswordForm.classList.add("form--hidden");
  });

  document
    .querySelector("#linkCreateAccount")
    .addEventListener("click", (e) => {
      e.preventDefault();
      loginForm.classList.add("form--hidden");
      createAccountForm.classList.remove("form--hidden");
    });

  document.querySelector("#linkLogin").addEventListener("click", (e) => {
    e.preventDefault();
    loginForm.classList.remove("form--hidden");
    createAccountForm.classList.add("form--hidden");
  });
  const data = {};

  // ID ?????? ????????? ?????????
  findIDForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const inputName = event.target[0].value;
    const inputEmail = event.target[1].value;

    if (inputName !== "" && inputEmail !== "") {
      data.name = inputName;
      data.email = inputEmail;

      $.ajax({
        url: "/findId",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),

        success: function (response) {
          console.log("????????? ?????? ?????? ??????");
          const userId = JSON.parse(response);
          alert(userId.userId);
        },
        error: function (error) {
          console.log("????????? ?????? ?????? ??????");
        },
      });
    }
  });

  // Password ?????? ????????? ?????????
  findPasswordForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const inputName = event.target[0].value;
    const inputID = event.target[1].value;
    const inputEmail = event.target[2].value;
    if (inputName !== "" && inputID !== "" && inputEmail !== "") {
      data.name = inputName;
      data.userId = inputID;
      data.email = inputEmail;

      $.ajax({
        url: "/findPw",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),

        success: function (response) {
          console.log("???????????? ?????? post success");
          const userPw = JSON.parse(response);
          alert(userPw.password);
        },
        error: function (error) {
          console.log("???????????? ?????? post error");
        },
      });
    }
  });

  // ???????????? ????????? ?????????
  createAccountForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const inputID = event.target[0].value;
    const inputEmail = event.target[1].value;
    const inputPw = event.target[2].value;
    const inputConfirmPw = event.target[3].value;

    if (inputID === "") {
      alert("???????????? ???????????? ??????.");
    } else if (inputEmail === "") {
      alert("???????????? ????????? ?????????.");
    } else if (inputPw === "" || inputConfirmPw === "") {
      alert("??????????????? ????????? ?????????.");
    } else if (inputPw !== inputConfirmPw) {
      alert("??? ??????????????? ???????????? ????????????.");
    } else if (
      inputID !== "" &&
      inputEmail !== "" &&
      inputPw !== "" &&
      inputPw === inputConfirmPw
    ) {
      // ????????? ??????
      data.userId = inputID;
      data.email = inputEmail;
      data.password = inputPw;

      $.ajax({
        type: "POST",
        url: "/join",
        contentType: "application/json",
        data: JSON.stringify(data),
        //        dataType: "json",
        success: function (response) {
          console.log("???????????? post success");
          if (response === "java.lang.Exception: ??????????????? ??????????????????!") {
            alert("??????????????? ?????????????????????.");
            window.location.replace("http://localhost:8080/");
          } else {
            alert("?????? ???????????? ????????? ?????????.");
          }
        },
        error: function (request, error) {
          console.log("???????????? post error");
        },
      });
    }
  });

  document.querySelectorAll(".form__input").forEach((inputElement) => {
    inputElement.addEventListener("blur", (e) => {
      if (
        e.target.id === "signupUsername" &&
        e.target.value.length > 0 &&
        e.target.value.length < 6
      ) {
        setInputError(
          inputElement,
          "Username must be at least 6 characters in length"
        );
      }
    });

    inputElement.addEventListener("input", (e) => {
      clearInputError(inputElement);
    });
  });
});
