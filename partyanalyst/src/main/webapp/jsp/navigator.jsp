
	<table align="left" border="0" id="headingtable">
		<tr>
			<th> State:</th><td><a id="stateref" href="statePageAction.action?stateId=${navigationVO.stateInfo.id}">${navigationVO.stateInfo.name}</a></td>
			<c:if test='${! empty navigationVO.districtInfo }'>
				<th>></th>
				<th>District:</th>
				<c:forEach var="district" items="${navigationVO.districtInfo}">
					<td><a id="districtref" href="districtPageAction.action?districtId=${district.id}&districtName=${district.name}">${district.name}</a></td>
				</c:forEach>
			</c:if>
			<c:if test='${! empty navigationVO.pcsInfo }'>
				<th>></th>
				<th>Parliament:</th>
					<c:forEach var="parliament" items="${navigationVO.pcsInfo}">
						<td><a id="parliamentref" href="constituencyPageAction.action?constituencyId=${parliament.id}">${parliament.name}</a></td>
					</c:forEach>
				</c:if>
			<c:if test='${! empty navigationVO.acsInfo }'>
				<th>></th>
				<th>Assembly:</th>
				<c:forEach var="assembly" items="${navigationVO.acsInfo}">
					<td><a id="assemblyref" href="constituencyPageAction.action?constituencyId=${assembly.id}">${assembly.name}</a></td>
				</c:forEach>
			</c:if>
		</tr>
	</table>
	
