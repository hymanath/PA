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
		</div><!â /.modal-content â>
	</div><!â /.modal-dialog â>
</div><!â /.modal â>
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
	var candidateId =$(this).attr("attr_cand_id");
getBrdWisNominPstAppliedDepOrCorpDetails(candidateId);
});
getBoardWiseNominatedPostMemberDetails();
function getBoardWiseNominatedPostMemberDetails(){
	var jsObj=
	   {				
		levelId:4,		//levelId,
		levelValue:101,		//levelValue,
		departmentId:2,		//departmentId,
		boardId:1,		//boardId,
		positionId:1,		//positionId,
		type:"this"		//type
		}
    $.ajax({
          type:'GET',
          url: 'getNominatedPostMemberDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null)
		   buildNominatedPostMemberDetails(result);
   });
}

function buildNominatedPostMemberDetails(result){
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
				//str+='<td>Suitable<i class="glyphicon glyphicon-list-alt pull-right"></i></td>';
				str+='<td style="position:relative" class="text-center">';
					str+='<span class="appliedCount" attr_cand_id='+result.subList[i].nominatedPostCandidateId+'>'+result.subList[i].otherDepartmentsCount+'</span>';
					str+='<div class="appliedPostPopup">';
						str+='<div class="appliedPostPopupArrow">';
							str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
							str+='<table class="table table-condensed">';
								str+='<thead>';
									str+='<th>Department</th>';
									str+='<th>Corporation</th>';
									str+='<th>Position</th>';
									str+='<th>Status</th>';
								str+='</thead>';
								str+='<tbody>';
									str+='<tr>';
										str+='<td>Agriculture Marketing</td>';
										str+='<td>Agriculture market committees</td>';
										str+='<td>Chairman</td>';
										str+='<td>Shortlisted</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Agriculture Marketing</td>';
										str+='<td>Agriculture market committees</td>';
										str+='<td>Chairman</td>';
										str+='<td>Shortlisted</td>';
									str+='</tr>';
								str+='</tbody>';
							str+='</table>';
						str+='</div>';
					str+='</div>';
				str+='</td>';
				if(result.subList[i].otherDeptShortListed != null && result.subList[i].otherDeptShortListed == 'YES')
					str+='<td>'+result.subList[i].otherDeptShortListed+'</td>';
				else
					str+='<td> NO </td>';
				str+='<td>'+result.subList[i].status+'</td>';
				str+='<td style="position:relative;">';
					str+='<button class="btn btn-success btnPopup">UPDATE</button>';
					str+='<div class="updateDropDown">';
						str+='<div class="updateDropDownArrow">';
							str+='<i class="glyphicon glyphicon-remove pull-right"></i>';
							str+='<label>Select Status</label>';
							str+='<select class="chosenSelect" id="updatedStatusSelectId'+i+'">';
								str+='<option value="3">Shortlisted/Rejected</option>';
							str+='</select>';
							str+='<label>Comments</label>';
							str+='<textarea class="form-control" id="statusCommentId'+i+'"></textarea>';
							str+='<button class="btn btn-success btn-block m_top10 updateStatusCls" attr_application_id="'+result.subList[i].nominatePostApplicationId+'" attr_selected_status_id="updatedStatusSelectId'+i+'" attr_comment_id="statusCommentId'+i+'">SUBMIT</button>';
						str+='</div>';
					str+='</div>';
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

$(document).on('click','.showPdfCls',function(){        
	var str = '';
	var filePath = $(this).attr("attr_filePath");
	str += '<iframe src="http://mytdp.com/'+filePath+'" width="100%" height="800">';    
	str += '</iframe>';
	$("#pdfReportDetailsId").html(str);
}); 

$(document).on("click",".updateStatusCls",function(){
	var applicationId = $(this).attr("attr_application_id");
	var selectDivId = $(this).attr("attr_selected_status_id");
	var commentDivId = $(this).attr("attr_comment_id");
	
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
function getBrdWisNominPstAppliedDepOrCorpDetails(candidateId){
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
	   if(result != null){
		   
	   }
   });
}
</script>
</body>
</html>
