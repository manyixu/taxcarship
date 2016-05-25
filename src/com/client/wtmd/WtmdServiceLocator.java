/**
 * WtmdServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.client.wtmd;

public class WtmdServiceLocator extends org.apache.axis.client.Service implements com.client.wtmd.WtmdService {

    public WtmdServiceLocator() {
    }


    public WtmdServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WtmdServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WtmdServiceHttpPort
    private java.lang.String WtmdServiceHttpPort_address = "http://localhost:8001/taxcarship/services/WtmdService";

    public java.lang.String getWtmdServiceHttpPortAddress() {
        return WtmdServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WtmdServiceHttpPortWSDDServiceName = "WtmdServiceHttpPort";

    public java.lang.String getWtmdServiceHttpPortWSDDServiceName() {
        return WtmdServiceHttpPortWSDDServiceName;
    }

    public void setWtmdServiceHttpPortWSDDServiceName(java.lang.String name) {
        WtmdServiceHttpPortWSDDServiceName = name;
    }

    public com.client.wtmd.WtmdServicePortType getWtmdServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WtmdServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWtmdServiceHttpPort(endpoint);
    }

    public com.client.wtmd.WtmdServicePortType getWtmdServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.client.wtmd.WtmdServiceHttpBindingStub _stub = new com.client.wtmd.WtmdServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getWtmdServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWtmdServiceHttpPortEndpointAddress(java.lang.String address) {
        WtmdServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.client.wtmd.WtmdServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.client.wtmd.WtmdServiceHttpBindingStub _stub = new com.client.wtmd.WtmdServiceHttpBindingStub(new java.net.URL(WtmdServiceHttpPort_address), this);
                _stub.setPortName(getWtmdServiceHttpPortWSDDServiceName());
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
        if ("WtmdServiceHttpPort".equals(inputPortName)) {
            return getWtmdServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.wtmd.derun.com", "WtmdService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.wtmd.derun.com", "WtmdServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WtmdServiceHttpPort".equals(portName)) {
            setWtmdServiceHttpPortEndpointAddress(address);
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
