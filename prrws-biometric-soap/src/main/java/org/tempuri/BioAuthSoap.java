/**
 * BioAuthSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface BioAuthSoap extends java.rmi.Remote {
    public java.lang.String employeeIntimeStatistics(java.lang.String date) throws java.rmi.RemoteException;
    public java.lang.String employeeOuttimeStatistics(java.lang.String date) throws java.rmi.RemoteException;
    public java.lang.String registeredEmployees(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String presentToday(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String presenBefore10AM(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String presentfrom10AMTo1030Am(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String presentfrom1030AmTo1100Am(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String presentafter1100Am(java.lang.String departmentcode) throws java.rmi.RemoteException;
    public java.lang.String empfromtodateAttendance(java.lang.String departmentcode, java.lang.String fromdate, java.lang.String todate) throws java.rmi.RemoteException;
}
