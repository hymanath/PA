package org.tempuri;

public class BioAuthSoapProxy implements org.tempuri.BioAuthSoap {
  private String _endpoint = null;
  private org.tempuri.BioAuthSoap bioAuthSoap = null;
  
  public BioAuthSoapProxy() {
    _initBioAuthSoapProxy();
  }
  
  public BioAuthSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initBioAuthSoapProxy();
  }
  
  private void _initBioAuthSoapProxy() {
    try {
      bioAuthSoap = (new org.tempuri.BioAuthLocator()).getBioAuthSoap();
      if (bioAuthSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bioAuthSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bioAuthSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bioAuthSoap != null)
      ((javax.xml.rpc.Stub)bioAuthSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.BioAuthSoap getBioAuthSoap() {
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap;
  }
  
  public java.lang.String employeeIntimeStatistics(java.lang.String date) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.employeeIntimeStatistics(date);
  }
  
  public java.lang.String employeeOuttimeStatistics(java.lang.String date) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.employeeOuttimeStatistics(date);
  }
  
  public java.lang.String registeredEmployees(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.registeredEmployees(departmentcode);
  }
  
  public java.lang.String presentToday(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.presentToday(departmentcode);
  }
  
  public java.lang.String presenBefore10AM(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.presenBefore10AM(departmentcode);
  }
  
  public java.lang.String presentfrom10AMTo1030Am(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.presentfrom10AMTo1030Am(departmentcode);
  }
  
  public java.lang.String presentfrom1030AmTo1100Am(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.presentfrom1030AmTo1100Am(departmentcode);
  }
  
  public java.lang.String presentafter1100Am(java.lang.String departmentcode) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.presentafter1100Am(departmentcode);
  }
  
  public java.lang.String empfromtodateAttendance(java.lang.String departmentcode, java.lang.String fromdate, java.lang.String todate) throws java.rmi.RemoteException{
    if (bioAuthSoap == null)
      _initBioAuthSoapProxy();
    return bioAuthSoap.empfromtodateAttendance(departmentcode, fromdate, todate);
  }
  
  
}