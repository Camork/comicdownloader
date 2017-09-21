<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>漫画下载器</title>
</head>

<body>

<form action="<%=request.getContextPath()%>/getComic" method="post" style="text-align: center;margin-top: 10%">

    <table align="center" style="border-spacing:0 20px;" >

        <tr>
            <td colspan="2"><h1> 漫画下载器</h1></td>
        </tr>
        <tr>
        <tr>
            <td>漫画名:</td>
            <td><input type="text" name="cartoonName" value="火影忍者"></td>
        </tr>
        <tr>
            <td>卷:</td>
            <td><input type="text" name="chapter" value="第600话"></td>
        </tr>
        <tr>
            <td colspan="2"><span>请参照http://www.verydm.com/网站上对应的卷或章节进行下载</span></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" id="submit"></td>
        </tr>
    </table>
</form>
</body>

</html>

