let name      = null
let email     = null
let gender    = null
let height    = null
let weight    = null
let allergy   = null
let vegan     = null
let calorie   = null

// 사용자 info
const infoName    = document.getElementById("info_name")
const infoEmail   = document.getElementById("info_email")
const infoGender  = document.getElementById("info_gender")
const infoHeight  = document.getElementById("info_gender")
const infoWeight  = document.getElementById("info_weight")
const infoAllergy = document.getElementById("info_allergy")
const infoVegan   = document.getElementById("info_vegan")
const infoCalorie = document.getElementById("info_calorie")

// 사용자 input
const getName    = document.getElementById("input_name")
const getGender  = document.getElementsByName("gender")
const getHeight  = document.getElementById("input_height")
const getWeight  = document.getElementById("input_weight")
const getAllergy = document.getElementById("search_allergy")
const getVegan   = document.getElementsByName("vegan_radio")
const getCalorie = document.getElementById("input_calorie")

// 사용자 db(연동 요청)
let dbName      = null
let dbEmail     = null
let dbGender    = null
let dbHeight    = null
let dbWeight    = null
let dbAllergy   = null
let dbVegan     = null
let dbCalorie   = null

const editBtn   = document.getElementById("edit_btn")

let edit = false

editBtn.innerText = "수정"

// 마이페이지 정보 업데이트(수정예정)
function infoUpdate() {
    infoName.innerText    = dbName
    infoEmail.innerText   = dbEmail
    infoGender.innerText  = dbGender
    infoHeight.innerText  = dbHeight
    infoWeight.innerText  = dbWeight
    infoAllergy.innerText = dbAllergy
    infoVegan.innerText   = dbVegan
    infoCalorie.innerText = dbCalorie
}

// db 업데이트
function dbUpdate() {

}

// 수정 or 확인 버튼 클릭 이벤트
function onEditClick() {
    if (edit) {
        edit = false
        editBtn.innerText = "수정"
    } else {
        // 입력값 가져오기
        name    = getName.value
        height  = getHeight.value
        weight  = getWeight.value
        allergy = getAllergy.value
        calorie = getCalorie.value
        getGender.forEach((node) => {
            if (node.checked) {
                gender = node.value
            }
        })
        getVegan.forEach((node) => {
            if (node.checked) {
                vegan = node.value
            }
        })

        // db 업데이트
        dbUpdate()

        // info 업데이트
        infoUpdate()

        edit = true
        editBtn.innerText = "확인"
    }
}

infoUpdate()

editBtn.addEventListener("click", onEditClick)