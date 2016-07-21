<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>BOARD WISE NOMINATED POSTS</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.tableShort thead th:nth-child(1)
{
	width:10% !important
}
.tableShort thead th:last-child
{
	width:7% !important
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12">
        	<h4>SHORTLISTING -CHAIRMAN POST - <small>A.P BUILDING AND OTHER CONSTRUCTION WORKERS WELFARE BOARD</small></h4>
            <h5>State Level ? Labour Department</h5>
        	<div class="panel panel-default panelDepartmentHead">
            	<div class="panel-body">
                	<div class="table-responsive" id="positionDivId">					
                    <!--	<table class="table table-bordered">
                        	<thead>
                            	<tr>
                                	<th rowspan="2"></th>
	                                <th rowspan="2">TOTAL AVAILABLE POSTS</th>
                                    <th rowspan="2">APPLICATIONS RECEIVED</th>
                                    <th rowspan="2">SHORTLISTED</th>
                                    <th colspan="4">CASTE GROUP</th>
                                    <th colspan="5">AGE GROUP</th>
                                </tr>
                                <tr>
                                	<th>SC</th>
                                    <th>ST</th>
                                    <th>BC</th>
                                    <th>OC</th>
                                    <th>20-29</th>
                                    <th>30-39</th>
                                    <th>40-49</th>
                                    <th>50-59</th>
                                    <th>60+</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                                	<td><p>THIS POST</p><small>Requested for this post members shortlisted</small></td>
                                    <td>02</td>
                                    <td>10</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                </tr>
                            	<tr>
                                	<td><p>ANY POST</p><small>Requested for any post members shortlisted for this</small></td>
                                    <td>02</td>
                                    <td>10</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                    <td>01</td>
                                </tr>
                            </tbody>
                        </table>-->
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12">
        	<div class="panel panel-default panelDepartmentHead">
            	<div class="panel-heading">
                	<h4 class="panel-title">APPLIED THIS POST - MEMBERS DETAILS</h4>
                </div>
                <div class="panel-body pad_0">
                	<div class="table-responsive">
                    	<!--<table class="table table-bordered table-condensed tableShort">
                        	<thead>
                            	<th>Name</th>
                                <th>Age</th>
                                <th>Caste</th>
                                <th>Sub Caste</th>
                                <th>Party Designations</th>
                                <th>Reports</th>
                                <th>Applied Any Dep/Corp</th>
                                <th>Shortlisted in any dep/ Corp</th>
                                <th>Current Status For this post</th>
                                <th>Update Application Status</th>
                            </thead>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" style="position:relative" class="text-center">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove closeIcon pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                            <tr>
                            	<td rowspan="2"><i class="glyphicon glyphicon-user"></i>  Shivaji</td>
                                <td rowspan="2">35</td>
                                <td rowspan="2">SC</td>
                                <td rowspan="2">Mala</td>
                                <td rowspan="2">State Organizing Secretary</td>
                                <td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                                <td rowspan="2" class="text-center" style="position:relative">
                                	<span class="appliedCount">02</span>
                                    <div class="appliedPostPopup">
                                    	<div class="appliedPostPopupArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <table class="table table-condensed">
                                                <thead>
                                                    <th>Department</th>
                                                    <th>Corporation</th>
                                                    <th>Position</th>
                                                    <th>Status</th>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Agriculture Marketing</td>
                                                        <td>Agriculture market committees</td>
                                                        <td>Chairman</td>
                                                        <td>Shortlisted</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                                <td rowspan="2">No</td>
                                <td rowspan="2">Shortlisted</td>
                                <td rowspan="2" style="position:relative;">
                                	<button class="btn btn-success btnPopup">UPDATE</button>
                                    <div class="updateDropDown">
                                        <div class="updateDropDownArrow">
                                        	<i class="glyphicon glyphicon-remove pull-right"></i>
                                            <label>Select Status</label>
                                            <select class="chosenSelect">
                                                <option>Shortlisted/Rejected</option>
                                            </select>
                                            <label>Comments</label>
                                            <textarea class="form-control"></textarea>
                                            <button class="btn btn-success btn-block m_top10">SUBMIT</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                            	<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>
                            </tr>
                        </table>
                        <p><i>Note:Click on <i class="glyphicon glyphicon-user"></i> to view complete profile and click on the applied count and know the status of last applied</i> </p>-->
						<div id="resultDivId"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="pdfModelId" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">CADRE REPORT DETAILS</h4>
			</div>
			<div class="modal-body" id="pdfReportDetailsId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" tabindex="-1" id="referModelId" role="dialog">  
	<div class="modal-dialog" style="width:60%;">      
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">REFERENCE DETAILS</h4>
			</div>
			<div class="modal-body" id="referenceDetailsId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
$('.chosenSelect').chosen();
$(document).on("click",".btnPopup",function(e){
	$(".updateDropDown").hide();
	$(this).closest('tr').find(".updateDropDown").show();
	e.stopPropagation()
});
$(document).on("click",".btnPopupAny",function(e){
	$(".updateDropDownAny").hide();
	$(this).closest('tr').find(".updateDropDownAny").show();
	e.stopPropagation()
});
$(document).on("click",function(){
	$(".updateDropDown").hide();
	$(".appliedPostPopup").hide();
});
$(document).on("click",".updateDropDown,.appliedPostPopup",function(e){
	e.stopPropagation()
});
$(document).on("click",".closeIcon",function(e){
	$(this).closest(".updateDropDown").hide();
});
$(document).on("click",".appliedCount",function(e){
	$(".appliedPostPopup").hide();
	$(this).closest('tr').find(".appliedPostPopup").show();
	e.stopPropagation();
	var candidateId = $(this).attr("attr_cand_id");
	var divId = $(this).attr("attr_divId");
getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId);
});
getBoardWiseNominatedPostMemberDetails();
function getBoardWiseNominatedPostMemberDetails(){
	var jsObj=
	   {				
		levelId:4,		//levelId,
		levelValue:299,		//levelValue,
		departmentId:2,		//departmentId,
		boardId:1,		//boardId,
		positionId:3,		//positionId,
		type:"any"		//type
		}
    $.ajax({
          type:'GET',
          url: 'getNominatedPostMemberDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null)
		   buildNominatedPostMemberDetails(result,"any",4,299);
   });
}

function buildNominatedPostMemberDetails(result,type,levelId,levelValue){
	var str='';
	
	str+='<table class="table table-bordered table-condensed tableShort">';
		str+='<thead>';
			str+='<th>Name</th>';
			str+='<th>Mobile</th>';
			str+='<th>Age</th>';
			str+='<th>Caste</th>';
			str+='<th>Sub Caste</th>';
			str+='<th>Party Designations</th>';
			str+='<th style="width:80px">Reports</th>';
			str+='<th>Applied Any Dep/Corp</th>';
			str+='<th>Reference</th>';
			str+='<th>Shortlisted in any dep/ Corp</th>';
			str+='<th>Current Status For this post</th>';
			str+='<th>Update Application Status</th>';
		str+='</thead>';
	if(result.subList != null && result.subList.length > 0){
		for(var i in result.subList){
			str+='<tr>';
			if(result.subList[i].tdpCadreId != null && result.subList[i].tdpCadreId > 0){
					str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].cadreName+'</td>';
					str+='<td>'+result.subList[i].cadreMobile+'</td>';
					str+='<td>'+result.subList[i].age+'</td>';
					str+='<td>'+result.subList[i].caste+'</td>';
					str+='<td>'+result.subList[i].casteName+'</td>';
					if(result.subList[i].partyPosition != null && result.subList[i].partyPosition.trim.length > 0)
						str+='<td>'+result.subList[i].partyPosition+'</td>';
					else
						str+='<td> - </td>';
					str+='<td>';
					if(result.subList[i].idNamevoList != null && result.subList[i].idNamevoList.length > 0){
						for(var j in result.subList[i].idNamevoList){
							str+='<p class="showPdfCls" attr_filePath="'+result.subList[i].idNamevoList[j].mobileNo+'" data-toggle="modal" data-target="#pdfModelId">'+result.subList[i].idNamevoList[j].status+'<i class="glyphicon glyphicon-list-alt pull-right" style="background-color:green;cursor:pointer;"></i></p>';
						}
					}
					else
						str+=' - ';
					str+='</td>';
					//Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			}
			else{
				str+='<td><i class="glyphicon glyphicon-user"></i>  '+result.subList[i].voterName+'</td>';
					str+='<td>'+result.subList[i].voterMoblie+'</td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
			}
				str+='<td style="position:relative" class="text-center">';
					str+='<span class="appliedCount" attr_cand_id="'+result.subList[i].nominatedPostCandidateId+'" attr_divId="departmentsTableId'+i+'">'+result.subList[i].otherDepartmentsCount+'</span>';
					str+='<div class="appliedPostPopup">';
						str+='<div class="appliedPostPopupArrow" id="departmentsTableId'+i+'">';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				if(result.subList[i].referCandCount != null)
					str+='<td><a class="referenceCls" data-toggle="modal" data-target="#referModelId" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'">'+result.subList[i].referCandCount+'</a></td>';
				else
					str+='<td> - </td>';
				if(result.subList[i].otherDeptShortListed != null && result.subList[i].otherDeptShortListed == 'YES')
					str+='<td>'+result.subList[i].otherDeptShortListed+'</td>';
				else
					str+='<td> NO </td>';
				str+='<td>'+result.subList[i].status+'</td>';
				str+='<td style="position:relative;">';
				if(type == "this"){
					str+='<button class="btn btn-success btnPopup updateButtonCls" attr_selected_status_id="updatedStatusSelectId'+i+'">UPDATE</button>';
					str+='<div class="updateDropDown">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivId'+i+'"></div>';
						//str+='<div class="statusUpdateDivCls" id="statusUpdateDivId'+i+'"></div>';
							str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
							str+='<label>Select Status</label>';
							str+='<select class="chosenSelect" id="updatedStatusSelectId'+i+'">';
								str+='<option value="0">Select Status</option>';
							str+='</select>';
							str+='<label>Comments</label>';
							str+='<textarea class="form-control" id="statusCommentId'+i+'"></textarea>';
							str+='<button class="btn btn-success btn-block m_top10 updateStatusCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_selected_status_id="updatedStatusSelectId'+i+'" attr_comment_id="statusCommentId'+i+'" attr_success_div="successDivId'+i+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
				}
				else if(type == "any"){
					str+='<button class="btn btn-success btnPopupAny updateButtonAnyCls" attr_count="'+i+'">UPDATE</button>';
					str+='<div class="updateDropDownAny">';
						str+='<div class="updateDropDownArrow">';
						str+='<div class="text-success" id="successDivAnyId'+i+'"></div>';
								str+='<div class="row">';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Department</label>';
									str+='<select class="chosenSelect" id="departmentAnyId'+i+'" onchange="getBoardsForDepartments('+i+')">';
										str+='<option value="0">Select Department</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Corporation/Board</label>';
									str+='<select class="chosenSelect" id="boardAnyId'+i+'" onchange="getPositionsForBoard('+i+')">';
										str+='<option value="0">Select Status</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Position</label>';
									str+='<select class="chosenSelect" id="positionAnyId'+i+'">';
										str+='<option value="0">Select Status</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-3 col-xs-12 col-sm-3">';
									str+='<label>Select Status</label>';
									str+='<select class="chosenSelect" id="updatedStatusAnyId'+i+'">';
										str+='<option value="0">Select Status</option>';
									str+='</select>';
								str+='</div>';
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<label>Comments</label>';
									str+='<textarea class="form-control" id="statusCommentAnyId'+i+'"></textarea>';
									str+='<button class="btn btn-success btn-block m_top10 updateStatusAnyCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_candidate_id="'+result.subList[i].nominatedPostCandidateId+'" attr_count="'+i+'" attr_levelId="'+levelId+'" attr_level_value="'+levelValue+'">SUBMIT</button>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				}	
				str+='</td>';
			str+='</tr>';
			/*str+='<tr>';
				str+='<td>Preferable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
			str+='</tr>';*/
		}
	}
	str+='</table>';
	
	$("#resultDivId").html(str);
}

$(document).on('click','.referenceCls',function(){
	var candidateId = $(this).attr("attr_candidate_id");
	
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getReferCadreDetailsForCandidateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildReferenceCandidateDetails(result);
	   }
   });
});

function buildReferenceCandidateDetails(result){
	var str='';
	
	str+='<table class="table table-condensed table-bordered">';
		str+='<thead>';
			str+='<th>Image</th>';
			str+='<th>Name</th>';
			str+='<th>Membership No</th>';
			str+='<th>Mobile No</th>';
			str+='<th>Position</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td><i class="glyphicon glyphicon-user"></i></td>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].percentage+'</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				str+='<td><p>'+result[i].publicRepr+'</p><p>'+result[i].partyPos+'</p></td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#referenceDetailsId").html(str);
}

$(document).on('click','.showPdfCls',function(){        
	var str = '';
	var filePath = $(this).attr("attr_filePath");
	str += '<iframe src="http://mytdp.com/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 

$(document).on("click",".updateButtonCls",function(){
	var selectDivId = $(this).attr("attr_selected_status_id");
	getApplicationStatus(selectDivId);
});

$(document).on("click",".updateButtonAnyCls",function(){
	var num = $(this).attr("attr_count");
	getDepartments(num);
	getApplicationStatus("updatedStatusAnyId"+num);
	$("#positionAnyId"+num).chosen();
	$("#boardAnyId"+num).chosen();
});

function getApplicationStatus(divId){
	$("#"+divId+" option").remove();
	
	var jsObj={}
	$.ajax({
          type:'GET',
          url: 'getAllApplicationStatusListAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#"+divId).append('<option value="0">Select Status</option>');
		   for(var i in result){
			   $("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#"+divId).chosen();
	   }
   });
}

function getDepartments(num){
	$("#departmentAnyId"+num+" option").remove();
	
	var jsObj={}
	$.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#departmentAnyId"+num).append('<option value="0">Select Department</option>');
		   for(var i in result){
			   $("#departmentAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#departmentAnyId"+num).chosen();
	   }
   });
}

function getBoardsForDepartments(num){
	$("#boardAnyId"+num+" option").remove();
	var depmtId = $("#departmentAnyId"+num).val();
	
	var jsObj={
		depmtId : depmtId
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
			$("#boardAnyId"+num).append('<option value="0">Select Corporation/Board</option>');
		   for(var i in result){
			   $("#boardAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#boardAnyId"+num).trigger('chosen:updated');
	   }
   });
}

function getPositionsForBoard(num){
	$("#positionAnyId"+num+" option").remove();
	var depmtId = $("#departmentAnyId"+num).val();
	var boardId = $("#boardAnyId"+num).val();
	
	var jsObj={
		depmtId : depmtId,
		boardId : boardId
	}
	$.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null && result.length > 0){
		   $("#positionAnyId"+num).append('<option value="0">Select Position</option>');
		   for(var i in result){
			   $("#positionAnyId"+num).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
		   }
		   $("#positionAnyId"+num).trigger('chosen:updated');
	   }
   });
}

$(document).on("click",".updateStatusCls",function(){
	var applicationId = $(this).attr("attr_application_id");
	var selectDivId = $(this).attr("attr_selected_status_id");
	var commentDivId = $(this).attr("attr_comment_id");
	var divId = $(this).attr("attr_success_div");
	
	var status = $("#"+selectDivId).val();
	var comment = $("#"+commentDivId).val();
	var jsObj=
	   {				
		nominatePostApplicationId:applicationId,
		statusId:status,
		comment :comment
		}
    $.ajax({
          type:'GET',
          url: 'updateApplicationStatusDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		if(result != null && result == 'success')
			$("#"+divId).html("Successfully Updated...");
		else
			$("#"+divId).html("Sorry,Exception Occured...Please try again...");
   });
});

$(document).on("click",".updateStatusAnyCls",function(){
	var applicationId = $(this).attr("attr_application_id");
	var candidateId = $(this).attr("attr_candidate_id");
	var num = $(this).attr("attr_count");
	var levelId= $(this).attr("attr_levelId");
	var levelVal = $(this).attr("attr_level_value");
	
	var deptId = $("#departmentAnyId"+num).val();
	var boardId = $("#boardAnyId"+num).val();
	var positionId = $("#positionAnyId"+num).val();
	var statusId = $("#updatedStatusAnyId"+num).val();
	var comment = $("#statusCommentAnyId"+num).val();
	
	var jsObj=
	   {	
		applicationId : applicationId,
		candidateId : candidateId,
		levelId : levelId,
		levelVal : levelVal,
		deptId : deptId,
		boardId : boardId,
		positionId : positionId,
		statusId : statusId,
		comment : comment
		}
    $.ajax({
          type:'GET',
          url: 'savingAnyPostCandidatesToPositionAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		if(result != null && result == 'success')
			$("#successDivAnyId"+num).html("Successfully Updated...");
		else
			$("#successDivAnyId"+num).html("Sorry,Exception Occured...Please try again...");
   });
});

getNominatedPostPostionDetails();
function getNominatedPostPostionDetails(){
	var jsObj=
	   {				
			depmtId:1,
			boardId:36,
			positionId:1,
			bLId:5,
			lValue:835
	   }
	    $.ajax({
          type:'GET',
          url: 'getNominatedPostPostionDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		  if(result != null){
			  buildNominatePostPositionDetails(result);
		  }
			
	   });  
}
function buildNominatePostPositionDetails(result){
	 var str='';
		   if(result !=null && result.length>0){
			   //console.log(result);
			   for(var i in result){			   
					str+='<table class="table table-bordered" id="nominatePositionDetilsId">';
					str+='<thead>';
					str+='<tr>';
					str+='<th rowspan="2"></th>';
					//str+='<th rowspan="2">TOTAL AVAILABLE POSTS</th>';
					str+='<th rowspan="2">APPLICATIONS RECEIVED</th>';
					str+='<th rowspan="2">SHORTLISTED</th>';
					str+='<th colspan="4">CASTE GROUP</th>';
					str+='<th colspan="5">AGE GROUP</th>';
					str+='</tr>';
					str+='<tr>';
					for(var j in result[i].idNameVoList){
					str+='<th>'+result[i].idNameVoList[j].name+'</th>';
					}
					str+='<th>20-29</th>';
					str+='<th>30-39</th>';
					str+='<th>40-49</th>';
					str+='<th>50-59</th>';
					str+='<th>60+</th>';
					str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					str+='<tr>';
					str+='<td><p>THIS POST</p><small>Requested for this post members shortlisted</small></td>';
					//str+='<td>02</td>';
					str+='<td>'+result[i].receivedCount+'</td>';
					str+='<td>'+result[i].shortListedCount+'</td>';
					for(var j in result[i].idNameVoList){
					str+='<td>'+result[i].idNameVoList[j].count+'</td>';
					}
					str+='<td>'+result[i].firstAgeGroupCount+'</td>';
					str+='<td>'+result[i].secondAgeGroupCount+'</td>';
					str+='<td>'+result[i].thirdAgeGroupCount+'</td>';
					str+='<td>'+result[i].fourthAgeGroupCount+'</td>';
					str+='<td>'+result[i].fifthAgeGroupCount+'</td>';
					str+='</tr>';
					str+='<tr>';
					str+='<td><p>ANY POST</p><small>Requested for any post members shortlisted for this</small>';str+='</td>';
					//str+='<td>02</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='<td>01</td>';
					str+='</tr>';
					str+='</tbody>';
					str+='</table>';
			   }
			   
			   $("#positionDivId").html(str);
		   }
}
function getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,divId){
	var jsObj=
	   {				
		candidateId:candidateId
		}
    $.ajax({
          type:'GET',
          url: 'getBrdWisNominPstAppliedDepOrCorpDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   buildDepartmentDetails(result,divId);
	   }
   });
}

function buildDepartmentDetails(result,divId){
	var str='';
	
	//str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
	str+='<table class="table table-condensed">';
		str+='<thead>';
			str+='<th>Level</th>';
			str+='<th>Location</th>';
			str+='<th>Department</th>';
			str+='<th>Corporation</th>';
			str+='<th>Position</th>';
			str+='<th>Status</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].hno+'</td>';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				str+='<td>'+result[i].pincode+'</td>';
				str+='<td>'+result[i].voterCardNo+'</td>';
				str+='<td>'+result[i].perc+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	$("#"+divId).html(str);
}
</script>
</body>
</html>
