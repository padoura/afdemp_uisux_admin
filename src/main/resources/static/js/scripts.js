/**
 * 
 */

$(document).ready(function() {
	
	
	$('.delete-product').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this ? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	
	$('.toogle-product').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'toggleProductActive';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to change the status to opposite?.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	


	$('#deleteSel').click(function() {
		var idList= $('.checkboxproduct');
		var productIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				productIdList.push(idList[i]['id'])
			}
		}
		
		console.log(productIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected products? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(productIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});



	$('#disSel').click(function() {
		var idList= $('.checkboxproduct');
		var productIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				productIdList.push(idList[i]['id'])
			}
		}
		
		console.log(productIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'deactivateList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to change the status of these profucts",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(productIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});




	$('#shareSel').click(function() {
		var idList= $('.checkboxproduct');
		var transactionIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				transactionIdList.push(idList[i]['id'])
			}
		}
		
		console.log(transactionIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'sendEarnings';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to share this earnings?",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(transactionIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});

	
	
	
	
	

	$('#enSel').click(function() {
		var idList= $('.checkboxproduct');
		var productIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				productIdList.push(idList[i]['id'])
			}
		}
		
		console.log(productIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'activateList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to change the status of these profucts",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(productIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});


	
	
	
	
	
	
	
	
	
	$("#selectAllProducts").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxproduct").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxProduct").prop("checked",false);
		}
	})
	

});