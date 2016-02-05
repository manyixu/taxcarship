/**
 * TaxQueryServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.client;

import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.BaseQueryResInfo;

public interface TaxQueryServicePortType extends java.rmi.Remote {
    public BaseQueryResInfo tarQuery(BaseQueryReqInfo basequeryreqinfo) throws java.rmi.RemoteException, SOAPException;
}
