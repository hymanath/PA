/**
 * BioAuthLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class BioAuthLocator extends org.apache.axis.client.Service implements org.tempuri.BioAuth {

    public BioAuthLocator() {
    }


    public BioAuthLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BioAuthLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BioAuthSoap
    private java.lang.String BioAuthSoap_address = "http://www.attendance.ap.gov.in/ApSecatt/BioAuth.asmx";

    public java.lang.String getBioAuthSoapAddress() {
        return BioAuthSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BioAuthSoapWSDDServiceName = "BioAuthSoap";

    public java.lang.String getBioAuthSoapWSDDServiceName() {
        return BioAuthSoapWSDDServiceName;
    }

    public void setBioAuthSoapWSDDServiceName(java.lang.String name) {
        BioAuthSoapWSDDServiceName = name;
    }

    public org.tempuri.BioAuthSoap getBioAuthSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BioAuthSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBioAuthSoap(endpoint);
    }

    public org.tempuri.BioAuthSoap getBioAuthSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BioAuthSoapStub _stub = new org.tempuri.BioAuthSoapStub(portAddress, this);
            _stub.setPortName(getBioAuthSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBioAuthSoapEndpointAddress(java.lang.String address) {
        BioAuthSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.BioAuthSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BioAuthSoapStub _stub = new org.tempuri.BioAuthSoapStub(new java.net.URL(BioAuthSoap_address), this);
                _stub.setPortName(getBioAuthSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BioAuthSoap".equals(inputPortName)) {
            return getBioAuthSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "BioAuth");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BioAuthSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BioAuthSoap".equals(portName)) {
            setBioAuthSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
