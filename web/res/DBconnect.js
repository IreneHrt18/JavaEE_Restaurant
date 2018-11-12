var objdbConn = new ActiveXObject("ADODB.Connection");    
var strdsn = "Driver={SQL Server};SERVER=(local);UID=sa;PWD=111111;DATABASE=hljdatabase";   
objdbConn.Open(strdsn);       
var objrs = objdbConn.Execute("SELECT * FROM restaurant ");                  // 输入本地的表  
var fdCount = objrs.Fields.Count - 1;           
if (!objrs.EOF){                                 
  document.write("<table border=1><tr>");     
  for (var i=0; i <= fdCount; i++)                  
      document.write("<td><b>" + objrs.Fields(i).Name + "</b></td>");  
  document.write("</tr>");  
  
  while (!objrs.EOF){                     
    document.write("<tr>");               
    for (i=0; i <= fdCount; i++)  
       document.write("<td valign='top'>" + objrs.Fields(i).Value + "</td>");  
    document.write("</tr>");  
    objrs.moveNext();                   
  }  
  document.write("</table>");   
}  
else   
  document.write("数据库内没有记录!<br>");  
objrs.Close();                           
objdbConn.Close();                    