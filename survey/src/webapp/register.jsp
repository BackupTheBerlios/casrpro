<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>

<html>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="files/styles.css" type=text/css rel=stylesheet>
<LINK href="files/styles_menu.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="files/menu.js" type=text/javascript> </SCRIPT>
<script language="JavaScript1.1" type="text/javascript" src="files/simplejoin.js"></script>


<title>Panel de Opinion
</title>

 
</head><body>
<center>

<TABLE WIDTH=85% CELLPADDING=0 BORDER=0>
  <tr>
	<TD><img src="Images/logo.jpg"></TD>
  </TR>
</TABLE>

<TABLE cellSpacing=0 cellPadding=2 width="85%" border=0>
  <TBODY>
  <TR>
    <TD class=b><SPAN class=bF>    
	Complete estos datos para registrarse y empezar a recibir encuestas sobre varios temas. 
      	Por participar en las encuestas nosotros lo recompensaremos.
	Solamente usted decidira que hacer con lo que obtenga.</SPAN></TD></TR>
  <TR><TD class=b>&nbsp;</TD></TR>
  <TR>
    <TD class=b><SPAN class=bF>
	<FONT color=red>La informacion que usted provea aqui y en cualquier encuesta
	nunca sera entregada a nadie en forma individual o identificable de alguna manera.
	<B>Lea la politica de privacidad para conocer mas detalles al respecto.</B>
	</FONT></SPAN> </TD></TR>
  <TR><TD class=b>&nbsp;</TD></TR>
  <TR>
    <TD class=b>
	Para participar solo debe ser mayor de 18 años y vivir en Argentina.</TD></TR>
    </TD>
  </TR>
	<TD class=b>&nbsp;</TD></TR>
  <TR>
	<TD class=b><SPAN class=bF>Registrese y obtenga su cuenta 
			gratis de PaneldeOpinion.com.
			Por favor contacte a ayuda@paneldeopinion.com 
			si tiene alguna dificultad para registrarse.</SPAN></TD></TR>  
  <TR>
	<TD class=b>&nbsp;</TD></TR>
  </TBODY>
</TABLE>
    
 

<!-- tab name -->
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="85%" border=0>
<TR>
	<TD vAlign=top class=b width=12 height=18>
	<IMG height=18 src="files/t_l_on.gif" width=12></TD>
 	<TD vAlign=top class=t_nolinkon align=middle 
	background=files/t_b_on.gif height=18>Registración</TD>
	<TD><IMG height=18 src="files/t_r_onB.gif" width=20></TD>
	<TD class=b_s width="100%" background=files/t_bg.gif>
	<IMG height=1 src="files/1x1.gif" width=1></TD>

</TR>
</TABLE>
 
<TABLE cellSpacing=0 cellPadding=0  width="85%" border=0>
	<TBODY><TR>
		<TD class=t_head height=5><IMG height=1 
		src="files/1x1.gif" 
		width=1></TD>
	</TR></TBODY>
</TABLE>



<!-- end tab name -->

   
    
  <TABLE cellSpacing=0 cellPadding=0 width="85%" border=0>
       <TBODY>
          <TR>
             <TD class=t_body>
    
    <!---------------------------Joinup Form---------------------->

<html:form action="/registerAction">

<TABLE cellSpacing=0 cellPadding=0 width="85%"  border=0>
  <TBODY>
 
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 bordercolor="##AA4f6a">

<!-- section separator -->
 <TR>
	<TD class=t_div align=middle height=20 width=25>
	<IMG height=5 src="files/F_bul.gif" width=3></TD>
	<TD class=t_div><SPAN class=bb>Email Address</SPAN></TD>
	<TD class=t_div>&nbsp;</TD>
 </TR>
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
<!-- end section separator -->

  
<TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Direccion de E-mail:</TD>
<TD><html:text size="30" property="email" /></TD></TR>
<TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Re-Ingrese su e-mail:</TD>
<TD><html:text size="30" property="emailConfirm" /></TD></TR>

<!-- NAME & ADDRESS --><!-- section separator -->
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
 <TR>
	<TD class=t_div align=middle height=20>
	<IMG height=5 src="files/F_bul.gif" width=3></TD>
	<TD class=t_div><SPAN class=bb>Nombre y Domicilio</SPAN></TD>
	<TD class=t_div>&nbsp;</TD>
 </TR>
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
<!-- end section separator -->

<TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Su nombre:</TD>
<TD><html:text size="30" maxlength="50" property="firstName" /></TD></TR>
<TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Su apellido:</TD>
<TD><html:text size="30" maxlength="50" property="lastName" /></TD></TR>

  
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Provincia:</TD>
    <TD><html:select property="state" >
	<html:option value="">seleccione uno</html:option>
	<html:option value="BA">Buenos Aires</html:option>
	<html:option value="CF">Capital Federal (Ciudad Aut de Bs Aires)</html:option>
	<html:option value="CA">Catamarca</html:option>
	<html:option value="CH">Chaco</html:option>
	<html:option value="CU">Chubut</html:option>
	<html:option value="CO">Córdoba</html:option>
	<html:option value="CR">Corrientes</html:option>
	<html:option value="ER">Entre Ríos</html:option>
	<html:option value="FO">Formosa</html:option>
	<html:option value="JU">Jujuy</html:option>
	<html:option value="LP">La Pampa</html:option>
	<html:option value="LR">La Rioja</html:option>
	<html:option value="ME">Mendoza</html:option>
	<html:option value="MI">Misiones</html:option>
	<html:option value="NE">Neuquén</html:option>
	<html:option value="RN">Río Negro</html:option>
	<html:option value="SL">Salta</html:option>
	<html:option value="SJ">San Juan</html:option>
	<html:option value="SL">San Luis</html:option>
	<html:option value="SC">Santa Cruz</html:option>
	<html:option value="SF">Santa Fe</html:option>
	<html:option value="SE">Santiago del Estero</html:option>
	<html:option value="TF">Tierra del Fuego</html:option>
	<html:option value="TU">Tucumán</html:option>
	</html:select></TD></TR>

<TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Codigo Postal:</TD>
<TD><html:text size="6" property="postalCode" /></TD></TR>



<!-- section separator -->
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
 <TR>
	<TD class=t_div align=middle height=20>
	<IMG height=5 src="files/F_bul.gif" width=3></TD>
	<TD class=t_div><SPAN class=bb>Datos Personales</SPAN></TD>
	<TD class=t_div>&nbsp;</TD>
 </TR>
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
<!-- end section separator -->


 <TR><TD align=middle height=10>&nbsp;</TD>
<TD class=b height=30>Fecha de Nacimiento:</TD> 
<TD>
<html:select property="birthDay"> 
		<html:option value="">Dia</html:option>
		<html:option value="1">01</html:option>
		<html:option value="2">02</html:option>
		<html:option value="3">03</html:option>
		<html:option value="4">04</html:option>
		<html:option value="5">05</html:option>
		<html:option value="6">06</html:option>
		<html:option value="7">07</html:option>
		<html:option value="8">08</html:option>
		<html:option value="9">09</html:option>
		<html:option value="10">10</html:option>
		<html:option value="11">11</html:option>
		<html:option value="12">12</html:option>
		<html:option value="13">13</html:option>
		<html:option value="14">14</html:option>
		<html:option value="15">15</html:option>
		<html:option value="16">16</html:option>
		<html:option value="17">17</html:option>
		<html:option value="18">18</html:option>
		<html:option value="19">19</html:option>
		<html:option value="20">20</html:option>
		<html:option value="21">21</html:option>
		<html:option value="22">22</html:option>
		<html:option value="23">23</html:option>
		<html:option value="24">24</html:option>
		<html:option value="25">25</html:option>
		<html:option value="26">26</html:option>
		<html:option value="27">27</html:option>
		<html:option value="28">28</html:option>
		<html:option value="29">29</html:option>
		<html:option value="30">30</html:option>
		<html:option value="31">31</html:option>
</html:select>
<html:select property="birthMonth">
		<html:option value="">Mes</html:option>
		<html:option value="1">01</html:option>
		<html:option value="2">02</html:option>
		<html:option value="3">03</html:option>
		<html:option value="4">04</html:option>
		<html:option value="5">05</html:option>
		<html:option value="6">06</html:option>
		<html:option value="7">07</html:option>
		<html:option value="8">08</html:option>
		<html:option value="9">09</html:option>
		<html:option value="10">10</html:option>
		<html:option value="11">11</html:option>
		<html:option value="12">12</html:option>
</html:select>
<html:select property="birthYear">
		<html:option value="">Año</html:option>
		<html:option value="1901">1901</html:option>
<html:option value="1902">1902</html:option>
<html:option value="1903">1903</html:option>
<html:option value="1904">1904</html:option>
<html:option value="1905">1905</html:option>
<html:option value="1906">1906</html:option>
<html:option value="1907">1907</html:option>
<html:option value="1908">1908</html:option>
<html:option value="1909">1909</html:option>
<html:option value="1910">1910</html:option>
<html:option value="1911">1911</html:option>
<html:option value="1912">1912</html:option>
<html:option value="1913">1913</html:option>
<html:option value="1914">1914</html:option>
<html:option value="1915">1915</html:option>
<html:option value="1916">1916</html:option>
<html:option value="1917">1917</html:option>
<html:option value="1918">1918</html:option>
<html:option value="1919">1919</html:option>
<html:option value="1920">1920</html:option>
<html:option value="1921">1921</html:option>
<html:option value="1922">1922</html:option>
<html:option value="1923">1923</html:option>
<html:option value="1924">1924</html:option>
<html:option value="1925">1925</html:option>
<html:option value="1926">1926</html:option>
<html:option value="1927">1927</html:option>
<html:option value="1928">1928</html:option>
<html:option value="1929">1929</html:option>
<html:option value="1930">1930</html:option>
<html:option value="1931">1931</html:option>
<html:option value="1932">1932</html:option>
<html:option value="1933">1933</html:option>
<html:option value="1934">1934</html:option>
<html:option value="1935">1935</html:option>
<html:option value="1936">1936</html:option>
<html:option value="1937">1937</html:option>
<html:option value="1938">1938</html:option>
<html:option value="1939">1939</html:option>
<html:option value="1940">1940</html:option>
<html:option value="1941">1941</html:option>
<html:option value="1942">1942</html:option>
<html:option value="1943">1943</html:option>
<html:option value="1944">1944</html:option>
<html:option value="1945">1945</html:option>
<html:option value="1946">1946</html:option>
<html:option value="1947">1947</html:option>
<html:option value="1948">1948</html:option>
<html:option value="1949">1949</html:option>
<html:option value="1950">1950</html:option>
<html:option value="1951">1951</html:option>
<html:option value="1952">1952</html:option>
<html:option value="1953">1953</html:option>
<html:option value="1954">1954</html:option>
<html:option value="1955">1955</html:option>
<html:option value="1956">1956</html:option>
<html:option value="1957">1957</html:option>
<html:option value="1958">1958</html:option>
<html:option value="1959">1959</html:option>
<html:option value="1960">1960</html:option>
<html:option value="1961">1961</html:option>
<html:option value="1962">1962</html:option>
<html:option value="1963">1963</html:option>
<html:option value="1964">1964</html:option>
<html:option value="1965">1965</html:option>
<html:option value="1966">1966</html:option>
<html:option value="1967">1967</html:option>
<html:option value="1968">1968</html:option>
<html:option value="1969">1969</html:option>
<html:option value="1970">1970</html:option>
<html:option value="1971">1971</html:option>
<html:option value="1972">1972</html:option>
<html:option value="1973">1973</html:option>
<html:option value="1974">1974</html:option>
<html:option value="1975">1975</html:option>
<html:option value="1976">1976</html:option>
<html:option value="1977">1977</html:option>
<html:option value="1978">1978</html:option>
<html:option value="1979">1979</html:option>
<html:option value="1980">1980</html:option>
<html:option value="1981">1981</html:option>
<html:option value="1982">1982</html:option>
<html:option value="1983">1983</html:option>
<html:option value="1984">1984</html:option>
<html:option value="1985">1985</html:option>
<html:option value="1986">1986</html:option>
<html:option value="1987">1987</html:option>
<html:option value="1988">1988</html:option>
</html:select></TD></TR>

 
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Usted es ...?</TD>
    <TD><html:select property="sex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
  </TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>y su estado civil es ...?</TD>
    <TD><html:select property="married"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Soltero</html:option>
	<html:option value="2">Casado</html:option>
	<html:option value="3">Separado/divorciado/viudo</html:option>
	</html:select>
  </TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Esta empleado?</TD>
    <TD><html:select property="isEmployed"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Full time</html:option>
	<html:option value="2">Part time</html:option>
	<html:option value="3">Desempleado</html:option>
	</html:select>
  </TD></TR>

   
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Su educacion?</TD>
    <TD><html:select property="educationLevel"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="Somehigh">Secundario Incompleto</html:option>
	<html:option value="Highgrad">Secundario</html:option>
	<html:option value="Somecoll">Carrera Terciaria</html:option>
	<html:option value="Collgrad">Carrera de Grado </html:option>
	<html:option value="Somepost">Posgrado</html:option>
	<html:option value="Gradgrad">Master, Doctorado</html:option></html:select></TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Cual es su ocupacion?</TD>
    <TD><html:select property="ocupation"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="Executive">Ejecutivo/Alta Gerencia</html:option>
	<html:option value="Gte">Gerencia media/Jefe</html:option>
	<html:option value="Emp">Empleado</html:option>
	<html:option value="Indep">Independiente</html:option>
	<html:option value="Homemaker">Ama de Casa</html:option>
	<html:option value="Student">Estudiante</html:option>
	<html:option value="None">Ninguno de Estos</html:option>
</html:select></TD></TR>



<!-- section separator -->
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
 <TR>
	<TD class=t_div align=middle height=20>
	<IMG height=5 src="files/F_bul.gif" width=3></TD>
	<TD class=t_div><SPAN class=bb>Datos del Grupo Familiar</SPAN></TD>
	<TD class=t_div>&nbsp;</TD>
 </TR>
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
<!-- section separator -->

 
  
<TR><TD align=middle height=10>&nbsp;</TD>
<TD colspan=2 class=b height=30>Ingrese edad y sexo de los otros miembros de la familia, que viven en el mismo hogar. 
<BR>Ingrese 0 para menores de 1 año. Deje en blanco si vive solo.</TD></tr>
    </Td></tr> 
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Primero:</TD>
    <TD class=b>Edad: <html:text property="firstAge" size="2"/> ... Sexo: 
	<html:select property="firstSex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
</TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Segundo:</TD>
    <TD class=b>Edad: <html:text property="secondAge" size="2"/> ... Sexo: 
	<html:select property="secondSex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
	</TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Tercero:</TD>
    <TD class=b>Edad: <html:text property="thirdAge" size="2"/> ... Sexo: 
	<html:select property="thirdSex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
	</TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Cuarto:</TD>
    <TD class=b>Edad: <html:text property="forthAge" size="2"/> ... Sexo: 
	<html:select property="forthSex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
	</TD></TR>
  <TR><TD align=middle height=10>&nbsp;</TD>
    <TD class=b height=30>Quinto:</TD>
    <TD class=b>Edad: <html:text property="fifthAge" size="2"/> ... Sexo: 
	<html:select property="fifthSex"> 
	<html:option value="">seleccione uno</html:option>
	<html:option value="1">Hombre</html:option>
	<html:option value="2">Mujer</html:option>
	</html:select>
	</TD></TR>


<!-- section separator -->
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
 <TR>
	<TD class=t_div align=middle height=20>
	<IMG height=5 src="files/F_bul.gif" width=3></TD>
	<TD class=t_div><SPAN class=bb>Terminos y Condiciones</SPAN></TD>
	<TD class=t_div>&nbsp;</TD>
 </TR>
 <TR>
	<TD align=middle height=10>
	<IMG height=1 src="files/1x1.gif" width=1></TD>
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>	
	<TD class=bb><IMG height=1 src="files/1x1.gif" width=1></TD>
 </TR>
<!-- section separator -->


<TR><TD align=middle height=10>&nbsp;</TD>
	<TD class=b height=30>Terminos Y Condiciones:</TD>
	<TD class=b><html:checkbox property="acceptTerms" />
        Por favor, indique aqui que acepta nuestros <A class=bl 
        href="terms.htm" target=_blank>terminos y condiciones</A>. </TD>
</TR>

<P>
<TR><TD align=middle height=10>&nbsp;</TD>
<TD colspan=2 align=middle>
<html:submit value="Listo!!" /></TD></TR>

</TBODY>
</TABLE>
<P>

</TD>
</TD></TR>
</TABLE>
</CENTER>


</TBODY>
</TD></TR>
</TABLE>
                       

</html:form></P>

</BODY></HTML>

