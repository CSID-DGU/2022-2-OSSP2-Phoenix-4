const logout = document.querySelector("#logout");
const popup = document.querySelector("#popup");
const close = document.querySelector("#close");
const logout_y = document.querySelector("#logout_y");
const logout_n = document.querySelector("#logout_n");

logout.addEventListener("click", () => {
  popup.className = "enter";
});

close.addEventListener("click", () => {
  popup.className = "exit";
});
logout_y.addEventListener("click", () => {
  popup.className = "exit";
});
logout_n.addEventListener("click", () => {
  popup.className = "exit";
});
