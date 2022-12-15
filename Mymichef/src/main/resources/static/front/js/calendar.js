let recipeData = {};
let menuData = [];

$.ajax({
  url: "/home/calendar/check",
  type: "POST",
  contentType: "json",
  data: JSON.stringify(recipeData),

  success: function (response) {
    console.log("recipe data success");
    const data = JSON.parse(response);
    console.log(data);
    for (const dataKey in data) {
      const element = data[dataKey];
      const dateData = {};

      const date = new Date(element.date);
      dateData.year = date.getFullYear();
      dateData.month = date.getMonth() + 1;
      dateData.date = date.getDate();
      dateData.food = element.recipe;
      dateData.ingredient = element.재료;
      dateData.recipe = element.조리과정;
      dateData.time = element.time;
      menuData.push(dateData);
      console.log(dateData);
    }
    console.log(menuData);

    $(document).ready(function () {
      calendarInit();
    });
  },
  error: function (error) {
    console.log("recipedata error");
    console.log(error);
    console.log(error.responseText);
  },
});

$(document).ready(function () {
  calendarInit();
});

function calendarInit() {
  // 날짜 정보
  const date = new Date(); // 현재 날짜
  const utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; // uct 표준시 도출
  const kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
  const today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)

  let thisMonth = new Date(
    today.getFullYear(),
    today.getMonth(),
    today.getDate()
  );
  // 달력에서 표기하는 날짜 객체

  let currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
  let currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
  let currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

  // kst 기준 현재시간
  // console.log(thisMonth);

  // 캘린더 렌더링
  renderCalender(thisMonth);

  function renderCalender(thisMonth) {
    let index;
    // 렌더링을 위한 데이터 정리
    currentYear = thisMonth.getFullYear();
    currentMonth = thisMonth.getMonth();
    currentDate = thisMonth.getDate();

    // 이전 달의 마지막 날 날짜와 요일 구하기
    const startDay = new Date(currentYear, currentMonth, 0);
    const prevDate = startDay.getDate();
    const prevDay = startDay.getDay();

    // 이번 달의 마지막날 날짜와 요일 구하기
    const endDay = new Date(currentYear, currentMonth + 1, 0);
    const nextDate = endDay.getDate();
    const nextDay = endDay.getDay();

    // console.log(prevDate, prevDay, nextDate, nextDay);

    // 현재 월 표기
    $(".year-month").text(currentYear + "." + (currentMonth + 1));

    // 렌더링 html 요소 생성
    let calendar = document.querySelector(".dates");
    calendar.innerHTML = "";

    // 지난달
    for (index = prevDate - prevDay + 1; index <= prevDate; index++) {
      calendar.innerHTML =
        calendar.innerHTML +
        '<div class="day prev disable">' +
        index +
        "</div>";
    }
    // 이번달
    for (index = 1; index <= nextDate; index++) {
      calendar.innerHTML =
        calendar.innerHTML + '<div class="day current">' + index + "</div>";
    }
    // 다음달
    for (index = 1; index <= (7 - nextDay === 7 ? 0 : 7 - nextDay); index++) {
      calendar.innerHTML =
        calendar.innerHTML +
        '<div class="day next disable">' +
        index +
        "</div>";
    }

    // 오늘 날짜 표기
    let todayDate;
    if (today.getMonth() === currentMonth) {
      todayDate = today.getDate();
      const currentMonthDate = document.querySelectorAll(".dates .current");
      currentMonthDate[todayDate - 1].classList.add("today");
    }
  }

  // 이전달로 이동
  $(".go-prev").on("click", function () {
    thisMonth = new Date(currentYear, currentMonth - 1, 1);
    renderCalender(thisMonth);
  });

  // 다음달로 이동
  $(".go-next").on("click", function () {
    thisMonth = new Date(currentYear, currentMonth + 1, 1);
    renderCalender(thisMonth);
  });

  // 날짜들 배열
  const days = Array.from(document.getElementsByClassName("day"));

  // 팝업 div
  const popup = document.getElementById("popup");

  // 팝업 닫기 함수
  function onPopupClose() {
    document.getElementById("popup_breakfast").innerHTML = "";
    document.getElementById("popup_lunch").innerHTML = "";
    document.getElementById("popup_dinner").innerHTML = "";
    popup.style.display = "none";
  }

  // 날짜 클릭
  function onDayClick(event) {
    console.log(currentYear, currentMonth, Number(event.target.textContent));
    console.log(
      menuData.length,
      menuData[0].year,
      menuData[0].month,
      menuData[0].date,
      menuData[0].ingredient,
      menuData[0].recipe
    );

    // 데이터베이스 날짜 비교
    for (let index = 0; index < menuData.length; index++) {
      console.log("find correct");
      const element = menuData[index];
      if (
        element.year === currentYear &&
        element.month === currentMonth + 1 &&
        element.date === Number(event.target.textContent)
      ) {
        // popup html 생성
        document.getElementById("popup_date").innerText =
          element.year + "-" + element.month + "-" + element.date;

        const food = element.food;
        let ingredient = "";
        for (const key in element.ingredient) {
          const ingredientText = `<p class="ingredientName">${key}</p>`;
          const quantity = `<p class="quantity">${element.ingredient[key]}</p>`;
          ingredient +=
            `<div class="ingredient_container">` +
            ingredientText +
            quantity +
            `</div>`;
        }

        const recipeData = Object.keys(element.recipe)
          .sort()
          .reduce((accumulator, key) => {
            accumulator[key] = element.recipe[key];

            return accumulator;
          }, {});
        let recipe = "";
        for (const key in recipeData) {
          const sequence = `<p class="sequence">${key}</p>`;
          const detail = `<p class="recipe_detail">${recipeData[key]}</p>`;
          recipe += `<div class="recipe">` + sequence + detail + `</div>`;
        }

        // 팝업 html 삽입
        const breakfastDiv = document.getElementById("popup_breakfast");
        const lunchDiv = document.getElementById("popup_lunch");
        const dinnerDiv = document.getElementById("popup_dinner");

        if (element.time === "breakfast") {
          breakfastDiv.innerHTML += food + ingredient + recipe;
        } else if (element.time === "lunch") {
          lunchDiv.innerHTML += food + ingredient + recipe;
        } else if (element.time === "dinner") {
          dinnerDiv.innerHTML += food + ingredient + recipe;
        }

        // 팝업 보이게
        popup.style.display = "block";
        document.getElementById("popupInner").scrollTo(0, 0);

        // 팝업 닫기 버튼 이벤트
        document
          .getElementById("closeBtn")
          .addEventListener("click", onPopupClose);
      }
    }
    // popup.style.display = "none";
  }

  // 각 날짜에 이벤트리스너 삽입
  days.forEach((day) => day.addEventListener("click", onDayClick));
}
(function () {
  "use strict";

  document
    .querySelector(".material-design-hamburger__icon")
    .addEventListener("click", function () {
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
