var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
var day = date.getDate();
document.getElementById('published').innerText = year + '년 ' + month + '월 ' + day + '일';