"use strict";
$(function () {
  $("#bigCategory").change(function () {
    let id = $("#bigCategory").val();
    $("#middleCategory > option").remove();

    $.ajax({
      url: "http://localhost:8080/category/middleCategory",
      type: "POST",
      dataType: "json",
      data: {
        id: id,
      },
      async: true,
    }).done(function (middleCategoryList) {
      for (let i = 0; i < middleCategoryList.length; i++) {
        $("#middleCategory").append(
          $("<option>")
            .html(middleCategoryList[i].name)
            .val(middleCategoryList[i].id)
        );
      }
    });
  });

  $("#middleCategory").change(function () {
    let id = $("#middleCategory").val();
    $("#smallCategory > option").remove();

    $.ajax({
      url: "http://localhost:8080/category/smallcategory",
      type: "POST",
      dataType: "json",
      data: {
        id: id,
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
