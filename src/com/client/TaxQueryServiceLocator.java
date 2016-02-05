/**
 * TaxQueryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.client;

public class TaxQueryServiceLocator extends org.apache.axis.client.Service implements TaxQueryService {

    public TaxQueryServiceLocator() {
    }


    public TaxQueryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TaxQueryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TaxQueryServiceHttpPort
    private java.lang.String TaxQueryServiceHttpPort_address = "http://localhost:7080/taxcarship/services/TaxQueryService";

    public java.lang.String getTaxQueryServiceHttpPortAddress() {
        return TaxQueryServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TaxQueryServiceHttpPortWSDDServiceName = "TaxQueryServiceHttpPort";

    public java.lang.String getTaxQueryServiceHttpPortWSDDServiceName() {
        return TaxQueryServiceHttpPortWSDDServiceName;
    }

    public void setTaxQueryServiceHttpPortWSDDServiceName(java.lang.String name) {
        TaxQueryServiceHttpPortWSDDServiceName = name;
    }

    public TaxQueryServicePortType getTaxQueryServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TaxQueryServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTaxQueryServiceHttpPort(endpoint);
    }

    public TaxQueryServicePortType getTaxQueryServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            TaxQueryServiceHttpBindingStub _stub = new TaxQueryServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getTaxQueryServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTaxQueryServiceHttpPortEndpointAddress(java.lang.String address) {
        TaxQueryServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (TaxQueryServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                TaxQueryServiceHttpBindingStub _stub = new TaxQueryServiceHttpBindingStub(new java.net.URL(TaxQueryServiceHttpPort_address), this);
                _stub.setPortName(getTaxQueryServiceHttpPortWSDDServiceName());
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
        if ("TaxQueryServiceHttpPort".equals(inputPortName)) {
            return getTaxQueryServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.derun.com", "TaxQueryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.derun.com", "TaxQueryServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TaxQueryServiceHttpPort".equals(portName)) {
            setTaxQueryServiceHttpPortEndpointAddress(address);
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
