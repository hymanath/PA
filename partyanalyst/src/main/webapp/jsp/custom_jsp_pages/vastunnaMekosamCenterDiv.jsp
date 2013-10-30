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
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE  District</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE  CONSTITUENCY</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE MANDAL</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE VILLAGE</th>
	</tr>

	


<tr><td>1</td><td>Day1</td><td>	02.10.12</td><td>ANATHAPUR</td>	<td>HINDUPURAM</td><td>ATP URBAN	</td><td>SOOLOORU</td></tr>
<tr><td>2</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>HINDUPURAM</td><td>ATP URBAN	</td><td>MELAPURAM</td></tr>
<tr><td>3</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>HINDUPURAM</td><td>ATP URBAN	</td><td>KOTNOORU</td></tr>
<tr><td>4</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>HINDUPURAM</td><td>ATP URBAN	</td><td>KOLLAKUNTA</td></tr>
<tr><td>5</td><td>Day2</td><td>	03.10.12</td><td>ANATHAPUR</td>	<td>HINDUPURAM</td><td>ATP URBAN	</td><td>MANESAMUDRAM</td></tr>
<tr><td>6</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>GOLLAPALLI</td></tr>
<tr><td>7</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>NADIMPALLI</td></tr>
<tr><td>8</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>KETIGAANICHERUVU</td></tr>
<tr><td>9</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>CHAALKOORU</td></tr>
<tr><td>10</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>GUDDAMANAAGEPALLI</td></tr>
<tr><td>11</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>BRAAHMANAPALLI</td></tr>
<tr><td>12</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>CHAAKARLAPALLI</td></tr>
<tr><td>13</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>PARIGI	</td><td>SOMANDEPALLI</td></tr>
<tr><td>14</td><td>Day3</td><td>	04.10.12</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>PENUGONDA</td></tr>
<tr><td>15</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>RODDAM</td></tr>
<tr><td>16</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>KAANAPURAM</td></tr>
<tr><td>17</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>MAHADEVAPALLI</td></tr>
<tr><td>18</td><td>-</td>	<td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>BAKSAMPALLI</td></tr>
<tr><td>19</td><td>-</td><td>-</td>	<td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>LGB NAGAR</td></tr>
<tr><td>20</td><td>Day4</td><td>	05.10.12</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>KOGIRA</td></tr>
<tr><td>21</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>RAAGIMEKALAPALLI</td></tr>
<tr><td>22</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>RAACHOORU</td></tr>
<tr><td>23</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>PENUGONDA</td><td>RODDAM	</td><td>ERRABENCH</td></tr>
<tr><td>24</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>NANDAMURINAGAR</td></tr>
<tr><td>25</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>GARIMEKALAPALLI</td></tr>
<tr><td>26</td><td>Day5</td><td>	06.10.12</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>GARIMEKALAPALLI</td></tr>
<tr><td>27</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>M C PALLI</td></tr>
<tr><td>28</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>THIMMAPURAM</td></tr>
<tr><td>29</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>REDDYVARIPALLI</td></tr>
<tr><td>30</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>KONDAPURAM</td></tr>
<tr><td>31</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>CHINNAKONDAPURAM</td></tr>
<tr><td>32</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>MAKKENAVARIPALLI</td></tr>
<tr><td>33</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>PEDDAIAHGAARIKOTALA</td></tr>
<tr><td>34</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>PEROORU</td></tr>
<tr><td>35</td><td>Day6</td><td>	07.10.12</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>PEROORU</td></tr>
<tr><td>36</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAPTHAADU</td><td>RAAMAGIRI	</td><td>GURRALAPALLI</td></tr>
<tr><td>37</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KURAAKULAPALLI</td></tr>
<tr><td>38</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>CHENNAMPALLI</td></tr>
<tr><td>39</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>RAALA ANANTHAPURAM</td></tr>
<tr><td>40</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>ATCHAMPALLI</td></tr>
<tr><td>41</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KOTAGUDDAM,</td></tr>
<tr><td>42</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>RAALAPALLI</td></tr>
<tr><td>43</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>ONTAREDDIPALLI</td></tr>
<tr><td>44</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KAMDOORU</td></tr>
<tr><td>45</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KOTTHAPALLI</td></tr>
<tr><td>46</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>ANDEPALLI</td></tr>
<tr><td>47</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>MANDAKURLAPALLI</td></tr>
<tr><td>48</td><td>Day7</td><td>	08.10.12</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>MANDAKURLAPALLI</td></tr>
<tr><td>49</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KADIRI</td></tr>
<tr><td>50</td><td>-<td>-</td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>DEVARAPALLI</td></tr>
<tr><td>51</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>MULAKANOORU</td></tr>
<tr><td>52</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>MULAKANOORUMITTA</td></tr>
<tr><td>53</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>DAASAMPALLI</td></tr>
<tr><td>54</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>BOYALAPALLI</td></tr>
<tr><td>55</td><td>	-</td><td>	-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>RAAMAKKAPALLI</td></tr>
<tr><td>56</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KURAHARABALLI</td></tr>
<tr><td>57</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>NAARAAYANAPURAM</td></tr>
<tr><td>58</td><td>Day8</td><td>	09.10.12</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>NAARAAYANAPURAM</td></tr>
<tr><td>59</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>ERRAMPALLI</td></tr>
<tr><td>60</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>KALYAANADURGAM</td></tr>
<tr><td>61</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>DODAGATTA</td></tr>
<tr><td>62</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>CHAARPARI</td></tr>
<tr><td>63</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>CHAAPARI TANDA</td></tr>
<tr><td>64</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>KALYAANADURGAM</td><td>KAMDOORU	</td><td>MAADIREDDIPALLI</td></tr>
<tr><td>65</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>VIRAPAAPALLI</td></tr>
<tr><td>66</td><td>Day9</td><td>	10.10.12</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>VIRAPAAPALLI</td></tr>
<tr><td>67</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>SEERPI</td></tr>
<tr><td>68</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>VENKATAADRIPALLI</td></tr>
<tr><td>69</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>SEERPI</td></tr>
<tr><td>70</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>SEERPI KOTTALA</td></tr>
<tr><td>71</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>AAVULENNA CROSS</td></tr>
<tr><td>72</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>ERRAGUDI CROSS</td></tr>
<tr><td>73</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>BELUGUPPA</td></tr>
<tr><td>74</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAYADURGAM</td><td>RAAYADURGAM	</td><td>PENUKALAPAADU</td></tr>
<tr><td>75</td><td>Day10</td><td>	11.10.12</td><td>ANATHAPUR</td>	<td>RAAYADURGAM</td><td>RAAYADURGAM	</td><td>HANAKANAHAL</td></tr>
<tr><td>76</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAYADURGAM</td><td>RAAYADURGAM	</td><td>SAALAPURAM</td></tr>
<tr><td>77</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>RAAYADURGAM</td><td>RAAYADURGAM	</td><td>N HANUMAAPURAM</td></tr>
<tr><td>78</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>NIMBAGALLU</td></tr>
<tr><td>79</td><td>Day11</td><td>	12.10.12</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>RENUMAAKULAPALLI</td></tr>
<tr><td>80</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>CHAABAALA CROSS</td></tr>
<tr><td>81</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>CHINNA HOTOORU</td></tr>
<tr><td>82</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>GADE HOTOORU</td></tr>
<tr><td>83</td><td>Day12</td><td>	13.10.12</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>GADE HOTOORU</td></tr>
<tr><td>84</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>POTTIPAADU</td></tr>
<tr><td>85</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>CHAAYAPURAM</td></tr>
<tr><td>86</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>KONAKANDLA</td></tr>
<tr><td>87</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>GUNTHAKALLU</td></tr>
<tr><td>88</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>HANUMAAN JN</td></tr>
<tr><td>89</td><td>Day13</td><td>	14.10.12</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>GUNTHAKALLU</td></tr>
<tr><td>90</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>BEERAPPAGUDI CRCLE</td></tr>
<tr><td>91</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>MASTAANVALI DARGA</td></tr>
<tr><td>92</td><td>-</td><td>-</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>RTC BUS STAND</td></tr>
<tr><td>93</td><td>-</td><td>-	</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>KL RAO CIRCLE</td></tr>
<tr><td>94</td><td>-</td><td>-	</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>DHARMAVARAM GATE</td></tr>
<tr><td>95</td><td>-</td><td>-	</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>TB CIRCLE</td></tr>
<tr><td>96</td><td>-</td><td>-	</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>BENCH KOTTAALA</td></tr>
<tr><td>97</td><td>-</td><td>-	</td><td>ANATHAPUR</td>	<td>URAVAKONDA</td><td>URAVAKONDA	</td><td>PORTAR DARGA</td></tr>
<tr><td>98</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>CHIPPAGIRI (KURNOOL DIST)</td></tr>
<tr><td>99</td><td>Day14</td><td>	15.04.12</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>BELDONA</td></tr>
<tr><td>100</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>NEMAKALLU</td></tr>
<tr><td>101</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>SANGAALA</td></tr>
<tr><td>102</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>THIMMAPURAM</td></tr>
<tr><td>103</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>NAGARADONA</td></tr>
<tr><td>104</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>CHIPPAGIRI	</td><td>RAAMADURGAM</td></tr>
<tr><td>105</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>AALOORU TOWN</td></tr>
<tr><td>106</td><td>Day15</td><td>	16.10.12</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>HULEBEEDU</td></tr>
<tr><td>107</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>TUMMALABEEDU</td></tr>
<tr><td>108</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>AGSANAKALLU</td></tr>
<tr><td>109</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>MANEKURTHI</td></tr>
<tr><td>110</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>GONEHAL </td></tr>
<tr><td>111</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>Alur</td><td>Alur	</td><td>KAMMARACHEDU</td></tr>
<tr><td>112</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>CHAAGI</td></tr>
<tr><td>113</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>DHANAAPURAM</td></tr>
<tr><td>114</td><td>Day16</td><td>	17.10.12</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>AAREKALLU</td></tr>
<tr><td>115</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>KOTEKALLU</td></tr>
<tr><td>116</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>DHANAAPURAM</td></tr>
<tr><td>117</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>KALLUBAAVI</td></tr>
<tr><td>118</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>AADONI</td></tr>
<tr><td>119</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>BANDIMETTA</td></tr>
<tr><td>120</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>TIKKASWAAMY DARAGA</td></tr>
<tr><td>121</td><td>-</td><td>-</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>BYCHEGIRI</td></tr>
<tr><td>122</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>NAARAAYANAPURAM</td></tr>
<tr><td>123</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>ADONI</td><td>ADONI	</td><td>AAREKALLU</td></tr>
<tr><td>124</td><td>Day17</td><td>	18.10.12</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>CHENNAPURAM</td></tr>
<tr><td>125</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>BODABANDA</td></tr>
<tr><td>126</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>BANAVAASI</td></tr>
<tr><td>127</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>VENKATAGIRI</td></tr>
<tr><td>128</td><td>-</td><td>-</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>HANUMAAPURAM</td></tr>
<tr><td>129</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>VENKATAAPURAM CLY</td></tr>
<tr><td>130</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>YEMMIGANNURU</td></tr>
<tr><td>131</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>KALLUGUTTA</td></tr>
<tr><td>132</td><td>Day18</td><td>	19.10.12</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>YEMMIGANNURU TOWN</td></tr>
<tr><td>133</td><td>-</td><td>-</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>KALLUGOTLA</td></tr>
<tr><td>134</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>CHINNAPURAM</td></tr>
<tr><td>135</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>THIMMAPURAM</td></tr>
<tr><td>136</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>DAIVAMDINNE</td></tr>
<tr><td>137</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>GAARLADINNE</td></tr>
<tr><td>138</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>YEMMIGANNURU</td><td>YEMMIGANNURU	</td><td>PESALADINNE</td></tr>
<tr><td>139</td><td>Day19</td><td>	20.10.12</td><td>KURNOOL</td>	<td>KODAMURU</td><td>KODAMURU	</td><td>KAMPAADU</td></tr>
<tr><td>140</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>KODAMURU</td><td>C.BELAGAL 	</td><td>BELAGAL</td></tr>
<tr><td>141</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>KODAMURU</td><td>C.BELAGAL 	</td><td>POLAKAL</td></tr>
<tr><td>142</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>JULAKAL</td></tr>
<tr><td>144</td><td>Day20</td><td>	21.10.12</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>JULAKAL</td></tr>
<tr><td>145</td><td>-</td><td>-</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>PONNEKAL</td></tr>
<tr><td>146</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>GUDUR</td></tr>
<tr><td>147</td><td>-</td><td>-	</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>MUNAGAALA</td></tr>
<tr><td>148</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>KODAMURU</td><td>GUDUR	</td><td>MALLAPURAM</td></tr>
<tr><td>149</td><td>-</td><td>	-</td><td>KURNOOL</td>	<td>KODAMURU</td><td>C.BELAGAL 	</td><td>PALAVAGU</td></tr>



</table>
</div>
<div >

<div style="margin-left:10px;margin-bottom:5px;">
<span title="Click here to view Schedule Padha yatra by chandrababu" onclick="getSchedule();" style="background:#21B2ED;cursor:pointer;margin-top:20px;"
id="votesShareSpan">Click here to view Vastunna Meekosam Schedule</span>
</div>
<br>
<div>

<fieldset >
 <legend style="font-weight:bold;font-family:Verdana;">View Profiles</legend>
<table>
<tr><td><a href="candidateElectionResultsAction.action?candidateId=3424" title="Click here to view Nara Chandra Babu Naidu Profile,News,Photos,Vedio Gallaries"><img width="125" height="120" src="images/candidates/NARA CHANDRABABU NAIDU.jpg"></img></a></td>
<td><a href="candidateElectionResultsAction.action?candidateId=7203"> <img width="125" height="120" src="images/candidates/NANDAMURI TARAKA RAMA RAO.jpg" style="margin-left:5px;" title="Click here to view  Nandamuri Taraka Rama Rao Profile,News,Photos,Vedio Gallaries"></img></a></td>
  
<td><a href="partyPageAction.action?partyId=872" style="font-family:helvetica;" title="Click here to view  Telugu Desham Party Details"><img width="125" height="120" src="images/party_flags/TDP.PNG" style="margin-left:5px;"></a></td></tr>
<tr><td><span><a href="candidateElectionResultsAction.action?candidateId=3424" style="font-family:helvetica;" title="Click here to view Nara Chandra Babu Naidu Profile,News,Photos,Vedio Gallaries">Nara Chandra Babu Naidu</a></span></td>
<td><span><a href="candidateElectionResultsAction.action?candidateId=7203" style="font-family:helvetica;" title="Click here to view  Nandamuri Taraka Rama Rao Profile,News,Photos,Vedio Gallaries"> Nandamuri Taraka Rama Rao</a></span></td>
<td><span><a href="partyPageAction.action?partyId=872" title="Click here to view  Telugu Desham Party Details">Telugu Desham Party</a></span></td>
</tr>
 </table> 
</fieldset>
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
