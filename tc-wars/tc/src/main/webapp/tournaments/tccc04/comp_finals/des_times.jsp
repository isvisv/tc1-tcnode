<%@  page
  language="java"
  errorPage="../../../errorPage.jsp"
  import="com.topcoder.common.web.data.Navigation,com.coolservlets.forum.*,
          java.text.SimpleDateFormat,
          java.util.HashMap,
          java.util.Iterator,
          com.topcoder.shared.util.ApplicationServer"
%>

<%
    String member = request.getParameter("member")==null?"":request.getParameter("member");
    String time = request.getParameter("time")==null?"":request.getParameter("time");
%>

<!-- Times for Pops -->

    <% if (member.equals("adic")) { %>
            <table border="0" cellspacing="0" cellpadding="0">
               <tr valign="middle">
                    <td class="compDocsTimeOff"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                    <td class="<%=time.equals("init")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=adic_init_doc1">Initial Submission</a></td>
                    <td class="<%=time.equals("final")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=adic_final_doc1">FINAL</a></td>
                    <td class="compDocsTimeOff" width="99%"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                </tr>
            </table>

<!-- Times for aksonov -->

    <% } else if (member.equals("aksonov")) { %>
            <table border="0" cellspacing="0" cellpadding="0">
               <tr valign="middle">
                    <td class="compDocsTimeOff"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                    <td class="<%=time.equals("init")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=aksonov_init_doc1">Initial Submission</a></td>
                    <td class="<%=time.equals("final")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=aksonov_final_doc1">FINAL</a></td>
                    <td class="compDocsTimeOff" width="99%"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                </tr>
            </table>

<!-- Times for MPhk -->

    <% } else if (member.equals("MPhk")) { %>
            <table border="0" cellspacing="0" cellpadding="0">
               <tr valign="middle">
                    <td class="compDocsTimeOff"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                    <td class="<%=time.equals("init")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=MPhk_init_doc1">Initial Submission</a></td>
                    <td class="<%=time.equals("final")?"compDocsTimeOn":"compDocsTimeOff"%>"><a href="/tc?module=Static&d1=tournaments&d2=tccc04&d3=comp_finals&d4=MPhk_final_doc1">FINAL</a></td>
                    <td class="compDocsTimeOff" width="99%"><img src="/i/clear.gif" alt="" width="1" height="1" border="0"></td>
                </tr>
            </table>

    <% } %>
