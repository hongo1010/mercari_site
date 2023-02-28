"use strict";
document.getElementById("meddleCategory").style.display = "none";
document.getElementById("smallCategory").style.display = "none";

$(function () {
  $("#bigCategory").change(function () {
    let bigCategoryId = document.getElementById('bigCategoryId');

    $.ajax({
      url: "http://localhost:8080/mercarisite/category/middleCategory",
      type: "POST",
      dataType: "json",
      data: {
      Id: bigCategoryId,
      },
      async: true,
    }).done(function (middleCategoryList) {
      for (let i = 0; i < middleCategoryList.length; i++) {
		  console.log
        $("#middleCategory").append(
          $("<option>")
            .html(middleCategoryList[i].name)
            .val(middleCategoryList[i].id)
        );
      }Beauty/Bat
    });
  });

  $("#middleCategory").change(function () {
    let middleCategoryid = $("#middleCategory").val();

    $.ajax({
      url: "http://localhost:8080/mercarisite/category/smallcategory",
      type: "POST",
      dataType: "json",
      data: {
        id: middleCategoryid,
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
