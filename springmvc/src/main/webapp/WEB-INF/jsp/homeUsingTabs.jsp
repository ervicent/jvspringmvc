<%@page deferredSyntaxAllowedAsLiteral="true"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link type="text/css" href="/springmvc/css/pepper-grinder/jquery-ui-1.8.16.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="/springmvc/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="/springmvc/js/jquery-ui-1.8.16.custom.min.js"></script>
		<style>
			#tabs { margin-top: 1em; }
			#tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
		</style>
		<script>
			$(function() {
					
				var tabCounter = 0;
				var $tabs = $("#tabs").tabs({
					cache: true,
					tabTemplate: "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>X</span></li>",
					load: function(event, ui) {
				        $('a', ui.panel).click(function() {
				            return false;
				        });
				        $(ui.panel).load(this.href);
				    }
				});
				
				$("#tabs span.ui-icon-close").live("click", function() {
					var index = $("li", $tabs).index($(this).parent());
					$tabs.tabs("remove", index);
				});
			
				
				// adiciona os itens do menu
				$("#itemBtn").button().click(function() {
					$tabs.tabs("add", "item", "Item", tabCounter);
					tabCounter++;
				});
				
			});	
			</script>
	</head>
	<body>
		<div id="menu">
			<div><input type="button" id="itemBtn" value="Item" /></div>
		</div>
		<div id="tabs">
			<ul></ul>
		</div>
	</body>
</html>
