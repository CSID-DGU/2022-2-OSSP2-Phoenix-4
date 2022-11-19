const name   = document.getElementsByName("id")
const man    = document.getElementById("man")
const woman  = document.getElementById("woman")
const id     = document.getElementsByName("id")
const pw     = document.getElementsByName("pw")
const height = document.getElementsByName("height")
const weight = document.getElementsByName("weight")
const phone  = document.getElementsByName("phone")
const subBtn = document.getElementById("submit")

const gender = null

function onGenderClick(event) {
    console.log(event.target)
}

function onSubmitClick() {
    if (name == "") {
        name.style.placeholder = 'color = red; ID를 입력해 주세요;'
    }
}


man.addEventListener("click", onGenderClick)
woman.addEventListener("click", onGenderClick)
subBtn.addEventListener("click", onSubmitClick)
