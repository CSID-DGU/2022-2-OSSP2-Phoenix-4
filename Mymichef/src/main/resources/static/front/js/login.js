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

  // // 데이터 형식
  // let data = {
  //   userId: "",
  //   email: "",
  //   password: "",
  //   name: "",
  //   phoneNumber: 0,
  //   height: 0,
  //   weight: 0,
  //   allergy: 0,
  //   gender: "",
  // };

  // 로그인 클릭 이벤트
  // loginForm.addEventListener("submit", (e) => {
  //   e.preventDefault();
  //
  //   const inputId = e.target[0].value;
  //   const inputPw = e.target[1].value;
  //
  //   if (inputId !== "" && inputPw !== "") {
  //     data.userId = inputId;
  //     data.password = inputPw;
  //
  //     $.ajax({
  //       url: "localhost8080/join",
  //       type: "POST",
  //       // dataType: "json",
  //       data: JSON.stringify(data),
  //
  //       success: function (response) {
  //         console.log("로그인 post success");
  //         console.log(response);
  //       },
  //       error: function (request, error) {
  //         console.log("로그인 post error");
  //         console.log(error);
  //         console.log(
  //           "code:" +
  //             request.status +
  //             "\n" +
  //             "message: " +
  //             request.responseText +
  //             "\n" +
  //             "error:" +
  //             error
  //         );
  //       },
  //     });
  //   }
  //
  //   setFormMessage(loginForm, "error", "Invalid username/password combination");
  // });

  // ID 찾기 이벤트 리스너
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
          console.log("아이디 찾기 통신 성공");
          console.log(response);
          const userId = JSON.parse(response);
          alert(userId.userId);
        },
        error: function (error) {
          console.log("아이디 찾기 통신 오류");
          console.log(error);
        },
      });
    }

    // setFormMessage(
    //   findIDForm,
    //   "error",
    //   "Invalid username/password combination"
    // );
  });

  // Password 찾기 이벤트 리스너
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
          console.log("비밀번호 찾기 post success");
          console.log(response);
          const userPw = JSON.parse(response);
          alert(userPw.password);
          console(data);
        },
        error: function (error) {
          console.log("비밀번호 찾기 post error");
          console.log(error);
        },
      });
    }

    // setFormMessage(
    //   findPasswordForm,
    //   "error",
    //   "Invalid username/password combination"
    // );
  });

  // 회원가입 이벤트 리스너
  createAccountForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const inputID = event.target[0].value;
    const inputEmail = event.target[1].value;
    const inputPw = event.target[2].value;
    const inputConfirmPw = event.target[3].value;

    if (inputID === "") {
      alert("아이디를 입력해주 세요.");
    } else if (inputEmail === "") {
      alert("이메일을 입력해 주세요.");
    } else if (inputPw === "" || inputConfirmPw === "") {
      alert("비밀번호를 입력해 주세요.");
    } else if (inputPw !== inputConfirmPw) {
      alert("두 비밀번호가 일치하지 않습니다.");
    } else if (
      inputID !== "" &&
      inputEmail !== "" &&
      inputPw !== "" &&
      inputPw === inputConfirmPw
    ) {
      // 데이터 생성
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
          console.log("회원가입 post success");
          console.log(response);
          console.log(data);
          if (response === "java.lang.Exception: 회원가입에 성공했습니다!") {
            alert("회원가입이 완료되었습니다.");
            window.location.replace("http://localhost:8080/");
          } else {
            alert("이미 존재하는 아이디 입니다.");
          }
        },
        error: function (request, error) {
          console.log("회원가입 post error");
          console.log(error);
          console.log(
            "code:" +
              request.status +
              "\n" +
              "message: " +
              request.responseText +
              "\n" +
              "error:" +
              error
          );
        },
      });
    }

    // setFormMessage(
    //   createAccountForm,
    //   "error",
    //   "Invalid username/password combination"
    // );
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
