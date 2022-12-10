let data1 = [];
let data2 = [];

 $.ajax({
    url: "/recommend/default",
    type: "POST",
    contentType: "json",
    data: JSON.stringify(data1),

    success: function (response) {
      console.log("recommend success");
      console.log(response);
      const userData = JSON.parse(response);
      console.log("RECIPE_NM_KO: " + userData.RECIPE_NM_KO);
    },
    error: function (error) {
      console.log("recommend error");
      console.log(error);
      console.log(error.responseText);
    },
 });

setTimeout(function() {

 $.ajax({
     url: "/recommend/save",
     type: "POST",
     contentType: "application/json",
     data: JSON.stringify(data2),

     success: function (response) {
       console.log("recommendInfo save success");
       console.log(response);

     },
     error: function (error) {
       console.log("recommendInfo save error");
       console.log(error);
       console.log(error.responseText);
     },
  })
    console.log('Works!');
  }, 3000);

  $.ajax({
      url: "/recommend/nation",
      type: "POST",
      contentType: "json",
      data: JSON.stringify(data1),

      success: function (response) {
        console.log("recommend(nation) success");
        console.log(response);
      },
      error: function (error) {
        console.log("recommend(nation) error");
        console.log(error);
        console.log(error.responseText);
      },
   });



(function() {

  'use strict';

  document.querySelector('.material-design-hamburger__icon').addEventListener(
    'click',
    function() {
      var child;

      document.body.classList.toggle('background--blur');
      this.parentNode.nextElementSibling.classList.toggle('menu--on');

      child = this.childNodes[1].classList;

      if (child.contains('material-design-hamburger__icon--to-arrow')) {
        child.remove('material-design-hamburger__icon--to-arrow');
        child.add('material-design-hamburger__icon--from-arrow');
      } else {
        child.remove('material-design-hamburger__icon--from-arrow');
        child.add('material-design-hamburger__icon--to-arrow');
      }

    });

})();

$(input[type="submit"]).on("click", function(e){
  e.preventDefault();
});