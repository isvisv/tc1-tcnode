<%@ page import="com.topcoder.shared.util.ApplicationServer" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Edit Personal Information</title>
        <script type="text/javascript" src="/js/regReskin20080904.js"></script>
        <link type="image/x-icon" rel="shortcut icon" href="/i/favicon.ico"/>
        <jsp:include page="style.jsp">
            <jsp:param name="key" value="tc_reg"/>
        </jsp:include>
    </head>
    
    <body>
		<jsp:include page="header.jsp" />
		<div id="heading" class="registrationSuccessfulHedading">	
			<div class="inner">
				<a href="profile_view_demographics.jsp" title="Your Profile" id="your_profile_link"><span>Your Profile</span></a>
				<span id="demographics"><span>/ Demographics</span></span>
			</div><!-- END .inner -->
		</div>
            
		<form name="form" method="post" action="profile_view_demographics.jsp">
			<div id="content" class="registrationForm">
				<div class="leftSide">
					<p>* = required information</p>
				</div>
				<div class="information rightSide">
					<h4><span>Demographic Information</span><span class="end">&nbsp;</span></h4>
					<div class="content-body">
						<ul class="form">
							<li class="row">
								<label for="gender">* Gender:</label>
								<select name="gender" id="gender">
									<option value="male" selected="selected">male</option>
									<option value="female">female</option>
								</select>
							</li>
							<li class="row">
								<label for="ethnic">* Ethnic Background:</label>
								<select name="ethnic" id="ethnic">
									<option>Decline To Answer</option>
								</select>
							</li>
							<li class="row">
								<label for="primary_interest">* Primary Interest in TopCoder:</label>
								<select name="primary_interest" id="primary_interest">
									<option>Decline To Answer</option>
								</select>
							</li>
							<li class="row">
								<label for="primary_interest">* How Did You Hear About TopCoder:</label>
								<select name="primary_interest" id="primary_interest">
									<option>Decline To Answer</option>
								</select>
							</li>
							<li class="row">
								<label for="clubs">* Clubs/Organizations:</label>
								<select name="clubs" id="clubs">
									<option>Decline To Answer</option>
								</select>
							</li>
							<li class="row">
								<label for="clubs_other">Other Clubs/Organizations:</label>
								<input  type="text" name="other_clubs" id="other_clubs" />
							</li>
							<li class="row">
								<label for="employed">* Currently Employed:</label>
								<select name="employed" id="employed">
									<option value="1" selected="selected">yes</option>
									<option value="0">no</option>
								</select>
							</li>
							<li class="row">
								<label for="industry">* Industry:</label>
								<select name="industry" id="industry">
									<option>Other</option>
								</select>
							</li>
							<li class="row">
								<label for="purchasing">* Purchasing Role:</label>
								<select name="purchasing" id="purchasing">
									<option>Other</option>
								</select>
							</li>
							<li class="row">
								<label for="revenue">* Company Revenue:</label>
								<select name="revenue" id="revenue">
									<option>Other</option>
								</select>
							</li>
							<li class="row">
								<label for="it_spending">* Company Spending on IT:</label>
								<select name="it_spending" id="it_spending">
									<option>Other</option>
								</select>
							</li>
							<li class="row">
								<label for="area_interest">* Primary Area of Interest:</label>
								<select name="area_interest" id="area_interest">
									<option>Other</option>
								</select>
							</li>
							<li class="row">
								<label for="shirt">* Shirt Size:</label>
								<select name="shirt" id="shirt">
									<option>Other</option>
								</select>
								<p class="label-description">In the event that you win/earn a shirt for a TopCoder event, it is convenient for us to have your shirt size on record. <br />
							If you choose not to answer this question, we will assume that you do not want any shirts.</li>
							<li class="row">
								<label for="title">* Job Title:</label>
								<input  name="title" type="text" id="title" value="Information Architect" />
							</li>
							<li class="row">
								<label for="company">* Company:</label>
								<input  name="company" type="text" id="company" value="TopCoder" />
							</li>
							<li class="row">
								<label for="hear">Resume:</label>
								<input name="resume" type="text" id="resume" />
								<input type="submit" name="button" id="button" value="browse" />
							</li>


							<li class="form-bottom">
								<a class="redBtn btnSave" href="JavaScript:document.form.submit();">
									<span class="buttonMask"><span class="text">SAVE CHANGES</span></span>
								</a>
								<p>
									Or cancel and 
									<a href="profile_view_demographics.jsp" title="Profile Page">return to your profile page</a>
								</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</form>
		<jsp:include page="footer.jsp" />
    </body>
</html>
