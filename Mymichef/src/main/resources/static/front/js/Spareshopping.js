// 서버 음식 api accept
let ingredientList = {};

$.ajax({
    url: "/food/list",
    type: "POST",
    contentType: "json",
    data: JSON.stringify(ingredientList),

    success: function (response) {
        console.log("userInfo success");
        console.log(response);
        const foodsUnit = JSON.parse(response);

        console.log(foodsUnit);
        let ingredientsData = [];

        for (let key in foodsUnit) {
            const ingredient = {};

            ingredient.name = key;
            ingredient.unit = foodsUnit[key];
            ingredientsData.push(ingredient);
        }

        // 오름차순 정렬
        ingredientsData = ingredientsData.sort((a, b) => {
            if (a.name > b.name) return 1;
            if (a.name < b.name) return -1;
        });

        const ingredientsResult = document.getElementById("ingredientsResult");
        for (let i = 0; i < ingredientsData.length; i++) {
            const ingredientName = ingredientsData[i].name;
            const ingredientImg = "../jpg/logo!.png";

            const divName = `<div class="ingredientName" id=${
                ingredientName + "name"
            }>${ingredientName}</div>`;
            const divImg = `<img class="ingredientImg" id=${
                ingredientName + "img"
            } src=${ingredientImg} alt=${ingredientName}>`;

            ingredientsResult.innerHTML =
                ingredientsResult.innerHTML +
                `<div class="ingredient" id=${ingredientName}>` +
                divImg +
                divName +
                `</div>`;
        }
        // 재료 검색
        function onSearch(event) {
            const search = event.target.value.toLowerCase();
            const items = document.getElementsByClassName("ingredient");

            for (let i = 0; i < items.length; i++) {
                const item = items[i].getElementsByClassName("ingredientName");
                if (item[0].innerHTML.toLowerCase().indexOf(search) !== -1) {
                    items[i].style.display = "inline-block";
                } else {
                    items[i].style.display = "none";
                }
            }
        }
        const ingredientSearch = document.getElementById("ingredientSearch");
        ingredientSearch.addEventListener("keyup", onSearch);

        const infoForm = document.getElementById("ingredientInfoForm");
        const infoName = document.getElementById("infoName");
        const items = Array.from(document.getElementsByClassName("ingredient"));

        // 재로 클릭 이벤트
        function onResultClick(event) {
            const id = event.target.id;
            let clickItem;
            let itemImg;
            let unit;

            for (let i = 0; i < ingredientsData.length; i++) {
                const element = ingredientsData[i].name;
                if (
                    id === element ||
                    id === element + "name" ||
                    id === element + "img"
                ) {
                    clickItem = element;
                    unit = foodsUnit[element];
                    itemImg = `<img class="infoImg" src=${clickItem} alt=${clickItem}>`;
                    infoForm.style.display = "flex";
                }
            }
            infoName.innerText = clickItem;
            $("label[for='quantity']").text(unit);
            // infoDiv.innerHTML += itemImg;
        }
        items.forEach((item) => item.addEventListener("click", onResultClick));

        // 재료 저장 이벤트
        function onSaveClick(event) {
            event.preventDefault();
            console.log("click save");
            console.log($(`label[for="quantity"]`).text());
            const name = document.getElementById("infoName").innerText;
            const mount = document.getElementById("quantity").value;
            const ingredunit = $(`label[for="quantity"]`).text();
            console.log(name, mount);

            if (mount !== "" && mount > 0) {
                const saveData = {
                    ingred: name,
                    amount: mount,
                    unit: ingredunit,
                };
                console.log(saveData);

                $.ajax({
                    url: "/home/shopping/input",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(saveData),

                    success: function (response) {
                        console.log("ingredients api post success");
                        console.log(response);
                        if (response === "장바구니 등록 성공(server)") {
                            alert("장바구니 등록 성공!");
                            window.location.replace("http://localhost:8080/home/shopping");
                        } // event.unbind();
                    },
                    error: function (error) {
                        console.log("ingredients api post error");
                        console.log(error);
                    },
                });
            } else {
                document.getElementById("error_msg").innerText =
                    "수량을 정확히 입력해 주세요";
            }
        }
        document.getElementById("infoBtn").addEventListener("click", onSaveClick);
    },

    error: function (error) {
        console.log("userInfo error");
        console.log(error);
        console.log(error.responseText);
    },
});

let ingredient = [];
$.ajax({
    url: "/home/shopping/check",
    type: "POST",
    contentType: "json",
    data: JSON.stringify(ingredient),

    success: function (response) {
        console.log("userInfo success");
        const data = JSON.parse(response);
        console.log(data);

        const listDoc = document.getElementById("food_list");

        // 재료 추가 이벤트
        function onDeleteClick(event) {
            console.log(event);
            const postData = {};
            const name = event.target.id;
            postData.ingred = name;

            console.log("delete");
            console.log(postData);
            $.ajax({
                url: "/home/shopping/food",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(postData),

                success: function (response) {
                    console.log("add success");
                    const data = response;
                    console.log(data);
                    if (response === "추가완료") {
                        alert("재료 추가 완료");
                        window.location.replace("http://localhost:8080/home/shopping");
                    }
                },
                error: function (error) {
                    console.log("delete error");
                    console.log(error);
                    console.log(error.responseText);
                },
            });
        }

        // 내 장바구니 리스트에 DB 추가
        for (const dataKey in data) {
            const foodName = `<p class="user_food_name">${dataKey}</p>`;
            const foodAmount = `<p class="user_food_amount">${data[dataKey]}</p>`;
            const deleteBtn = `<button class="food_delete" id=${dataKey}>식재료에 추가</button>`;
            const foodDiv =
                `<div class="user_food">` +
                foodName +
                foodAmount +
                deleteBtn +
                `</div>`;
            listDoc.innerHTML += foodDiv;
        }

        // button 삭제 이벤트 추가
        const deleteBtn = document.querySelectorAll(".food_delete");
        deleteBtn.forEach((btn) => {
            btn.addEventListener("click", onDeleteClick);
        });
    },
    error: function (error) {
        console.log("userInfo error");
        console.log(error);
        console.log(error.responseText);
    },
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
