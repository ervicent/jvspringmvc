<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="item_content">
	<script>
		$(function(){
			
		    $("#item_save")
		    	.click(function() {
		    		$.post("item/save", $("#itemForm").serializeArray(), function(data) {
	    			  $('#item_content').html(data);
	    			});
		    	});
		    
		    $("#item_cancel")
		    	.click(function() {
		    		$('#item_content').load("item");
		    	});
		    	
		});
	</script>
	
	<div class="ui-dialog ui-widget ui-widget-content ui-corner-all undefined ui-resizable">
	   <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
	      <span id="ui-dialog-title-dialog" class="ui-dialog-title">Item</span>
	   </div>
	   <div style="height: 200px; width: auto;" class="ui-dialog-content ui-widget-content" id="dialog">
	   
	   	
	   		<div>
				<c:forEach var="message" items="${messages}">
				${message}<br />
				</c:forEach>
				<br />
			</div>
	   
			<form id="itemForm">
				<div>
					<table>
						<tbody>
							<tr>
								<td>
									Id:
								</td>
								<td>
									<input id="id" name="id" type="text" value="${item != null ? item.id : ''}" />
								</td>
							</tr>
							<tr>
								<td>
									Descricao:
								</td>
								<td>
									<input id="description" name="description" type="text" value="${item != null ? item.description : ''}" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<button id="item_save" type="button">salvar</button>
					<button id="item_cancel" type="button">cancelar</button>
				</div>
			</form>
			
		</div>
	</div>
	
</div>