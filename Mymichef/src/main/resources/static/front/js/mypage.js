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
const infoNowPW   = document.getElementById("info_now_p/w")
const infoNewPW   = document.getElementById("info_new_p/w")
const infoAgnPW   = document.getElementById("info_again_p/w")
const infoEmail   = document.getElementById("info_email")
const infoGender  = document.getElementById("info_gender")
const infoHeight  = document.getElementById("info_gender")
const infoWeight  = document.getElementById("info_weight")
const infoAllergy = document.getElementById("info_allergy")
const infoVegan   = document.getElementById("info_vegan")
const infoCalorie = document.getElementById("info_calorie")

// 사용자 input
const getName    = document.getElementById("input_name")
const getNowPW   = document.getElementById("input_now_p/w")
const getNewPW   = document.getElementById("input_new_p/w")
const getAgnPW   = document.getElementById("input_again_p/w")
const getGender  = document.getElementsByName("gender")
const getHeight  = document.getElementById("input_height")
const getWeight  = document.getElementById("input_weight")
const getAllergy = document.getElementById("search_allergy")
const getVegan   = document.getElementsByName("vegan_radio")
const getCalorie = document.getElementById("input_calorie")

// 사용자 db(연동 요청)
let dbName      = null
let dbPW        = null      //손봐주세용...............
let dbEmail     = null
let dbGender    = null
let dbHeight    = null
let dbWeight    = null
let dbAllergy   = null
let dbVegan     = null
let dbCalorie   = null

const editBtn   = document.getElementById("edit_btn")
const CheckBtn  = document.getElementById("check_btn")

let edit = false
let check = false

editBtn.innerText = "수정"
CheckBtn.innerText= "확인"

// input value 가져오기
function getValue() {
    name    = getName.value
    //여기도 pw!!!!!!!!!!!!!!!!
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
}

// db 업데이트
function dbUpdate() {

}

// 마이페이지 정보 업데이트(수정예정)
function infoUpdate() {
    infoName.innerText    = dbName
    //비밀번호 db관련 수정 필요해요!!!!!!!!!!!!!!!!!!!!
    infoEmail.innerText   = dbEmail
    infoGender.innerText  = dbGender
    infoHeight.innerText  = dbHeight
    infoWeight.innerText  = dbWeight
    infoAllergy.innerText = dbAllergy
    infoVegan.innerText   = dbVegan
    infoCalorie.innerText = dbCalorie
}

// 수정 or 확인 버튼 클릭 이벤트
function onEditClick() {
    if (edit) {
        edit = false
        editBtn.innerText = "수정"
    } else {
        getValue()
        dbUpdate()
        infoUpdate()

        edit = true
        editBtn.innerText = "확인"
    }
}

//여기좀 손봐주세요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
function onCheckClick() {
    if (check) {
        check = false
        CheckBtn.innerText = "확인"
    } else {
        getValue()
        dbUpdate()
        infoUpdate()

        check = true
        CheckBtn.innerText = "일치"
    }
}

infoUpdate()

editBtn.addEventListener("click", onEditClick)
CheckBtn.addEventListener("click", onCheckClick)