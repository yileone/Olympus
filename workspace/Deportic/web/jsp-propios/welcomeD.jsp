<%
String app = request.getParameter("application");
%>
<div class="social">
		<ul>
		
			<li><a href="#" target="_blank" class="icon-youtube"></a></li>
			<li><a href="#" target="_blank" class="icon-mail"></a></li>
			<li><a href="#" target="_blank" class="icon-googleplus"></a></li>

		</ul>
</div>
<div id="wrapper">
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="#">Home</a></li>
			<li><a href="#">Funcionalidad 1</a></li>
			<li><a href="#">Funcionalidad 2</a></li>
			<li><a href="#">Funcionalidad 3</a></li>
			<li><a href="#">Funcionalidad 4</a></li>
			<li><a href="#">Funcionalidad ...</a></li>
			<li><a href="#">Funcionalidad N</a></li>
		</ul>
	</div>
	<!-- end #menu 
	<div id="header">
		<div id="logo">
			<h1><a href="#">  BIENVENIDOS A DEPORTIC  </a></h1>			
		</div>
	</div>
	<!-- end #header -->
	<div class="scrollWrapper" onMouseover="scrollspeed=0" onMouseout="scrollspeed=current">
    <div class="scrollTitle">Últimas Noticias</div>
    <div id="scroll" >
        <div class="title">Primera Noticia</div>
        <div class="content">Contenido de ejemplo para el scroll de noticias personalizable. En el contenido puedes añadir cualquier codigo HTML, incluidos enlaces <a href="http://www.lawebdelprogramador.com" target="_top">La Web del programador</a>
        </div>
        <div class="title">Segunda Noticia</div>
        <div class="content">Contenido de ejemplo para el scroll de noticias personalizable. En el contenido puedes añadir cualquier codigo HTML, incluidos enlaces <a href="http://www.lawebdelprogramador.com" target="_top">La Web del programador</a>
        </div>
        <div class="title">Tercera Noticia</div>
        <div class="content">Contenido de ejemplo para el scroll de noticias personalizable. En el contenido puedes añadir cualquier codigo HTML, incluidos enlaces <a href="http://www.lawebdelprogramador.com" target="_top">La Web del programador</a>
        </div>
       
    </div>
    
</div>	
 <div class="scrollTitle"  width:"80%" height:"100%"></div>
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
		<div id="content">
			<div class="post">
				<h2 class="title"><a href="#">ACA VA EL TABLERO</a></h2>
				<div style="clear: both;">&nbsp;</div>
			</div>
		<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end #content -->
		<div id="sidebar" >
			<ul>
				<li>
					<jsp:include page='<%="../xava/module.jsp?application=" + app + "&module=SignIn"%>'/>
					<div style="clear: both;">&nbsp;</div>
				</li>
			</ul>
</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>

	<!-- end #page -->
</div>
	<!--<div id="footer">
		<p><a href="#">***********************</a>.</p>
	</div>
	 end #footer -->
