<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<input type="hidden" name="page" value="${selPage}" />


<div class="fg-toolbar tableFooter">
	<div class="dataTables_info" id="dynamic_info">Total ${recordCount} entries</div>
	
	<c:if test="${pageCount > 1}">
		<div class="dataTables_paginate paging_full_numbers" id="dynamic_paginate">
			<c:if test="${(selPage-1)>=0}">
				<a tabindex="0" href="${baseURL}/user/specialist/registration?page=${selPage-1}" class="previous paginate_button paginate_button_disabled" id="dynamic_previous">Previous</a>
			</c:if>
			<span>
				<c:forEach begin="${lowerLimit}" end="${upperLimit}" var="pg">
					<c:set var="curPage" value=""/>
					<c:if test="${pg == selPage}"><c:set var="curPage">paginate_active</c:set></c:if>
					<a tabindex="0" href="${baseURL}/user/specialist/registration?page=${pg}" class="paginate_button ${curPage}">${pg+1}</a>
				</c:forEach>
			</span>
			<c:if test="${(selPage+1)<=(pageCount-1)}">
				<a tabindex="0" href="${baseURL}/user/specialist/registration?page=${selPage+1}" class="next paginate_button paginate_button_disabled" id="dynamic_next">Next</a>
			</c:if>
		</div>
	</c:if>
</div>
