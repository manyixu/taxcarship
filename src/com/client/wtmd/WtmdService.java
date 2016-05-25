/**
 * WtmdService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.client.wtmd;

public interface WtmdService extends javax.xml.rpc.Service {
    public java.lang.String getWtmdServiceHttpPortAddress();

    public com.client.wtmd.WtmdServicePortType getWtmdServiceHttpPort() throws javax.xml.rpc.ServiceException;

    public com.client.wtmd.WtmdServicePortType getWtmdServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
