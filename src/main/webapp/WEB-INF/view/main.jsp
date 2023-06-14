<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 지시자 -->
<%!// 선언부
	public Random rand = new Random();

	public int getNumber() {
		return rand.nextInt(10);
	}%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="msg" value="페이지" scope="page" />
	<c:set var="msg" value="리퀘스트" scope="request" />
	<c:set var="msg" value="세션" scope="session" />
	<c:set var="msg" value="어플리케이션" scope="application" />
	<!-- 각각의 범위에 데이터 설정가능 -->
	<h1>${msg }</h1>
	<h2>${pageScope.msg }</h2>
	<h3>${requestScope.msg }</h3>
	<h3>${sessionScope.msg }</h3>
	<h3>${applicationScope.msg }</h3>
	
	<h2>JSP 파일이 실행되었습니다.</h2>
	<form action="./main">
		<input type="number" name="num">
	</form>
	<h2><%=(Integer) request.getAttribute("number")%></h2>
	<!--  <h2>//request.getParameter("num")</h2> -->
	<!-- 
		 get.ParameterValues는 보통 체크 박스 등의 값을 받을때 사용한다. 
		 일반적으로 값은 1개의 값만을 받아오기 때문에 현재 getParameter를 사용하는 것.-->
	<ul>
		<%
		for (int i = getNumber(); i >= 0; i--) { // 스크립트릿
		%>
		<li><%=i%></li>
		<!-- 표현식 -->
		<%
		}
		%>
	</ul>

<!--    == 또는 eq
   != 또는 ne
   < 또는 lt
   > 또는 gt
   <= 또는 le
   >= 또는 ge -->
	<h3>EL - Expression Language</h3>
	<%="값"%>
	-> ${"값" }
	<br> ${10 + 20 }
	<br> ${10 div 3 }
	<br> ${10 mod 3 }
	<br> ${10 eq 10 }
	<br> ${10 ne 10 }
	<br> ${10 gt 10 }
	<br> ${10 ge 10 }
	<br> ${10 lt 10 }
	<br> ${10 le 10 }
	<br> ${10 eq 10 }
	<br> ${true and true }
	<br> ${true or true }
	<br> ${not true }
	<br> ${empty x }
	<br> ${empty x ? "없음" : "있음" }
	<!-- x에 저장된 값이 null 혹은 빈 문자열에 따른 true false -->
	${"문자열" }${"문자열" } ${10 }${"문자열" }

	<h4>EL로 request.getAttribute() 사용</h4>
	${requestScope.number }
	<!-- 객체로서 전달받는다. setAttribute()와 같은 과정이 필요하다. -->
	<br>

	<h4>EL로 request.getParameter() 사용</h4>
	${param.num }
	<!-- String을 그대로 받을 수 있다. setParameter()같은 것이 존재하지않는다. -->
	<br>

	<h3>JSTL - Core</h3>
	<h4>변수 설정</h4>
	<c:set var="name" value="10" />
	<br> ${name }
	<br>


	<h4>변수 삭제</h4>
	<c:remove var="name" />
	${empty name ? "없음" : "있음" }
	<br>


	<h4>조건문</h4>
	<c:set var="num" value="10" />
	<c:if test="${num eq 10 }">
		num에 저장된 값은 10 입니다.
		</c:if>
	<br>
	<c:choose>
		<c:when test="${num lt 5 }">
			num에 저장된 값은 5 보다 작습니다.
		</c:when>
		<c:when test="${num lt 10 }">
			num에 저장된 값은 5 이상이고 10 미만입니다.
		</c:when>
		<c:otherwise>
			num에 저장된 값은 10 이상입니다.
		</c:otherwise>
	</c:choose>
	<br>


	<h4>반복문</h4>
	<c:forEach var="i" begin="0" end="5" step="2">
			${i } , 
			</c:forEach>
	<br>


	<c:set var="arr">
			a, b, c, d, e, f
		</c:set>
	<c:forEach var="i" items="${arr }" varStatus="loop">
			값: ${i } 
			| index: ${loop.index } 
			| count: ${loop.count } 
			| first: ${loop.first } 
			| last: ${loop.last }<br>
	</c:forEach>
	<br>
		<c:forEach var="i" items="${arr }" varStatus="loop">
			값: ${i } /
	</c:forEach>

	<br>
	<c:forTokens var="s" items="010-1234-5678" delims="-">
		${s }
		</c:forTokens>

	<h4>URL 주소 생성</h4>
	<c:url var="myUrl" value="/home" />
		${myUrl }
	<br>

	<c:url var="myUrl" value="/home">
		<c:param name="name" value="value" />
		<c:param name="key" value="context" />
	</c:url>
	${myUrl }
	<br>




	<br>
	<h3>JSTL - Function</h3>

	<h4>contains(전체문자열, 일부문자열)</h4>
	${fn:contains("Hello JSTL Tag Library", "JSTL") }

	<h4>contiansIgnoreCase(전체문자열, 일부문자열)</h4>
	${fn:containsIgnoreCase("Hello JSTL Tag Library", "jstl") }

	<h4>replaced("전체문자열, 변경전문자열, 변경후문자열")</h4>
	${fn:replace("Hello JSTL Tag Library", "Tag", "태그") }

	<h4>split(전체문자열, 분리구분문자)</h4>
	<c:forEach var="s" items="${fn:split('Hello JSTL Tag Library', ' ') }">
			${s }<br>
		<!-- split을 그냥 브라우저에 출력시 참조주소값으로 나오기때문에 forEach문 사용 -->
	</c:forEach>

	<h4>toUpperCase(전체문자열)</h4>
	${fn:toUpperCase("Hello JSTL Tag Library") }

	<h4>toLowerCase(전체문자열)</h4>
	${fn:toLowerCase("Hello JSTL Tag Library") }

	<h4>trim(전체문자열)</h4>
	${fn:trim("     Hello JSTL Tag Library     ") }

	<h4>length(전체문자열)</h4>
	${fn:length("Hello JSTL Tag Library") }

	<h4>substring(전체문자열, 시작위치, 끝위치)</h4>
	${fn:substring("Hello JSTL Tag Library", 6, 10) }



	<h3>JSTL - Formatting</h3>

	<h4>숫자 포멧</h4>
	<c:set var="num1" value="123456789" />
	<c:set var="num2" value="12345.6789" />
	<c:set var="num3" value="0.1234" />

	<fmt:formatNumber value="${num1 }" type="number" />
	<br>
	<fmt:formatNumber value="${num2 }" type="number" />
	<br>

	<fmt:formatNumber value="${num1 }" type="number" groupingUsed="false" />
	<br>
	<fmt:formatNumber value="${num2 }" type="number" groupingUsed="false" />
	<br>

	<fmt:setLocale value="en_US" />
	<fmt:formatNumber value="${num1 }" type="currency" />
	<br>

	<fmt:setLocale value="ko_KR" />
	<fmt:formatNumber value="${num2 }" type="currency" />
	<br>

	<fmt:formatNumber value="${num2 }" type="currency" currencySymbol="￥" />
	<br>
	<fmt:formatNumber value="${num2 }" type="currency"
		pattern="###,###.###원" />
	<br>

	<fmt:formatNumber value="${num1 }" type="percent" />
	<br>
	<fmt:formatNumber value="${num2 }" type="percent" />
	<br>

	<fmt:formatNumber value="${num3 }" type="percent" />
	<br>
	<fmt:formatNumber value="${num3 }" type="percent" maxFractionDigits="2" />
	<br>

	<h4>날짜 포맷</h4>

	<c:set var="now" value="<%=new java.util.Date()%>" />

	<fmt:formatDate value="${now }" type="date" />
	<br>
	<fmt:formatDate value="${now }" type="date" dateStyle="short" />
	<br>
	<fmt:formatDate value="${now }" type="date" dateStyle="long" />
	<br>

	<fmt:setLocale value="en_US" />
	<fmt:formatDate value="${now }" type="date" />
	<br>
	<fmt:formatDate value="${now }" type="date" dateStyle="short" />
	<br>
	<fmt:formatDate value="${now }" type="date" dateStyle="long" />
	<br>

	<fmt:formatDate value="${now }" type="time" />
	<br>
	<fmt:formatDate value="${now }" type="time" timeStyle="short" />
	<br>
	<fmt:formatDate value="${now }" type="time" timeStyle="long" />
	<br>

	<fmt:formatDate value="${now }" type="both" />
	<br>
	<fmt:formatDate value="${now }" type="both" dateStyle="short"
		timeStyle="short" />
	<br>
	<fmt:formatDate value="${now }" type="both" dateStyle="long"
		timeStyle="long" />
	<br>

	<fmt:formatDate value="${now }" type="both"
		pattern="yyyy-MM-dd a hh:mm:ss E요일" />
	<br>

	<fmt:parseDate var="pDate" value="2023-01-17" pattern="yyyy-MM-dd" />
	<fmt:formatDate value="${pDate }" type="date" />

	<hr>
	<fmt:setTimeZone value="GMT-1" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long"
		timeStyle="medium" />
	<br>

	<fmt:setTimeZone value="GMT+1" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long"
		timeStyle="medium" />
	<br>

	<fmt:setTimeZone value="GMT" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long"
		timeStyle="medium" />
	<br>

	<fmt:setTimeZone value="GMT+9" />
	<fmt:formatDate value="${now }" type="both" dateStyle="long"
		timeStyle="medium" />
	<br>	
</body>
</html>