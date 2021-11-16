<%@page import="model.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel = "stylesheet"
   type = "text/css"
   href = "Style.css" />
   
   <style>
   	@CHARSET "ISO-8859-1";

@import url('https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css');
@media(min-width:768px) {
    body {
        margin-top: 50px;
    }
    /*html, body, #wrapper, #page-wrapper {height: 100%; overflow: hidden;}*/
}

#wrapper {
    padding-left: 0;    
}

#page-wrapper {
    width: 100%;        
    padding: 0;
    background-color: #fff;
}

@media(min-width:768px) {
    #wrapper {
        padding-left: 225px;
    }

    #page-wrapper {
        padding: 22px 10px;
    }
}

/* Top Navigation */

.top-nav {
    padding: 0 15px;
}

.top-nav>li {
    display: inline-block;
    float: left;
}

.top-nav>li>a {
    padding-top: 20px;
    padding-bottom: 20px;
    line-height: 20px;
    color: #fff;
}

.top-nav>li>a:hover,
.top-nav>li>a:focus,
.top-nav>.open>a,
.top-nav>.open>a:hover,
.top-nav>.open>a:focus {
    color: #fff;
    background-color: #1a242f;
}

.top-nav>.open>.dropdown-menu {
    float: left;
    position: absolute;
    margin-top: 0;
    /*border: 1px solid rgba(0,0,0,.15);*/
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    background-color: #fff;
    -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
    box-shadow: 0 6px 12px rgba(0,0,0,.175);
}

.top-nav>.open>.dropdown-menu>li>a {
    white-space: normal;
}

/* Side Navigation */

@media(min-width:768px) {
    .side-nav {
        position: fixed;
        top: 60px;
        left: 225px;
        width: 225px;
        margin-left: -225px;
        border: none;
        border-radius: 0;
        border-top: 1px rgba(0,0,0,.5) solid;
        overflow-y: auto;
        background-color: #222;
        /*background-color: #5A6B7D;*/
        bottom: 0;
        overflow-x: hidden;
        padding-bottom: 40px;
    }

    .side-nav>li>a {
        width: 225px;
        border-bottom: 1px rgba(0,0,0,.3) solid;
    }

    .side-nav li a:hover,
    .side-nav li a:focus {
        outline: none;
        background-color: #1a242f !important;
    }
}

.side-nav>li>ul {
    padding: 0;
    border-bottom: 1px rgba(0,0,0,.3) solid;
}

.side-nav>li>ul>li>a {
    display: block;
    padding: 10px 15px 10px 38px;
    text-decoration: none;
    /*color: #999;*/
    color: #fff;    
}

.side-nav>li>ul>li>a:hover {
    color: #fff;
}

.navbar .nav > li > a > .label {
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  position: absolute;
  top: 14px;
  right: 6px;
  font-size: 10px;
  font-weight: normal;
  min-width: 15px;
  min-height: 15px;
  line-height: 1.0em;
  text-align: center;
  padding: 2px;
}

.navbar .nav > li > a:hover > .label {
  top: 10px;
}

.navbar-brand {
    padding: 5px 15px;
}
   </style>
<meta charset="UTF-8">
<title>Event Management</title>
</head>
<body>


	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	
	<div id="throbber" style="display:none; min-height:120px;"></div>
<div id="noty-holder"></div>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li><a href="#" data-placement="bottom" data-toggle="tooltip" href="#" data-original-title="Stats"><i class="fa fa-bar-chart-o"></i>
                </a>
            </li>            
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin User <b class="fa fa-angle-down"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-fw fa-user"></i> Edit Profile</a></li>
                    <li><a href="#"><i class="fa fa-fw fa-cog"></i> Change Password</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="fa fa-fw fa-power-off"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <h1 style="color:white; text-align:center;">Event Management App</h1>
            <ul class="nav navbar-nav side-nav">
            
            	<li>
                    <a href="homeView1.jsp"><i class="fa fa-fw fa-calendar"></i>EVENTS</a>
                </li>

                <li>
                    <a href="Payment1.jsp"><i class="fa fa-fw fa-credit-card"></i>PAYMENTS</a>
                </li>
                <li>
                    <a href="Supplier1.jsp"><i class="fa fa-fw fa-paper-plane-o"></i>SUPPLIERS</a>
                </li>
                <li>
                    <a href="Login.jsp"><i class="fa fa-fw fa-sign-out"></i>LOGOUT</a>
                </li>
                
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row" id="main" >
                <div class="col-sm-12 col-md-12 well" id="content">
                <h1>Update Event</h1>


	<%
	Event event = (Event) request.getAttribute("event");
	%>
			<form method="POST" action="UpdateEventServlet1">
			
					
					<div class="form-group row">
					    <label class="col-sm-2 col-form-label">Event ID</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" name="eventID" disabled="disabled" value="<%=event.getEventID()%>" >
					    </div>
					  </div>
					  
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">Customer Name</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" name="customerName" value="<%=event.getCustomerName()%>" >
					    </div>
					  </div>
                    
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">Event Type</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name = "type" onChange="calculatePrice()" id="type">
  								<option name = "type" value="wedding">Wedding</option>
  								<option name = "type" value="birthday_party">Birthday Party</option>
  								<option name = "type" value="funeral">Funeral</option>
  								<option name = "type" value="get_together">Get Together</option>
  								<option name = "type" value="prize_giving">Prize Giving</option>
  								<option name = "type" value="other">Other</option>
							</select>
					    </div>
					  </div>
					  
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">Member quantity</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" name="quantity" value="<%=event.getQuantity()%>" required="required" onChange="calculatePrice()" id="qnt">
					    </div>
					  </div>
					  
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">Event Hours</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" name="hours" value="<%=event.getHours()%>" required="required" onChange="calculatePrice()" id="hours">
					    </div>
					    
					  </div>
					  
					  <div class="form-group row">
						    <label class="col-sm-2 col-form-label">Location</label>
				  		  	<div class="form-check">
					  		  	<div class="col-sm-10">
					  		  		<input class="form-check-input" type="radio" name="location" value="indoor"  tabindex="1" onChange="calculatePrice()" id="location">
								  	<label class="form-check-label" for="exampleRadios1"> Indoor</label>
								  	
								  	<input class="form-check-input" type="radio" name="location" value="outdoor" tabindex="2"  onChange="calculatePrice()" id="location">
								  	<label class="form-check-label" for="exampleRadios2"> Outdoor</label>
					  		  	</div>
							</div>
							
						</div>
						
						<div class="form-group row">
						    <label class="col-sm-2 col-form-label">Time</label>
				  		  	<div class="form-check">
					  		  	<div class="col-sm-10">
					  		  		<input class="form-check-input" type="radio" name="time" value="day"  tabindex="1" onChange="calculatePrice()" id="time">
								  	<label class="form-check-label" for="exampleRadios1"> Day</label>
								  	
								  	<input class="form-check-input" type="radio" name="time" value="night" tabindex="2"  onChange="calculatePrice()" id="time">
								  	<label class="form-check-label" for="exampleRadios2"> Night</label>
					  		  	</div>
							</div>
							
						</div>
					  
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">Total</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" name="description" value="<%=event.getDescription()%>" required="required" id="total">
					    </div>
					    
					  </div>
					  
					  
						
						<input type="hidden" name="eventID" value="<%=event.getEventID()%>" />
					  <button type="submit" class="btn btn-primary" value="Update Event" >Update</button>
					  
					</form>
					
					<br>
					
				<form method="POST" action="DeleteEventServlet1">
					<input type="hidden" name="eventID"
						value="<%=event.getEventID()%>" />  
						<button type="submit" class="btn btn-danger" value="Delete Employee">Delete</button>
				</form>
	

			</div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div><!-- /#wrapper -->

	<script>
$(function(){
    $('[data-toggle="tooltip"]').tooltip();
    $(".side-nav .collapse").on("hide.bs.collapse", function() {                   
        $(this).prev().find(".fa").eq(1).removeClass("fa-angle-right").addClass("fa-angle-down");
    });
    $('.side-nav .collapse').on("show.bs.collapse", function() {                        
        $(this).prev().find(".fa").eq(1).removeClass("fa-angle-down").addClass("fa-angle-right");        
    });
}) 
</script>

<script type = "text/javascript">

function calculatePrice(){

    //Get selected data  
    var elt = document.getElementById("type");
    var type = elt.options[elt.selectedIndex].value;
    var qnt = document.getElementById("qnt").value;
    var hours = document.getElementById("hours").value;
    var time = document.getElementById("time").value;
    var loc = document.getElementById("location").value;
    
    var tPrice = 0;
    var total = 0;
    
    if(type=="wedding"){
    	tPrice = 100000;
    } else if(type=="birthday_party"){
    	tPrice = 25000;
    } else if(type=="funeral"){
    	tPrice = 15000;
    } else if(type=="get_together"){
    	tPrice = 60000;
    } else if(type=="prize_giving"){
    	tPrice = 40000;
    } else {
    	tPrice = 20000;
    }
    
  //convert data to integers
    qnt = parseInt(qnt);
    hours = parseInt(hours);
    
    var qPrice = 0;
    if(qnt>=50){
		qPrice = 25000;
	} else if(qnt>=20){
		qPrice = 10000;
	} else{
		qPrice = 0;
	}
	
	var hPrice = 0;
	if(hours>3){
		hPrice = 5000;
	} else{
		hPrice = 0;
	}
	
	var timeP = 0;
	if(time=="day"){
    	timeP = 0;
    } else{
    	timeP = 5000;
    }
	
	var lPrice = 0;
	if(loc=="indoor"){
    	lPrice = 0;
    } else{
    	lPrice = 10000;
    }
    //calculate total value  
    total = tPrice + hPrice + qPrice + timeP +lPrice; 

    //print value to  PicExtPrice 
    document.getElementById("total").value=total;

}

</script>

</body>
</html>