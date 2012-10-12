<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<style>
#votesShareSpan{background:#ED5B21;border-radius: 5px; color: #FFF; font-family: verdana; font-size: 13px; font-weight: bold; padding: 5px;}
</style>


<div id="listOfscheduleDates"></div>

<div id="scheduleDates" style="margin-top:20px;font-family:arial;font-size:13px;display:none;">
	<table class="table table-striped" style="font-size: 12px;">
	
	<tr>
		<th style="background:#21B2ED;color:#ffffff;">SNO</th>
		<th style="background:#21B2ED;color:#ffffff;">DAYWISE</th>
		<th style="background:#21B2ED;color:#ffffff;">DATE</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE  CONSTITUENCY</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE MANDAL</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE VILLAGE</th>
	</tr>

	<tr>
	<td>1</td>
	<td>Day1</td>
	<td>02.10.12</td>
	<td>HINDUPURAM</td>
	<td>HINDUPURAM</td>
	<td>HINDUPURAM</td>
	
	
	</tr>
		<tr><td>2</td><td>Day2</td>
		<td>03.10.12</td>
	<td>ANANTHAPURAM</td>
	<td>ATP URBAN </td>
	<td>SUGOORU</td>
	
	</tr>
	<tr><td>3</td><td>-</td><td>-</td>
	<td>HINDUPURAM</td>
	<td>ATP URBAN</td>
	<td>SOMADEVAMPALLI</td>

	</tr>
	<tr><td>4</td><td>-</td><td>-</td>
	<td>HINDUPURAM</td>
	<td>PARIGI</td>
	<td>GOLLAPALLI</td>
	
	</tr>
	<tr><td>5</td><td>-</td><td>-</td>
	<td>HINDUPURAM</td>
	<td>PARIGI</td>
	<td>MANESAMUDRAM</td>
	
	</tr>
	<tr><td>6</td><td>-</td><td>-</td>
	<td>HINDUPURAM</td>
	<td>PARIGI</td>
	<td>CHAALAKOOORU</td>

	</tr>
	<tr><td>7</td><td>Day3</td><td>04.10.12</td>
	<td>PENUGONDA</td>
	<td>PENUGONDA</td>
	<td>PENUGONDA</td>
	
	</tr>
	<tr><td>8</td><td>-</td><td>-</td>
	<td>PENUGONDA</td>
	<td>PENUGONDA</td>
	<td>PARAMESWARAPURAM</td>
	
	</tr>
	<tr><td>9</td><td>-</td><td>-</td>
	<td>PENUGONDA</td>
	<td>PENUGONDA</td>
	<td>DODDIKUNTA</td>
	
	</tr>
	<tr><td>10</td><td>Day4</td><td>05.10.12</td>
	<td>PENUGONDA</td>
	<td>RODDAM</td>
	<td>TURAKAALAPATNAM</td>
	
	</tr>
	<tr><td>11</td><td>-</td><td>-</td>
	<td>PENUGONDA</td>
	<td>RODDAM</td>
	<td>KOGIRA</td>
	
	</tr>
	<tr><td>12</td><td>-</td><td>-</td>
	<td>PENUGONDA</td>
	<td>RODDAM</td>
	<td>KANCHISAMUDRAM</td>
	
	</tr>
	<tr><td>13</td>	<td>-</td><td>-</td><td>PENUGONDA</td>	<td>RODDAM</td>	<td>BEEDAANUPALLI</td><td>-</td></tr>
	<tr><td>14</td><td>-</td><td>-</td>	<td>PENUGONDA</td>	<td>RODDAM</td>	<td>RAAGIMEKALAPALLI</td></tr>		

<tr><td>15</td><td>-</td><td>-</td>	<td>PENUGONDA</td>	<td>RODDAM</td>	<td>RAACHOORU</td></tr>	
<tr><td>16</td>	<td>-</td><td>-</td><td>PENUGONDA</td>	<td>RODDAM</td>
<td>RYAAPAATA</td>	</tr>
<tr><td>17</td><td>-</td><td>-</td><td>PENUGONDA</td>	<td>RODDAM</td>	<td>APPAASUPALLI</td></tr>
<tr><td>18	</td><td>Day5</td>
<td>06.10.12</td><td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>GARIMEKALAPALLI</td></tr>
<tr><td>19</td>	<td>-</td><td>-</td><td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>NANDAMURI NAGAR	</td></tr>
<tr><td>20</td><td>-</td><td>-</td>	<td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>M C PALLI</td>	</tr>
<tr><td>21</td><td>-</td><td>-</td>	<td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>THIMMMAAPURAM</td>		</tr>
<tr><td>22</td>	<td>-</td>	<td>-</td>	<td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>PEDDAKONDAPURAM	</td></tr>
<tr><td>23</td><td>-</td>	<td>-</td>		<td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>CHINNAKONDAPURAM</td>	</tr>
<tr><td>24</td>	<td>-</td><td>-</td><td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>MAKKINAVAARIPALLE</td></tr>
<tr><td>25</td>	<td>-</td><td>-</td><td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>PEDDAYYAVARIKOTTALA</td></tr>		

<tr><td>26</td><td>-</td><td>-</td>	<td>RAAPTHAADU</td>	<td>RAAMAGIRI</td>	<td>PEROOORU</td>	</tr>
<tr><td>27</td>	<td>Day6</td><td>07.10.12</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>CHENNAMPALLI</td></tr>
<tr><td>28</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>ATCHAMPALLI	</td></tr>	
<tr><td>29</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>KOTAGUDDAM</td>	</tr>
<tr><td>30</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>KAMBADOORU</td>		</tr>
<tr><td>31</td>	<td>-</td><td>-</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>RAALAPALLI</td>		</tr>
<tr><td>32</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>JALLIPALLI CROSS</td></tr>	
<tr><td>33</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>ONTAREDDYPALLI CROSS</td></tr>

<tr><td>34</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>ANDEPALLI</td>
</tr>

<tr><td>35</td><td>Day7</td><td>08.10.12</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>MANDAKURLAPALLI	</td>	</tr>

<tr><td>36</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>KURLAPALLI</td>	</tr>	

<tr><td>37</td>	<td>-</td><td>-</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>DEVARAPALLI</td></tr>		


<tr><td>38</td>		<td>-</td>	<td>-</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>THIMMMAAPURAM</td></tr>	


<tr><td>39</td>	<td>-</td><td>-</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>MULAKANURU</td></tr>		


<tr><td>40</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>MULAKANURU MITTA</td></tr>		


<tr><td>41</td>	<td>Day8</td><td>09.10.12</td><td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>DAASAMPALLI</td>		</tr>

<tr><td>42</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KAMBADOORU</td>	<td>BOYALAPALLI</td></tr>	

<tr><td>43</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KALYAANADURGAM</td>	<td>CHENNAMPALLI</td>	</tr>	


<tr><td>44</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KALYAANADURGAM</td>	<td>EDUGURRAALAPALLI</td></tr>		


<tr><td>45</td>		<td>-</td>	<td>-</td><td>KALYAANADURGAM</td>	<td>KALYAANADURGAM</td>	<td>KAAMAKKAPURAM</td>	</tr>


<tr><td>46</td><td>-</td><td>-</td>	<td>KALYAANADURGAM</td>	<td>KALYAANADURGAM</td>	<td>NAARAAYANAPURAM </td></tr>

</table>
</div>
<div >

<div style="margin-left:10px;margin-bottom:5px;">
<span title="Click here to view Schedule Padha yatra by chandrababu" onclick="getSchedule();" style="background:#21B2ED;cursor:pointer;margin-top:20px;"
id="votesShareSpan">Click here to view Vastunna Meekosam Schedule</span>
</div>
		
<script>
function getSchedule()
	 {
	 $.fx.speeds._default = 900;
	 $("#listOfscheduleDates").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 500,
								width:850,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy"><b>Vastunna Meekosam (Padha Yathra) Schedule</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#listOfscheduleDates").dialog();
	var str=$('#scheduleDates').html();
	$("#listOfscheduleDates").html(str);
	}
</script>
