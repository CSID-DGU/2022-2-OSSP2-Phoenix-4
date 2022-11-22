const userInfo = Array.from(document.getElementsByClassName("info"));
const home = document.getElementById("home");

function onHomeClick() {
  const url = "./password_check.html";
  const a = document.createElement("a");

  a.href = url;
  a.click();
}

home.addEventListener("click", onHomeClick);
