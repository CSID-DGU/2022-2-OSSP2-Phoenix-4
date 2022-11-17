// db 연동
const name      = null
const email     = null
const gender    = null
const height    = null
const weight    = null
const allergy   = null
const vegan     = null
const calorie   = null

const infoName  = document.getElementById("info_name")
const infoEmail = document.getElementById("info_email")
const infoGender= document.getElementById("info_gender")
const infoHeight= document.getElementById("info_gender")
const infoWei   = document.getElementById("info_weight")
const infoAl    =document.getElementById("info_allergy")
const infoVegan = document.getElementById("info_vegan")
const infoCal   = document.getElementById("info_calorie")

const getName   = document.getElementById("input_name")
const getHeight = document.getElementById("input_height")
const getWeight = document.getElementById("input_weight")
const getAllergy= document.getElementById("search_allergy")
const editBtn   = document.getElementById("edit_btn")


let edit = false



editBtn.innerText = "수정"





function onEditClick() {
    if (edit) {
        edit = false
        editBtn.innerText = "수정"
    } else {
        edit = true
        editBtn.innerText = "확인"
    }
}


editBtn.addEventListener("click", onEditClick)