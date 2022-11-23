// 로그인 input
const id = document.getElementById("id");
const password = document.getElementById("password");

// 아이디 찾기 input
const findIdName = document.getElementById("find_id_name");
const findIdEmail = document.getElementById("find_id_email");

// 비밀번호 찾기 input
const findPwName = document.getElementById("find_pw_name");
const findPwId = document.getElementById("find_pw_id");
const findPwEmail = document.getElementById("find_pw_email");

// 회원가입 input

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

  const findPasswordForm = document.querySelector("#findPassword");
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

  const createAccountForm = document.querySelector("#createAccount");
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

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();

    // Perform your AJAX/Fetch login

    setFormMessage(loginForm, "error", "Invalid username/password combination");
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

// 회원가입 시 json 형식 데이터 생성
function joinMembership() {
  const userList = [];
  const userData = {};

  userData.name = document.getElementById("signupUsername").value;
  userData.email = document.getElementById("signupEmail").value;
  userData.password = document.getElementById("signupPassword").value;
  userData.confirmPassword = document.getElementById(
    "signupConfirmPassword"
  ).value;
  if (userData.name !== "") {
    if (userData.email !== "") {
      if (userData.password !== "") {
        userList.push(userData);
        console.log("userdata 생성 완료");
      } else {
        console.log("password error");
      }
    } else {
      console.log();
    }
  }

  console.log(userList);
}

function onSubmitClick() {
  const userID = document.getElementById("signupUsername");
  const submitEmail = document.getElementById("signupEmail");
  const submitPassword = document.getElementById("signupPassword");
  const submitConfirmPassword = document.getElementById(
    "signupConfirmPassword"
  );

  console.log(userID.value, userPassword.value);
}

const submitBtn = document.getElementById("submitBtn");
submitBtn.addEventListener("click", joinMembership);
