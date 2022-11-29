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

  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();

    // 데이터 담을 리스트
    const data = {};

    // // 데이터 형식
    // data = {
    //   user_id: "",
    //   email: "",
    //   password: "",
    //   name: "",
    //   phoneNumber: 0,
    //   height: 0,
    //   weight: 0,
    //   allergy: 0,
    //   gender: "",
    // };

    // 로그인 폼
    if (e.target.id === "login") {
      const inputId = e.target[0].value;
      const inputPw = e.target[1].value;

      if (inputId !== "" && inputPw !== "") {
        data.user_id = inputId;
        data.password = inputPw;

        $.ajax({
          url: "localhost8080/join",
          type: "POST",
          dataType: "json",
          data: data,

          success: function (response) {
            console.log("로그인 post success");
            console.log(response);
          },
          error: function (error) {
            console.log("로그인 post error");
            console.log(error);
          },
        });
      }
    }

    // 아이디 찾기 폼
    if (e.target.id === "findID") {
      const inputName = e.target[0].value;
      const inputEmail = e.target[1].value;

      if (inputName !== "" && inputEmail !== "") {
        data.findIDName = inputName;
        data.findIDEmail = inputEmail;

        $.ajax({
          url: "localhost8080/join",
          type: "POST",
          dataType: "json",
          data: data,

          success: function (response) {
            console.log("아이디 찾기 get success");
            console.log(response);
          },
          error: function (error) {
            console.log("아이디 찾기 get error");
            console.log(error);
          },
        });
      }
    }
    // 비밀번호 찾기 폼
    if (e.target.id === "findPassword") {
      const inputName = e.target[0].value;
      const inputID = e.target[1].value;
      const inputEmail = e.target[2].value;
      if (inputName !== "" && inputID !== "" && inputEmail !== "") {
        data.findPasswordName = inputName;
        data.findPasswordID = inputID;
        data.findPasswordEmail = inputEmail;

        $.ajax({
          url: "localhost8080/join",
          type: "POST",
          dataType: "json",
          data: data,

          success: function (response) {
            console.log("비밀번호 찾기 post success");
            console.log(response);
          },
          error: function (error) {
            console.log("비밀번호 찾기 post error");
            console.log(error);
          },
        });
      }
    }

    // 회원가입 폼
    if (e.target.id === "createAccount") {
      const inputID = e.target[0].value;
      const inputEmail = e.target[1].value;
      const inputPw = e.target[2].value;
      const inputConfirmPw = e.target[3].value;

      if (
        inputID !== "" &&
        inputEmail !== "" &&
        inputPw !== "" &&
        inputPw === inputConfirmPw
      ) {
        // 데이터 생성
        data.user_id = inputID;
        data.email = inputEmail;
        data.password = inputPw;

        $.ajax({
          type: "POST",
          url: "localhost8080/join",
          data: data,
          dataType: "json",
          success: function (response) {
            console.log("회원가입 post success");
            console.log(response);
            console.log(data);
          },
          error: function (error) {
            console.log("회원가입 post error");
            console.log(error);
          },
        });
      }
    }

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
