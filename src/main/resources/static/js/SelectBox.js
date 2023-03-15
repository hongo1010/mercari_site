"use strict";
//document.getElementById("meddleCategory").style.display = "none";
//document.getElementById("smallCategory").style.display = "none";

$(function () {

  $("#bigCategory").change(function () {
    let bigCategoryId = document.getElementById('bigCategoryId');
    let select_value = $('option:selected').val();

    console.log(select_value);
    $.ajax({
      url: "/mercarisite/category/middleCategory",
      type: "POST",
      dataType: "json",
      data: {
        id: select_value,
      },
      headers: {
        'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content'),
      },
      async: true,
    }).done(function (middleCategoryList) {
      console.log(middleCategoryList)
      for (let i = 0; i < middleCategoryList.length; i++) {

        $("#middleCategory").append(
          $("<option>")
            .html(middleCategoryList[i].name)
            .val(middleCategoryList[i].id)
        );
      }
      //Beauty/Bat
    });
  });

  $("#middleCategory").change(function () {
    let middleCategoryid = $("#middleCategory").val();

    $.ajax({
      url: "/mercarisite/category/smallcategory",
      type: "POST",
      dataType: "json",
      data: {
        id: middleCategoryid,
      },
      headers: {
        'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content'),
      },
      async: true,
    }).done(function (smallCategoryList) {
      for (let i = 0; i < smallCategoryList.length; i++) {
        $("#smallCategory").append(
          $("<option>")
            .html(smallCategoryList[i].name)
            .val(smallCategoryList[i].id)
        );
      }
    });
  });
});
