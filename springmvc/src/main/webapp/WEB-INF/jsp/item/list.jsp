<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="item_content">
	<script type="text/javascript">
		$(function(){
		    
		    $("#item_add")
		    	.click(function() {
		    		$('#item_content').load("item/add");
		    	});
		    	
		});
	
		function loadAction(action) {
			$('#item_content').load(action);
		}
	
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
	      
	      	<div>
				<a id="item_add" href="#">Novo</a>
			</div>
			<div>
				<table>
					<thead>
						<tr>
							<td>
								ID
							</td>
							<td>
								Descricao
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${items}">
						<tr>
							<td>
								${item.id}
							</td>
							<td>
								${item.description}
							</td>
							<td>
								<a id="item_edit" href="javascript:loadAction('item/edit/${item.id}');">#editar</a>
							</td>
							<td>
								<a id="item_remove" href="javascript:loadAction('item/remove/${item.id}');">#excluir</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	      
	      
	   </div>
	</div>


	
</div>