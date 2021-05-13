/**
 * 
 */

$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	// If valid------------------------
	$("#formItem").submit();
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#email").val($(this).closest("tr").find('td:eq(0)').text());
	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#des").val($(this).closest("tr").find('td:eq(4)').text());
});
// CLIENT-MODEL================================================================
function validateItemForm()
{
	// CODE
	if ($("#email").val().trim() == "")
	{
		return "Insert Item Code.";
	}
	// NAME
	if ($("#name").val().trim() == "")
	{
		return "Insert Item Name.";
	}
	
	if ($("#address").val().trim() == "")
	{
		return "Insert Item Name.";
	}

	// PRICE-------------------------------
	if ($("#phone").val().trim() == "")
	{
		return "Insert Item Price.";
	}
		// is numerical value
	var num = $("#phone").val().trim();
	if (!$.isNumeric(num))
	{
		return "Insert a numerical value for Item Price.";
	}
	// convert to decimal price
	$("#phone").val(parseInteger(num).toFixed(10));
	
	// DESCRIPTION------------------------
	if ($("#des").val().trim() == "")
	{
		return "Insert Item Description.";
	}
	return true;
}
