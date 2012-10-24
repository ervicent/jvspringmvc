<%@page deferredSyntaxAllowedAsLiteral="true"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link type="text/css" href="/springmvc/css/pepper-grinder/jquery-ui-1.8.16.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="/springmvc/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="/springmvc/js/jquery-ui-1.8.16.custom.min.js"></script>
		<script>
			$(function() {
					
				// add the itens to content
				$("#HomeBtn").button().click(function() {
					$("#content").load("home/welcome");
				});
				
				// add the itens to content
				$("#itemBtn").button().click(function() {
					$("#content").load("item");
				});
				
				
				// init
				$("#content").load("home/welcome");
				
			});	
			</script>
	</head>
	<body>
		<div id="menu" class="ui-widget">
			<div>
				<input type="button" id="HomeBtn" value="Home" />
				<input type="button" id="itemBtn" value="Item" />
			</div>
		</div>
		<div id="content" style="margin: 10px;">
		</div>
	</body>
</html>
