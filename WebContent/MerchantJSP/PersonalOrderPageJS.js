/**
 * 全局变量
 */
var currentPage;
var totalPage;
var pageGap;
var searchText;
var isSearch;
var isSort;
/**
 * 设置当前页面
 * @param num
 * @returns void
 */
function setCurrentPage(num) {
	this.currentPage = num;
}
/**
 * 设置页面总数
 * @param num
 * @returns void
 */
function setTotalPage(num) {
	this.totalPage = num;
}
/**
 * 设置分页列表要展示的数量
 * @param num
 * @returns void
 */
function setPageGap(num) {
	this.pageGap = num;
}

function setIsSearch(bool) {
	this.isSearch = bool;
}

function setIsSort(bool) {
	this.isSort = bool;
}
/**
 * 初始化页面
 * @returns void
 */
$(document).ready(
	$(document).on("click", ".pageButton", function () {
		getlist($(this).val());
	}),
	$(document).on("click", "#searchButton", function search() {
		if ($("[type=search]").val() != "") {
			setIsSearch(true);
			searchText = $("[type=search]").val();
			getlist(1);
			$("[type=search]").val("");
		} else {
			initPage();
		}
	}),
	$(document).on("click", ".nextPage", function nextPage() {
		currentPage++;
		if (currentPage <= totalPage) {
			getlist(currentPage);
		} else {
			setCurrentPage(totalPage);
		}
		hideButton(currentPage);
	}),
	$(document).on("click", ".prevPage", function prevPage() {
		currentPage--;
		if (currentPage >= 1) {
			getlist(currentPage);
		} else {
			setCurrentPage(1);
		}
		hideButton(currentPage);
	}),
	// $(document).on("click", "", function sort() {
	// 	setIsSort(true);
	// 	if (isSearch == true) {

	// 	} else {
	// 		getlist(currentPage);
	// 	}

	// }),
	initPage()
);
/**
 * 初始化页面
 * @returns
 */
function initPage() {
	$.getJSON("../PersonalServlet?action=getPageModel",
		function (data, textStatus, jqXHR) {
			setCurrentPage(data.currentPageNum);
			setTotalPage(data.totalPageNum);
			setPageGap(data.pageGap);
			getlist(currentPage);
		}
	);
	setIsSearch(false);
	setIsSort(false);
	this.searchText = "";
}

/**
 * 设置当前页数据
 * @returns
 */
function getlist(num) {
	var errorMessage = "糟糕，服务器遇到了错误请重新加载。";
	if (isSearch != true) {
		if (isSort != true) {
			var url = "../PersonalServlet?action=searchByPage&currentPage=" + num;
			setDishList(url, errorMessage, errorMessage);
		} else {
			var url = "../PersonalServlet?action=searchByPageAndSort&currentPage=" + num;
			setDishList(url, errorMessage, errorMessage);
		}
	} else {
		if (isSort == true) {
			var url = "../PersonalServlet?action=searchAndSort&searchText=" + searchText;
			setDishList(url, errorMessage, errorMessage);
		} else {
			setCurrentPage(1);
			setTotalPage(1);
			var url = "../PersonalServlet?action=search&searchText=" + searchText;
			setDishList(url, errorMessage, errorMessage);
		}
	}
	//hideButton(num);
}
/**
 * 获得需要添加的html代码
 * @param item
 * @returns string
 */
function getListText(item) {
	var href="../PersonalServlet?action=statement&ordernumber=";
	var style="text-align:center";
	var text = "<tr style="+style+">" +
		"<td scope='row'><a href="+href + item.ORDERNO + ">"+item.ORDERNO +"</a></td>" +
		"<td>" + item.USERNO + "</td>" +
		"<td>" + item.USERNAME + "</td>" +
		"<td>" + item.PRICE + "</td>" +
		"<td>" + item.TIME + "</td>" +
		"<td>" + item.ORDERSTATE +"</td>" +				
		"<td>" + item.COMMENTSTATE +"</td>" +		
		"</tr>";
	return text;
}
/**
 * 通过url设置dishlist的html代码
 * @param url
 * @returns null
 */
function setDishList(url, errorMessage, notFindMessage) {
	var listText = "";
	$.ajax({
		type: "post",
		url: url,
		datatype: "json",
		success: function (data) {
			data = $.parseJSON(data);
			if (data != null) {
				$.each(data, function (index, item) {
					listText += getListText(item);
				});
				$("#dishlist").html(listText);
			} else {
				$("#dishlist").html(notFindMessage);
			}
		},
		error: function () {
			$("#dishlist").html(errorMessage);
		},
		beforeSend: function () {
			$("#dishlist").html("loading");
		}
	});
}
/**
 * 根据用户的操作隐藏按钮和省略号
 * @param currentIndex
 * @returns void
 */
//function hideButton(currentIndex) {
//	//设置按钮隐藏
//	var buttons = document.getElementsByClassName("pageButton");
//	$.each(buttons, function () {
//		if (Math.abs(parseInt($(this).val()) - parseInt(currentIndex)) <= parseInt(pageGap)) {
//			$(this).show();
//		} else {
//			$(this).hide();
//		}
//	});
//	//重新设置首页和尾页
//	$(".pageButton[value='1']").show();
//	$(".pageButton[value=" + totalPage + "]").show();
//	//设置省略号
//	if (parseInt(currentIndex) - parseInt(pageGap) - 1 > 1) {
//		$(".xx span:first").show();
//	} else {
//		$(".xx span:first").hide();
//	}
//	if ((parseInt(currentIndex) + parseInt(pageGap) + 1 < totalPage) && totalPage > pageGap + 2) {
//		$(".xx span:last").show();
//	} else {
//		$(".xx span:last").hide();
//	}
//	//设置上一页下一页
//	if (currentIndex == 1) {
//		$(".prevPage").hide();
//	} else {
//		$(".prevPage").show();
//	}
//	if (currentIndex == totalPage) {
//		$(".nextPage").hide();
//	} else {
//		$(".nextPage").show();
//	}
//}